package models;

import lombok.Data;
import java.util.List;

@Data
public class AddBookResponse {
    List<IsbnRequest> books;
}
