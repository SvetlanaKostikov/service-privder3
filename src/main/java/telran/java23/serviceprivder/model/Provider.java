package telran.java23.serviceprivder.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="email")
@Document(collection = "Providers")
public class Provider {
    @Id
    String email;
    String password;
    String profession;
    String firstName;
    String lastName;
    Long telephone;
    Long whatsApp;
    Address address;
    Boolean isActive;
    Set<Service> services;
    Set<Record>records;
    Schedule schedule;
    Double vote;
    Set<String>comments;



}
