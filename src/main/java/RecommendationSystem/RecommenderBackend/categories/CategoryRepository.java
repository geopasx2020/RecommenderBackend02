package RecommendationSystem.RecommenderBackend.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
//    Interesting findByInterestingId(Long interestingId);
//@Query("SELECT  i FROM Interesting i WHERE i.id=?1")
Optional<Category> findInterestingById(Long id);



}
