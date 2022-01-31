package RecommendationSystem.RecommenderBackend.interesting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interestings")
public class InterestingController {

    @Autowired
    InterestingRepository interestingRepository;

    @GetMapping
    List<Interesting> getinterestings() {

        return interestingRepository.findAll();
    }

    @PostMapping
    Interesting createInteresting(@RequestBody Interesting interesting) {
        return interestingRepository.save(interesting);
    }

    @DeleteMapping(path = "/deleteInteresting/{interestingId}")
    public void deleteInteresting(@PathVariable("interestingId") Long interestingId) {
        interestingRepository.deleteById(interestingId);

    }

    @DeleteMapping(path = "/deleteInterestings/{userId}")
    public void deleteInterestingByUserId(@PathVariable("userId") Long userId) {
        interestingRepository.deleteById(userId);

    }

}
