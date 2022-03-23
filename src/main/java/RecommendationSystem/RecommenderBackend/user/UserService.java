package RecommendationSystem.RecommenderBackend.user;
import RecommendationSystem.RecommenderBackend.categories.CategoryRepository;
import RecommendationSystem.RecommenderBackend.categories.InterestingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
   @Autowired
    private final UserRepository userRepository;

   @Autowired
   private CategoryRepository interRepo;




    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }
    @Autowired
    public List<User> getUsers()
    {
        return userRepository.findAll();
    }
    @Autowired
    private InterestingService interestingService;


    public void addNewUser(User user) {
        Optional<User> userOptional=userRepository
                .findUserByEmail(user.getEmail());
        if(userOptional.isPresent()){
            throw new IllegalStateException("Email is already taken");
        }
        userRepository.save(user);
    }

    public String deleteUser(Long userId) {
        String message;
        boolean exists=userRepository.existsById(userId);
        if(!exists){
            message="user with id "+userId+" does not exist";
            throw new IllegalStateException("user with id "+userId+" does not exist");


        }
        else {
            message="user with id "+userId+" was deleted successfully ";
        }
        userRepository.deleteById(userId);
        return message;
    }

    public String deleteInteresting(Long userId,Long interestingId) {
        String message;
        boolean exists=interRepo.existsById(interestingId);

            message="interesting with id "+interestingId+" was deleted successfully ";
            interRepo.deleteById(interestingId);
        return message;
    }




    public User updateUser(User user){
//        List<Interesting> listInterestings=interRepo.findAll();
//        user.setInterestings((Set<Interesting>) listInterestings);
        Long id= user.getId();
        User usr=userRepository.findById(id).get();
        usr.setEmail(user.getEmail());

        usr.setLastname(user.getLastname());
        usr.setPassword(user.getPassword());
        user.setDob(user.getDob());
        usr.setGender(usr.getGender());
        usr.setFirstname(user.getFirstname());
        Optional<User> userOptional=userRepository
                .findUserByEmail(user.getEmail());
        usr.setInterestings(usr.getInterestings());

        if(userOptional.isPresent()){
            throw new IllegalStateException("Email is already taken");
        }
        return userRepository.save(usr);

    }

    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    public List<User> getSimilarUsers(User user){
        int year = user.getDob().getYear();
        return userRepository.findUsersByAge( year-2, year+8);
    }




    }








