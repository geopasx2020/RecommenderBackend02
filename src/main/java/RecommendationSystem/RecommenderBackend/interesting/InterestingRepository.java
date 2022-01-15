package RecommendationSystem.RecommenderBackend.interesting;

import RecommendationSystem.RecommenderBackend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterestingRepository extends JpaRepository<Interesting,Long> {
//    Interesting findByInterestingId(Long interestingId);
@Query("SELECT  i FROM Interesting i WHERE i.id=?1")
Optional<Interesting> findInterestingById(Long id);



}
