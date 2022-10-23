
package api.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
public class Geo {

    private String lat;
    private String lng;

    public Geo(@JsonProperty(value = "lat") String lat, @JsonProperty(value = "lng") String lng) {
        this.lat = lat;
        this.lng = lng;
    }
}