package telran.java23.serviceprivder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientRegisterDto {
    String email;
    String password;
    String firstName;
    String lastName;
    Long telephone;
}
