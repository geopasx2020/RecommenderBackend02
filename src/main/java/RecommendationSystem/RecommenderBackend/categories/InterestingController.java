package RecommendationSystem.RecommenderBackend.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interestings")
public class InterestingController {

    @Autowired
    CategoryRepository categoryRepository;

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

    }

}
