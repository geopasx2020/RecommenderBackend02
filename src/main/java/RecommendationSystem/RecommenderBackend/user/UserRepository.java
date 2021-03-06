package RecommendationSystem.RecommenderBackend.user;


import RecommendationSystem.RecommenderBackend.categories.Category;
import RecommendationSystem.RecommenderBackend.dto.InterestingResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository
extends JpaRepository<User,Long> {

    @Query("SELECT  u FROM User u WHERE u.email=?1")
    Optional<User> findUserByEmail(String email);

    List<User> findByEmailAndPasswordAndRole(String email, String password, String role);
    User findByEmailAndPassword(String email, String password);

    @Query("SELECT u FROM User u WHERE year(u.dob) between ?1 and ?2")
    List<User> findUsersByAge(int yearOfBirthSince, int yearOfBirthTo);




}
