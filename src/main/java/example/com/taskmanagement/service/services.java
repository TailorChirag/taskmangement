package example.com.taskmanagement.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import example.com.taskmanagement.exceptions.PasswordNotFoundException;
import example.com.taskmanagement.exceptions.TaskAlreadyExistsException;
import example.com.taskmanagement.exceptions.TaskNotFoundException;
import example.com.taskmanagement.exceptions.UsernameNotFoundException;
import example.com.taskmanagement.models.Task;
import example.com.taskmanagement.models.Token;
import example.com.taskmanagement.models.User;
import example.com.taskmanagement.repositories.TaskRepository;
import example.com.taskmanagement.repositories.TokenRepository;
import example.com.taskmanagement.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class services implements userService, taskService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;
    private TaskRepository taskRepository;

    @Autowired
    public services(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                    TokenRepository tokenRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
        this.taskRepository = taskRepository;
    }

    public User signUp(String username, String password) throws JsonProcessingException {

        User user = new User();
        user.setUsername(username);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));

        User user1 = userRepository.save(user);

        return user1;
    }

    public Token login(String username, String password) throws UsernameNotFoundException, PasswordNotFoundException {

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User does not exist");
        }

        User user = optionalUser.get();

        if(!bCryptPasswordEncoder.matches(password, user.getHashedPassword())){
            throw new PasswordNotFoundException("Password does not match");
        }
        Token token = getToken(user);

        // TODO 1: Change the above token to a JWT Token

        Token savedToken = tokenRepository.save(token);

        return savedToken;
    }
    private static Token getToken(User user) {
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plus(30, ChronoUnit.DAYS);

        // Convert LocalDate to Date
        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Token token = new Token();
        token.setUser(user);
        token.setExpiryAt(expiryDate);
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        return token;
    }


    @Override
    public Task createTask(Task task) throws TaskAlreadyExistsException {
       Optional<Task> optionalTask = taskRepository.findById(task.getId());
       if (!optionalTask.isEmpty()){
           throw new TaskAlreadyExistsException("Task already exists");
       }
        return taskRepository.save(task);
    }

    @Override
    public Page<Task> getTasks(int page, int size) throws TaskNotFoundException {

        Pageable pageable = PageRequest.of(page, size);
        return taskRepository.findAll(pageable);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()){
            return null;
        }

        Task existingTask = optionalTask.get();

        if(task.getTitle() != null){
            existingTask.setTitle(task.getTitle());
        }
        if (task.getDescription() != null){
            existingTask.setDescription(task.getDescription());
        }
        if (task.getDueDate() != null){
            existingTask.setDueDate(task.getDueDate());
        }
        if (task.getPriority() != null){
            existingTask.setPriority(task.getPriority());
        }
        return taskRepository.save(optionalTask.get());
    }

    @Override
    public void deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()){
            return;
        }
        taskRepository.deleteById(id);
    }


}
