package example.com.taskmanagement.ControllerAdvices;



import example.com.taskmanagement.dtos.ExceptionDto;
import example.com.taskmanagement.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(UsernameNotFoundException.class)
    private ResponseEntity<ExceptionDto> handleUsernameNotfoundException(UsernameNotFoundException message){
        ExceptionDto dto = new ExceptionDto();
        dto.setMesasgae(message.getMessage());
        return new ResponseEntity<>(
                dto,
                HttpStatus.valueOf(302)
        );
    }

    @ExceptionHandler(PasswordNotFoundException.class)
    private ResponseEntity<ExceptionDto> handlePasswordNotFoundException(PasswordNotFoundException message){
        ExceptionDto dto = new ExceptionDto();
        dto.setMesasgae(message.getMessage());
        return new ResponseEntity<>(
                dto,
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(TokenNotExistOrAlreadyExpiredException.class)
    private ResponseEntity<ExceptionDto> handleTokenNotExistOrAlreadyExpiredException(
            TokenNotExistOrAlreadyExpiredException message){
        ExceptionDto dto = new ExceptionDto();
        dto.setMesasgae(message.getMessage());
        return new ResponseEntity<>(
                dto,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(TaskNotFoundException.class)
    private ResponseEntity<ExceptionDto> handleTaskNotFoundException(TaskNotFoundException message){
        ExceptionDto dto = new ExceptionDto();
        dto.setMesasgae(message.getMessage());
        return new ResponseEntity<>(
                dto,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(TaskAlreadyExistsException.class)
    private ResponseEntity<ExceptionDto> handleTaskAlreadyExistsException(TaskAlreadyExistsException message){
        ExceptionDto dto = new ExceptionDto();
        dto.setMesasgae(message.getMessage());
        return new ResponseEntity<>(
                dto,
                HttpStatus.NOT_FOUND
        );
    }



}
