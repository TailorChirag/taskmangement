package example.com.taskmanagement.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import example.com.taskmanagement.dtos.LoginRequestDto;
import example.com.taskmanagement.dtos.SignUpRequestDto;
import example.com.taskmanagement.exceptions.PasswordNotFoundException;
import example.com.taskmanagement.exceptions.TaskAlreadyExistsException;
import example.com.taskmanagement.exceptions.TaskNotFoundException;
import example.com.taskmanagement.exceptions.UsernameNotFoundException;
import example.com.taskmanagement.models.Token;
import example.com.taskmanagement.models.User;
import example.com.taskmanagement.models.Task;
import example.com.taskmanagement.service.services;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private services services;

    public UserController(services services) {
        this.services = services;
    }


    @PostMapping("/register")
    public User signUp(@RequestBody() SignUpRequestDto request) throws JsonProcessingException {

        String username = request.getUsername();
        String password = request.getPassword();

        return services.signUp(username, password);
    }

    @PostMapping("/login")
    public Token login(@RequestBody() LoginRequestDto requestDto) throws PasswordNotFoundException, UsernameNotFoundException {
        return services.login(requestDto.getUsername(), requestDto.getPassword());
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody() Task task) throws TaskAlreadyExistsException {
        return services.createTask(task);
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() throws TaskNotFoundException {
        return services.getTasks();
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return services.updateTask(id, task);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        services.deleteTask(id);
    }
}
