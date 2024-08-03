package example.com.taskmanagement.service;


import example.com.taskmanagement.exceptions.PasswordNotFoundException;
import example.com.taskmanagement.exceptions.UsernameNotFoundException;
import example.com.taskmanagement.models.Token;

public interface userService {

    public Token login(String userName, String password) throws UsernameNotFoundException, PasswordNotFoundException;
}
