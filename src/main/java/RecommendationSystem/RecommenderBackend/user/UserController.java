package RecommendationSystem.RecommenderBackend.user;

import RecommendationSystem.RecommenderBackend.categories.Category;
import RecommendationSystem.RecommenderBackend.categories.CategoryRepository;
import RecommendationSystem.RecommenderBackend.categories.InterestingService;
import RecommendationSystem.RecommenderBackend.dto.UserDTO;
import RecommendationSystem.RecommenderBackend.dto.User2DTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200",allowedHeaders={"*"}, allowCredentials = "true")
//@CrossOrigin(origins="*",allowedHeaders={"*"})
//@CrossOrigin(origins = "http://localhost:4200")
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
    public void deleteUser(@PathVariable("userId") Long userId, @CookieValue("sessionId") String sessionId  ) {
        if( !userService.getLoggedInUser(sessionId).isAdmin() ){ throw new ResponseStatusException( HttpStatus.FORBIDDEN, "Not Admin"); }


        userService.deleteUser(userId);

    }




    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userRepository.findById(id).get();
    }

    public User getMyUser(@CookieValue("sessionId") String  sessionId) {
        return userRepository.findById(1L).get();
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user){

        return  userService.updateUser(user);
    }

    //@JsonIgnore
    @GetMapping({ "/User/{email}/{password}/{role}" })
    public UserDTO login1(@PathVariable String email,@PathVariable String password,@PathVariable String role) {
        User user = userRepository.findByEmailAndPasswordAndRole(email, password,role).get(0);
        return new UserDTO(user);
    }

    //@PostMapping({ "/User/login" })
    @GetMapping({ "/login/{email}/{password}" })
    //public UserDTO login(UserDTO credentials, HttpServletResponse httpResponse) {
    public UserDTO login(UserDTO credentials, HttpServletResponse httpResponse, @PathVariable String email,@PathVariable String password) {
        System.out.println("login()");
        //User user = userRepository.findByEmailAndPassword(credentials.getEmail(), credentials.getPassword());
        User user = userService.login(email, password);
        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found") ;
        String sessionId = userService.startSession(user);

        Cookie cookie = new Cookie("sessionId", sessionId);
        // expires in 1 hour
        cookie.setMaxAge(60 * 60);
        // optional properties
        //cookie.setSecure(true);
//        cookie.setDomain("localhost:8080");
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        httpResponse.addCookie(cookie);
        return new UserDTO(user);
//      return null;
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

