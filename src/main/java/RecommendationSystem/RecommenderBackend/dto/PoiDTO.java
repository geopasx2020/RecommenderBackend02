package RecommendationSystem.RecommenderBackend.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.persistence.Column;

public class PoiDTO {

    private String title;
    private String startTime;
    @JsonAlias("category_id")
    private Long categoryId;

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
