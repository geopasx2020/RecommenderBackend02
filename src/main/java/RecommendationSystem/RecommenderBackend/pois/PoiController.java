package RecommendationSystem.RecommenderBackend.pois;
import RecommendationSystem.RecommenderBackend.categories.Category;
import RecommendationSystem.RecommenderBackend.categories.CategoryRepository;
import RecommendationSystem.RecommenderBackend.user.User;
import RecommendationSystem.RecommenderBackend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;

@RestController
@CrossOrigin(origins="http://localhost:4200",allowedHeaders={"*"}, allowCredentials = "true")
//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins="*",allowedHeaders={"*"})
@RequestMapping(path = "pois")
public class PoiController {
    @Autowired
    private PoiRepository poiRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    PoiService poiService;
    @Autowired
    UserService userService;


    @GetMapping
    public List<Poi> getPois() {

        return poiService.getPois();
    }



    @DeleteMapping(path = "{poiId}")
    public void deletePoi(@PathVariable("poiId") Long poiId) {

        poiService.deletePoi(poiId);
    }

//    @GetMapping("/{id}")
//    public Poi getPoi(@PathVariable("id") Long id) {
//
//        return poiRepository.findById(id).get();
//    }
/*
    @PutMapping("/updatePoi")
    public Poi updatePoi(@RequestBody Poi poi){

        return  poiService.updatePoi(poi);
    }
    */


    @GetMapping("/getpoi/{id}")
    public Poi getPoi(@PathVariable("id") Long id) {

        return poiRepository.findById(id).get();
    }

    @PostMapping("/{id}/review")
    public Poi reviewPoi(@PathVariable("id") Long poiId, @RequestBody RecommendationSystem.RecommenderBackend.dto.Review frontReview){
        System.out.println(frontReview.getScore());
        User user = userService.getUserById(frontReview.getUserId());
        Poi poi = poiRepository.findById(poiId).get();

        Review existingReview = reviewRepository.findByUserAndPoi(user, poi);
        if(existingReview == null) {
            Review review = new Review(poi, frontReview.getScore(), user);
            //Review review = new Review();
            //        review.setScore(score);
            reviewRepository.save(review);
        }else{
            existingReview.setScore(frontReview.getScore());
            reviewRepository.save(existingReview);
        }
         return poi;
        //return  poiService.updatePoi(poi);
    }



    @GetMapping(path = "/recommendations/{userId}")
    public List<Poi> getRecommendations(@PathVariable("userId") long userId) {
//    public List<Poi> getRecommendations(@CookieValue("sessionId") String sessionId ) {
//        long userId = userService.getLoggedInUser(sessionId).getId();

    //public List<Poi> getRecommendations(@PathVariable("userId"), long userId, int pageSize, int pageNo) {
        System.out.println("getRecommendations()");
        //1) similar users : reviewd pois
        //2) interesting categories: all pois
        //https://www.geeksforgeeks.org/user-based-collaborative-filtering/?ref=lbp)
        //3) similarity points with other users based on old reviews:  use this points to predict a score for every unreviewed POI

        User user = userService.getUserById(userId);

        //## 1
        List<User> users = userService.getSimilarUsers(user); //TODO only age?
        List<Poi> pois1 =  poiRepository.findByUsersReviews(users);

        //##2
        Collection<Category> myInterests = user.getInterestings();
        // visits * avgScore / maxScore (+absVisitsPerCategory)
        double baseScore = user.getAverageScore();
        List<Category> favoriteCategories = reviewRepository.avgScoreForUserAndCategory(user, baseScore );
        System.out.println("baseScore "+baseScore);
        System.out.println("favoriteCategories "+favoriteCategories);

                /*
        for( Object[] avgScore : reviewRepository.avgScoreForUserAndCategory(user, user.getAverageScore() ) ){
            System.out.println("cat "+avgScore[0]);

            System.out.println("score "+avgScore[1]);
            if()
        }*/
        System.out.println("myInterests "+myInterests);
        myInterests.addAll(favoriteCategories);
        System.out.println("myInterests "+myInterests);
        List<Poi> pois2 =  poiRepository.findByCategoryInOrderByAverageScore(myInterests); //TODO better sorting?

        //##3
        List<Poi> pois3 =  getRecommendationsBasedOnReviewSimilarity(user);

        // TODO sort by score?

        //return poiService.getPois();
        List<Poi> mixed = mix(pois3, pois2, pois1);
        System.out.println("count:"+" "+ pois1.size()+" "+pois2.size()+" "+pois3.size()+" "+mixed.size());
        //return mixed.subList(0,5);
        return mixed;
//        return pois1;
    }

