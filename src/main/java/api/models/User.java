package api.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
public class User {

    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public User(@JsonProperty(value = "id") int id, @JsonProperty(value = "name") String name,
                @JsonProperty(value = "username") String username, @JsonProperty(value = "email") String email,
                @JsonProperty(value = "address") Address address, @JsonProperty(value = "phone") String phone,
                @JsonProperty(value = "website") String website, @JsonProperty(value = "company") Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }
}