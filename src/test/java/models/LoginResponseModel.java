package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResponseModel {
    private String userId;
    private String username;
    private String password;
    private String token;
    private String expires;
    @JsonProperty("created_date")
    private String createdDate;
    private String isActive;
}
