package RecommendationSystem.RecommenderBackend.pois;

import javax.persistence.*;

@Entity
public class ImageData {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private byte[] data;

    private int size;

    ImageData(){}

    public ImageData(String name, byte[] data) {
        this.name = name;
        this.data = data;
        this.size = data.length;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
