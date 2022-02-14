package RecommendationSystem.RecommenderBackend.poisCategory;

import RecommendationSystem.RecommenderBackend.pois.Poi;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="poiCategory")
public class poiCategory {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="category_name")
    String category_name;

    @Override
    public String toString() {
        return "poiCategory{" +
                "id=" + id +
                ", category_name='" + category_name + '\'' +
                ", pois=" + pois +
                '}';
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }


    @JsonIgnore
    @OneToMany(mappedBy = "poiCategory")
    private Set<Poi> pois=new HashSet<>();

    public Set<Poi> getPois() {
        return pois;
    }

    public void setPois(Set<Poi> pois) {
        this.pois = pois;
    }

    public poiCategory(Long id, String name, Set<Poi> pois) {
        this.id = id;

        this.pois = pois;
    }

    public poiCategory(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public poiCategory(Long id, String name) {
        this.id = id;

    }


}
