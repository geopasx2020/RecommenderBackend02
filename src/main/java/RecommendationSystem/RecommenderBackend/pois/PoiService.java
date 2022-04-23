package RecommendationSystem.RecommenderBackend.pois;

import RecommendationSystem.RecommenderBackend.pois.Poi;
import RecommendationSystem.RecommenderBackend.pois.PoiRepository;
import RecommendationSystem.RecommenderBackend.pois.PoiService;
import RecommendationSystem.RecommenderBackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoiService {

    @Autowired
    private  PoiRepository poiRepository;
//    public List<Poi> getAllPois();
//    public boolean deleteFile(Long id,String file);
@Autowired
public List<Poi> getPois()
{

    return poiRepository.findAll();
}

    public List<Poi> getRecommendations()
    {

        return poiRepository.findAll();
    }



public String deletePoi(Long poiId) {
        String message;
        boolean exists=poiRepository.existsById(poiId);
        if(!exists){
            message="poi with id "+poiId+" does not exist";
            throw new IllegalStateException("poi with id "+poiId+" does not exist");


        }
        else {
            message="poi with id "+poiId+" was deleted successfully ";
        }
        poiRepository.deleteById(poiId);
        return message;
    }
/*
    public Poi updatePoi(Poi poi){

        Long id= poi.getId();
        Poi pnt=poiRepository.findById(id).get();
        pnt.setTitle(poi.getTitle());

        pnt.setTitle(poi.getTitle());
//        pnt.setCategory(poi.getCategory());
        pnt.setStartTime(poi.getStartTime());
        pnt.setEndTime(poi.getEndTime());
        pnt.setIndoor(poi.getIndoor());
        pnt.setCategory(poi.getPoiCategory());

        return poiRepository.save(pnt);

    }
*/
public void addNewPoi(Poi poi) {
       poiRepository.save(poi);
}


}
