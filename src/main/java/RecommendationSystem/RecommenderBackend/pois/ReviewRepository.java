package RecommendationSystem.RecommenderBackend.pois;

import RecommendationSystem.RecommenderBackend.categories.Category;
import RecommendationSystem.RecommenderBackend.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
//   Page<Poi> findById(@RequestParam("id") Long id, Pageable pageable);
    //Poi.();
    Review findByUserAndPoi(User user, Poi poi);

    @Query("select p.category from Review r inner join r.poi as p where r.user=?1 GROUP BY p.category HAVING avg(r.score) > ?2")
    List<Category> avgScoreForUserAndCategory(User user, double threshold);
}
