package RecommendationSystem.RecommenderBackend.pois;
import RecommendationSystem.RecommenderBackend.user.User;
import RecommendationSystem.RecommenderBackend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

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

    @PutMapping("/updatePoi")
    public Poi updatePoi(@RequestBody Poi poi){

        return  poiService.updatePoi(poi);
    }

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

    @GetMapping(path = "/recommendations/{userId}/")
    public List<Poi> getRecommendations(@PathVariable("userId") long userId) {
        //copy from users with similar
        //pois in interesting categories
        User user = userService.getUserById(userId);
        user.getInterestings();


        return poiService.getPois();
    }


}
