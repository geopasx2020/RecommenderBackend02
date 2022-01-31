package RecommendationSystem.RecommenderBackend.interesting;

import RecommendationSystem.RecommenderBackend.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="interesting")
public class Interesting {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="description")
    private String description;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Column(name="imagePath")
    private String imagePath;

    @JsonIgnore
    @ManyToMany(mappedBy = "selectedInterestings",cascade = CascadeType.PERSIST)

    private Set<User> users=new HashSet<>();

    public String allInterestings;


    public Long getId() {
        return id;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public String getAllInterestings(){
        return  allInterestings;}

    public Interesting(){

    }


}
