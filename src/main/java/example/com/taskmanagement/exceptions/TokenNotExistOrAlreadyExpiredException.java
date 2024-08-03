package example.com.taskmanagement.exceptions;

public class TokenNotExistOrAlreadyExpiredException extends Exception{

    public TokenNotExistOrAlreadyExpiredException(String message){
        super(message);
    }

}
