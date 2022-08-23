package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestCustomException extends RuntimeException {

    private String error;

    public BadRequestCustomException(String errorMessage) {
        super(errorMessage);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
