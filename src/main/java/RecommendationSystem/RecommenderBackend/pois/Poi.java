package RecommendationSystem.RecommenderBackend.pois;

//--14/2/2022--



import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import RecommendationSystem.RecommenderBackend.categories.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="poi")
public class Poi {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "title")
    private String title;

    //@Column(name = "category")
    //private String category;
    @Column(name="time_start")
    private String startTime;


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

    private Float averageScore; //Nullable

    private Float longtitude; //Nullable

    private Float latitude; //Nullable

    @Column
    private String imagePath;

    @OneToOne
    private ImageData image;

    @ManyToOne
//    @ManyToOne(fetch=FetchType.LAZY)
    private Category category; //{id:}

    @OneToMany(mappedBy = "poi")
    private List<Review> reviews;


    public Poi(){

    }

    public Poi(Long id, String title, Category category, String startTime, String endTime, String indoor, String imagePath) {
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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
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

    public Float getAverageScore() {
        return averageScore;
    }

    public Float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Float longtitude) {
        this.longtitude = longtitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public void calculateAverageScore() {
        if( reviews.isEmpty() ) { this.averageScore = null; }
        int sum = 0;
        for(Review r : reviews){
            sum += r.getScore();
        }
        this.averageScore = ((float)sum) / reviews.size();
    }

    public Poi(Long id, String title, String startTime, String endTime, String indoor, Float averageScore, Float longtitude, Float latitude, String imagePath, Category category) {
        Id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.indoor = indoor;
        this.averageScore = averageScore;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.imagePath = imagePath;
        this.category = category;

    }

    public void setIndoor(String indoor) {

        this.indoor = indoor;
    }

    public void setCategory(Category category) {

        this.category = category;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Poi == false) return  false;
        if(this.Id == null) return false;
        if(((Poi)o).Id == null) return false;
        return this.Id == ((Poi)o).Id;
    }

    @JsonIgnore
    public ImageData getImage() {
        return image;
    }
    public long getImageId() {
        return image.getId();
    }


    public void setImage(ImageData image) {
        this.image = image;
    }
}
