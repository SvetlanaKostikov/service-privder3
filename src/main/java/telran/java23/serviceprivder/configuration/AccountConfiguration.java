package telran.java23.serviceprivder.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import telran.java23.serviceprivder.model.AccountUserCredential;

import java.util.Base64;

@Configuration
public class AccountConfiguration {
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String getPasswordEncoder(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public AccountUserCredential tokens(String auth) {
        int pos = auth.indexOf(" ");
        String token = auth.substring(pos + 1);
        byte[] decodeBytes = Base64.getDecoder().decode(token);
        String credential = new String(decodeBytes);//user:password
        String[] credentials = credential.split(":");
        return new AccountUserCredential(credentials[0], credentials[1]);
    }
}