    private List<Poi> mix(List<Poi>... lists){
        double weights[] = {0.8, 0.15, 0.05};
        int window = 6;
        List<Poi> result = new ArrayList<>();
        int index[] = {0, 0, 0};
        Set<Poi> included = new HashSet<>();

        int currentList = 0;

        while(true) {

            int start = index[currentList];
            int end = index[currentList] + (int) Math.ceil(window * weights[currentList]);
            System.out.println("picking "+currentList+" "+start+" "+end);
            for (int i = start; i < end && i < lists[currentList].size(); i++) {
                Poi p = lists[currentList].get(i);
                if(included.contains(p) == false) {
                    result.add(p);
                    included.add(p);
                }
                index[currentList]++;
            }
            int emptyLists = 0;
            do {
                currentList = (currentList + 1) % lists.length;
                emptyLists++;
                System.out.println("emptyLists "+emptyLists);
                if(emptyLists == 4){ return result; }
            }while( index[currentList] >= lists[currentList].size() );
            System.out.println("count:"+" "+ lists[0].size()+" "+lists[1].size()+" "+lists[2].size()+" "+result.size());
        }

    }

    private List<Poi> getRecommendationsBasedOnReviewSimilarity(User alice){
        HashMap<Poi, float[]> res3 = new HashMap<>();

        List<User> users = userService.getUsers();
        HashMap<Long, Review> aliceReviews = new HashMap<>();

        for(Review r : alice.getReviews()){
            aliceReviews.put(r.getPoi().getId(), r);
        }
        double aliceAvg = alice.getAverageScore();
        System.out.println("avg:"+aliceAvg+" of"+aliceReviews.size());

        users.remove(alice); //remove Alice
        for(User u : users){
            List<Review> notRatedByAlice = new ArrayList<>();
            double uAvg = u.getAverageScore();
            double aliceSum = 0;
            double uSum = 0;
            double distSum = 0;
            for(Review r : u.getReviews()){
                long poiId = r.getPoi().getId();
                double uS = ( r.getScore() - uAvg);

                if(  aliceReviews.containsKey(poiId ) == true ) {
                    Review aliceReview = aliceReviews.get(poiId);
                    double aS = (aliceReview.getScore()-aliceAvg);

                    distSum +=  aS*uS ;
                    aliceSum += aS*aS;
                    uSum += uS*uS;
                }else{
                    notRatedByAlice.add(r);
                }
            }

            double similarity = (distSum==0) ?0 :distSum / ( Math.sqrt(aliceSum) * Math.sqrt(uSum) );
            System.out.println("user:"+u.getId()+" similarity:"+similarity +" "+notRatedByAlice.size());

            if(similarity != 0) {
                for (Review review : notRatedByAlice) {
                    float[] f = res3.get(review.getPoi());
                    if (f == null) {
                        f = new float[]{0, 0};
                        res3.put(review.getPoi(), f);
                    }
                    f[0] += similarity * review.getScore(); //nominator
                    f[1] += Math.abs(similarity);         //denominator
                }
            }
        }

        //TODO add confidence for score (per POI), based on count of user reviews

        List<Recommendation> recommendations = new ArrayList<>();
        for(Map.Entry<Poi, float[]> entry : res3.entrySet()){
            float f[] = entry.getValue();
            double score = aliceAvg + (f[0]/f[1]);
            recommendations.add(new Recommendation(entry.getKey(), score) );
        }

        Collections.sort(recommendations);
        List<Poi> finalPois = new ArrayList<>();
        for(Recommendation r : recommendations){
            System.out.println("final recommendation: "+r.poi.getId()+" score:"+r.score);
            finalPois.add(r.poi);
        }

        return finalPois;
    }

    private static class Recommendation implements Comparable<Recommendation> {
        Poi poi;
        double score;

        public Recommendation(Poi poi, double score) {
            this.poi = poi;
            this.score = score;
        }

        @Override
        public int compareTo(Recommendation o) {
            if(this.score < o.score){ return +1; }
            else if( this.score == o.score){ return 0;}
            else{ return -1; }
        }
    }


}
