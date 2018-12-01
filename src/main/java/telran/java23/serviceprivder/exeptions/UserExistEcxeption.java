package telran.java23.serviceprivder.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserExistEcxeption extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    String message;

    public UserExistEcxeption(String message) {
        this.message = message;
    }

}






