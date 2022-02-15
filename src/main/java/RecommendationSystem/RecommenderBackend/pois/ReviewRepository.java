package RecommendationSystem.RecommenderBackend.pois;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
//   Page<Poi> findById(@RequestParam("id") Long id, Pageable pageable);

}
