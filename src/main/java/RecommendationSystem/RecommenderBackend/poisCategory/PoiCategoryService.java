package RecommendationSystem.RecommenderBackend.poisCategory;

import RecommendationSystem.RecommenderBackend.pois.Poi;
import RecommendationSystem.RecommenderBackend.user.User;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoiCategoryService {
    @Autowired
    private poiCategoryRepository poiCategoryRepo;

    public List<Poi> getPoisByCategory(Long id) {
        return poiCategoryRepo.getPoisByCategory(id);
    }
    @Autowired
    public List<poiCategory> getCategories()
    {

        return poiCategoryRepo.findAll();
    }

}
