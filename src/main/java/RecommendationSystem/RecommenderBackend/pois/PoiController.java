package RecommendationSystem.RecommenderBackend.pois;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{id}/review")
    public Poi reviewPoi(@PathVariable("id") Long poiId, @RequestBody Review review){
        System.out.println(review.getScore());
        Poi poi = poiRepository.findById(poiId).get();
        //Review review = new Review();
        review.setPoi(poi);
//        review.setScore(score);
        reviewRepository.save(review);
         return poi;
        //return  poiService.updatePoi(poi);
    }







}
