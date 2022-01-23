package RecommendationSystem.RecommenderBackend.pois;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="poi")
public class Poi {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name="time_start")
    private LocalTime startTime;

    @Column(name="time_end")
    private LocalTime endTime;
    @Column(name="indoor")
    private String indoor;
    @Column(nullable = true, length = 64)
    private String photos;

//    public void setPhotosImagePath(String photosImagePath) {
//        PhotosImagePath = photosImagePath;
//    }

    @Column(nullable = true)
    private String PhotosImagePath;

    public Poi(Long id, String title, String category, LocalTime startTime, LocalTime endTime, String indoor, String photos) {
        Id = id;
        this.title = title;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.indoor = indoor;
        this.photos = photos;
    }
    @Transient
    public String getPhotosImagePath() {
        if (photos == null || Id == null) return null;

        return "/pois-photos/" + Id + "/" + photos;
    }

    public String getPhotos() {

        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }




    public Poi(){

    }

    public Poi(Long id, String title, String category, LocalTime startTime, LocalTime endTime, String indoor) {
        Id = id;
        this.title = title;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.indoor = indoor;

    }

    @Override
    public String toString() {
        return "Poi{" +
                "Id=" + Id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", indoor='" + indoor + '\'' +

                '}';
    }



    public Poi(String originalFilename, String contentType, byte[] compressBytes) {
    }

    public String getTitle() {
        return title;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public String getIndoor() {
        return indoor;
    }

    public void setIndoor(String indoor) {
        this.indoor = indoor;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }










}
