package models;

import lombok.Data;

@Data
public class RegisterResponseModel {
    int id;
    String token;
    String error;
}
