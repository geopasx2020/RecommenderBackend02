package RecommendationSystem.RecommenderBackend.UserInteresting;


import RecommendationSystem.RecommenderBackend.dto.InterestingRequest;

import RecommendationSystem.RecommenderBackend.categories.CategoryRepository;
import RecommendationSystem.RecommenderBackend.user.User;
import RecommendationSystem.RecommenderBackend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*",allowedHeaders={"*"})
@RequestMapping(path="api/v1/user")
public class UserInterestingController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;



    @PostMapping("/placeInteresting")
    public User placeInteresting(@RequestBody InterestingRequest request){
        return userRepository.save(request.getUser());
    }
    @GetMapping("/findAllInterestings")
    public List<User> findAllInterestings(){
        return userRepository.findAll();
    }


    @DeleteMapping(path = "/deleteInterestings/{userId}")
    public void deleteInterestingsByUserId(@PathVariable("userId") Long userId) {
        categoryRepository.deleteById(userId);
    }






}
