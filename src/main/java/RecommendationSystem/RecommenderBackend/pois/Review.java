package RecommendationSystem.RecommenderBackend.pois;

//--14/2/2022--


import RecommendationSystem.RecommenderBackend.user.User;

import javax.persistence.*;

@Entity
@Table(name="review", uniqueConstraints = { @UniqueConstraint(columnNames = { "poi_id", "user_id" }) }  )
public class Review {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="poi_id")
    private Poi poi;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    private int score;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;


    public Review(){   }

    public Review(Poi poi, int score, User user) {
        this.poi = poi;
        this.score = score;
        this.user = user;
    }

    public Review(int score) {
        this.score = score;
    }

    public void setPoi(Poi poi) {
        this.poi = poi;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

}
