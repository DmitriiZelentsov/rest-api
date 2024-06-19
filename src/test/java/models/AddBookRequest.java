package models;

import lombok.Data;
import java.util.List;

@Data
public class AddBookRequest {
    String userId;
    List<IsbnRequest> collectionOfIsbns;
}
