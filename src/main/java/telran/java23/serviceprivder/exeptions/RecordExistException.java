package telran.java23.serviceprivder.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RecordExistException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    String message;

    public RecordExistException(String message) {
        super(message);
    }
}
