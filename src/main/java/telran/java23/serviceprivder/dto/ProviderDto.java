package telran.java23.serviceprivder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java23.serviceprivder.model.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDto {
    String profession;
    String firstName;
    String lastName;
    Long telephone;
    Long whatsApp;
    Address address;
    Boolean isActive;
    Set<Service> services;
    Double averageVote;


}
