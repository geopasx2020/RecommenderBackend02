package RecommendationSystem.RecommenderBackend.pois;
import RecommendationSystem.RecommenderBackend.categories.Category;
import RecommendationSystem.RecommenderBackend.user.User;
import RecommendationSystem.RecommenderBackend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "pois")
public class PoiController {
    @Autowired
    private PoiRepository poiRepository;
    @Autowired
    private ReviewRepository reviewRepository;

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

    /*
    HTTP1
    GET
    /api/poi/23/review?p=2
    Accept-Langaue: en

    {score: 2}
     */
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

    @GetMapping(path = "/r")
    public List<Poi> getRecommendations2() {
        //return new ArrayList<Poi>();
        return getRecommendations(79);
    }

    @GetMapping(path = "/recommendations/{userId}")
    public List<Poi> getRecommendations(@PathVariable("userId") long userId) {
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
        List<Poi> pois2 =  poiRepository.findByCategoryIn(myInterests); //TODO better sorting?

        //##3
        List<User> users2 = userService.getSimilarUsers(user); //TODO only age?
        List<Poi> pois3 =  poiRepository.findByUsersReviews(users);




        // TODO sort by score?

        //return poiService.getPois();
        return pois1;
    }

    private void f(User user){
        List<User> users = userService.getUsers();
        HashMap<Long, Review> aliceReviews = new HashMap<>();
        for(Review r : user.getReviews()){
            aliceReviews.put(r.getPoi().getId(), r);
        }
        double aliceAvg = user.getAverageScore();

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
            double simalirity = distSum / ( Math.sqrt(aliceSum) * Math.sqrt(uSum) );

            //simalirity * r.getScore()-

        }


    }


}
