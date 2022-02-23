package RecommendationSystem.RecommenderBackend.pois;

import RecommendationSystem.RecommenderBackend.interesting.Interesting;
import RecommendationSystem.RecommenderBackend.pois.Poi;
import RecommendationSystem.RecommenderBackend.pois.PoiRepository;
import RecommendationSystem.RecommenderBackend.pois.PoiService;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PoiRepository extends JpaRepository<Poi,Long> {
   //Page<Poi> findById(@RequestParam("id") Long id, Pageable pageable);
   //List<Poi> findByCategoryIn(Collection<Interesting> categories);  //select p from Poi where p.categoru in :categories
//   List<Poi> findByCategoryIn(Collection<String> categories);  //select p from Poi where p.categoru in :categories
}
