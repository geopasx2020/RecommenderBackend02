package RecommendationSystem.RecommenderBackend.user;

import RecommendationSystem.RecommenderBackend.categories.Category;
import RecommendationSystem.RecommenderBackend.categories.CategoryRepository;
import RecommendationSystem.RecommenderBackend.categories.InterestingService;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@CrossOrigin(origins="*",allowedHeaders={"*"})
@RestController
@RequestMapping(path="api/v1/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private InterestingService interestingService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public UserController(UserService userService) {
        this.userService = userService;


    }

    @GetMapping
    public List<User> getUsers() {

        return userService.getUsers();
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {

        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);

    }




    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id) {

        return userRepository.findById(id).get();
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user){

        return  userService.updateUser(user);
    }

    @JsonIgnore
    @GetMapping({ "/User/{email}/{password}/{role}" })
    public User login(@PathVariable String email,@PathVariable String password,@PathVariable String role) {
        return userRepository.findByEmailAndPasswordAndRole(email, password,role).get(0);
    }
//*****************************************
    @PostMapping("/createInteresting")
    Category createInteresting(@RequestBody Category category){
        return categoryRepository.save(category);
    }
    @PostMapping("/{userId}/interestings/{interestingId}")
    User createInterestingToUser(

            @PathVariable Long userId,
            @PathVariable Long[]  interestingId
    )
    {
        User user=userRepository.findById(userId).get();
//       List<Interesting> interesting=interestingRepository.findById(interestingId);
//        user.enrollInteresting(interesting);


        return userRepository.save(user);

    }
   
    @PutMapping("/{userId}/interestings/{interestingId}")
    User enrollInterestingToUser(

            @PathVariable Long userId,
            @PathVariable Long interestingId
    )
    {
        User user=userRepository.findById(userId).get();
        Category category = categoryRepository.findById(interestingId).get();
        user.enrollInteresting(category);

        return userRepository.save(user);


}
    @DeleteMapping("/{userId}/interestings/{interestingId}")
    User enrollInteresting1ToUser(

            @PathVariable Long userId,
            @PathVariable Long interestingId
    )
    {
        User user=userRepository.findById(userId).get();
        Category category = categoryRepository.findById(interestingId).get();
        user.removeInteresting(category);

        return userRepository.save(user);


    }
//
//    @PutMapping("/{userId}/test/{interestingIds}")
//
//    User enrollInterestingToUser(
//            @PathVariable Long userId,
//            @PathVariable String intererestingIds
//    )
//    {
//        User user=userRepository.findById(userId).get();
//
//        return userRepository.save(user);
//
//    }

    @GetMapping("/interestings")
    public List<Category> getInterestings() {

        return categoryRepository.findAll();
    }

    //get interestings by user id
    @GetMapping("/users/interestings/{id}")
    public User getUserInt(@PathVariable Long id) {

       return userRepository.findById(id).get();
    }

    @RequestMapping(value="/test/{interestingIds}", method=RequestMethod.GET)
    @ResponseBody
    public String test(@PathVariable List<Long> interestingIds) {
        //Example: pring your params
        for(Long param : interestingIds) {
            System.out.println("id: " + param);
        }
        return "Dummy";
    }






}

