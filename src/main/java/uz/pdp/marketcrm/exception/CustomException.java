package uz.pdp.marketcrm.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private String code;
    private String field;
    private HttpStatus httpStatus;

    public CustomException(String message, String code, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public CustomException(String message, String code, String field, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.field = field;
        this.httpStatus = httpStatus;
    }
}
