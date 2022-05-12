package RecommendationSystem.RecommenderBackend.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class PoiDTO {

    private String title;
    private String startTime;
    @NotNull
    @JsonAlias("category_id")
    private Long categoryId;

    private Long imageId;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIndoor() {
        return indoor;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public void setIndoor(String indoor) {
        this.indoor = indoor;
    }

    private String endTime;
    private String indoor;
    private Float longtitude; //Nullable

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

    private Float latitude; //Nullable

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
