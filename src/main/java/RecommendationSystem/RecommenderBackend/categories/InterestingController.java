package RecommendationSystem.RecommenderBackend.categories;

import RecommendationSystem.RecommenderBackend.pois.Poi;
import RecommendationSystem.RecommenderBackend.pois.PoiRepository;
import RecommendationSystem.RecommenderBackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:4200",allowedHeaders={"*"}, allowCredentials = "true")
@RequestMapping("/interestings")
public class InterestingController {


    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    PoiRepository poiRepository;
    /*
        @GetMapping
        List<Category> getinterestings() {

            return categoryRepository.findAll();
        }

        @PostMapping
        Category createInteresting(@RequestBody Category category) {
            return categoryRepository.save(category);
        }

        @DeleteMapping(path = "/deleteInteresting/{interestingId}")
        public void deleteInteresting(@PathVariable("interestingId") Long interestingId) {
            categoryRepository.deleteById(interestingId);

        }

        @DeleteMapping(path = "/deleteInterestings/{userId}")
        public void deleteInterestingByUserId(@PathVariable("userId") Long userId) {
            categoryRepository.deleteById(userId);

        } */


}
