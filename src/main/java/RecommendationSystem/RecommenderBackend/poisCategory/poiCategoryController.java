package RecommendationSystem.RecommenderBackend.poisCategory;

import RecommendationSystem.RecommenderBackend.pois.Poi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="*",allowedHeaders={"*"})
@RestController
@RequestMapping(value = "/category")
public class poiCategoryController {
    @Autowired
    public PoiCategoryService poiService;

    @GetMapping("/categories/{id}/pois")
    public List<Poi> getPoisByCategory(@PathVariable Long id){
        return poiService.getPoisByCategory(id);
    }

    @GetMapping("/allcategories")
    public List<poiCategory> getCategories(){
        return poiService.getCategories();
    }
}
