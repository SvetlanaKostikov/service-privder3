package telran.java23.serviceprivder.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="email")
@Document(collection = "Clients")
public class Client {
    @Id
    String email;
    String password;
    String firstName;
    String lastName;
    Long telephone;
    LinkedHashMap<LocalDateTime,Record> records;

}
