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
    private String startTime;

    @Override
    public String toString() {
        return "Poi{" +
                "Id=" + Id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", startTime='" + startTime + '\'' +

                ", endTime='" + endTime + '\'' +
                ", indoor='" + indoor + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }








    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        imagePath = imagePath;
    }

    @Column(name="time_end")
    private String endTime;
    @Column(name="indoor")
    private String indoor;

    @Column
    private String imagePath;


    public Poi(){

    }

    public Poi(Long id, String title, String category, String startTime, String endTime, String indoor, String imagePath) {
        Id = id;
        this.title = title;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.indoor = indoor;
        this.imagePath=imagePath;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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












}
