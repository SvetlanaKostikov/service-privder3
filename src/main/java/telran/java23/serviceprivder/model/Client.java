package telran.java23.serviceprivder.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Iterator;
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
    //String - eto LocalDateTime
    LinkedHashMap<String,Record> records;

    public Record findRecord(String id){
        for (Map.Entry<String, Record> entry : records.entrySet()) {
           if(entry.getValue().getId().equals(id)){
               return entry.getValue();
            }
        }
        return null;
    }

    public String findKeyRecord(String id){
        for (Map.Entry<String, Record> entry : records.entrySet()) {
            if(entry.getValue().getId().equals(id)){
                return entry.getKey();
            }
        }
        return null;
    }

    public void deleteRecord(String id){
        records.remove(findKeyRecord(id));
    }

}
