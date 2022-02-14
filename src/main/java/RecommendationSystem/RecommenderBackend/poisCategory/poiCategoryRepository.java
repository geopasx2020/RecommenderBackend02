package RecommendationSystem.RecommenderBackend.poisCategory;

import RecommendationSystem.RecommenderBackend.pois.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface poiCategoryRepository extends JpaRepository<poiCategory,Long> {
    @Query("select pois from poiCategory p where p.id=?1")
    List<Poi> getPoisByCategory(Long id);

//    public poiCategory findByCategoryId(Long id);
}
