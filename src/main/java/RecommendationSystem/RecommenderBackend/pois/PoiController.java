package RecommendationSystem.RecommenderBackend.pois;
import RecommendationSystem.RecommenderBackend.pois.Poi;
import RecommendationSystem.RecommenderBackend.pois.PoiRepository;
import RecommendationSystem.RecommenderBackend.pois.PoiService;
import RecommendationSystem.RecommenderBackend.pois.Poi;
import RecommendationSystem.RecommenderBackend.pois.PoiRepository;
import RecommendationSystem.RecommenderBackend.user.User;
import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import RecommendationSystem.RecommenderBackend.pois.PoiService;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.Deflater;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "pois")
public class PoiController {
    @Autowired
    private PoiRepository poiRepository;

    @Autowired
    PoiService poiService;

    @GetMapping
    public List<Poi> getPois() {

        return poiService.getPois();
    }

    @PostMapping
    public void registerNewPoi(@RequestBody Poi poi) {

        poiService.addNewPoi(poi);
    }

    @DeleteMapping(path = "{poiId}")
    public void deletePoi(@PathVariable("poiId") Long poiId) {
        poiService.deletePoi(poiId);
    }

    @GetMapping("/{id}")
    public Poi getPoi(@PathVariable("id") Long id) {

        return poiRepository.findById(id).get();
    }

    @PutMapping("/updatePoi")
    public Poi updatePoi(@RequestBody Poi poi){

        return  poiService.updatePoi(poi);
    }
    @PostMapping("/save")
    public RedirectView savePoi(Poi poi,
                                 @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        poi.setPhotos(fileName);

        Poi savedPoi = poiRepository.save(poi);

        String uploadDir = "poi-photos/" + savedPoi.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return new RedirectView("/poi", true);
    }





}
