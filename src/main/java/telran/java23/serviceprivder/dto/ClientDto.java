package telran.java23.serviceprivder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java23.serviceprivder.model.Record;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientDto {
    String firstName;
    String lastName;
    Long telephone;
    Set<Record> records;
}
