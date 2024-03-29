package tech.drufontael.carshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<?> resourseNotFoundException(ResourseNotFoundException ex, WebRequest request){
        ErrorResponse errorDatails=new ErrorResponse(new Date(), HttpStatus.NOT_FOUND.toString(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDatails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidArgumentFormatException.class)
    public ResponseEntity<?> invalidArgumentFormatException(InvalidArgumentFormatException ex,WebRequest request){
        ErrorResponse errorDatails=new ErrorResponse(new Date(), HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDatails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<?> resourceAlreadyExistsException(ResourceAlreadyExistsException ex,WebRequest request){
        ErrorResponse errorDetails=new ErrorResponse(new Date(),HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
}
