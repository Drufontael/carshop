package tech.drufontael.carshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidArgumentFormatException extends RuntimeException{
    public InvalidArgumentFormatException(String message) {
        super(message);
    }
}
