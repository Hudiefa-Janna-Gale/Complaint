package team.Complaint.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {


    // Validation errors (@NotBlank, @Email, etc...)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex
    ){

        String message = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                    new ErrorResponse(
                        400,
                        message,
                        LocalDateTime.now()
                    )
                );
    }



    // General errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception ex
    ){

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                    new ErrorResponse(
                        500,
                        ex.getMessage(),
                        LocalDateTime.now()
                    )
                );
    }

}