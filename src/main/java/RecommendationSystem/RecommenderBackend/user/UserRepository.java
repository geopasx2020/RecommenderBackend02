package RecommendationSystem.RecommenderBackend.user;


import RecommendationSystem.RecommenderBackend.dto.InterestingResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository
extends JpaRepository<User,Long> {
    //select * from student where email=?
    @Query("SELECT  u FROM User u WHERE u.email=?1")
    Optional<User> findUserByEmail(String email);

    List<User> findByEmailAndPasswordAndRole(String email, String password, String role);






}
