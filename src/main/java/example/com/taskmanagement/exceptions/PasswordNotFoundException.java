package example.com.taskmanagement.exceptions;

public class PasswordNotFoundException extends Exception{

    public PasswordNotFoundException(String message){
        super(message);
    }

}
