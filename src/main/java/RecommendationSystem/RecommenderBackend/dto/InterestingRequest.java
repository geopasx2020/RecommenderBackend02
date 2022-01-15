package RecommendationSystem.RecommenderBackend.dto;

import RecommendationSystem.RecommenderBackend.user.User;

public class InterestingRequest {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "InterestingRequest{" +
                "user=" + user +
                '}';
    }

}
