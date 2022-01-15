package RecommendationSystem.RecommenderBackend.pois;

import RecommendationSystem.RecommenderBackend.pois.Poi;
import RecommendationSystem.RecommenderBackend.pois.PoiRepository;
import RecommendationSystem.RecommenderBackend.pois.PoiService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PoiRepository extends JpaRepository<Poi,Long> {
    //select * from student where email=?
    @Query("SELECT  p FROM Poi p WHERE p.title=?1")
    Optional<Poi> findPoiByTitle(String title);
}
