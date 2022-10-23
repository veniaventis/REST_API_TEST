package api.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
public class Company {

    private String name;
    private String catchPhrase;
    private String bs;

    public Company(@JsonProperty(value = "name") String name, @JsonProperty(value = "catchPhrase") String catchPhrase,
                   @JsonProperty(value = "bs") String bs){
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }
}