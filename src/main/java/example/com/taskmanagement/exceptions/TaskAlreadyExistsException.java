package example.com.taskmanagement.exceptions;

public class TaskAlreadyExistsException extends Exception{

        public TaskAlreadyExistsException(String message) {
            super(message);
        }
}
