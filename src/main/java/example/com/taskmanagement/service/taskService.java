package example.com.taskmanagement.service;

import example.com.taskmanagement.exceptions.TaskAlreadyExistsException;
import example.com.taskmanagement.exceptions.TaskNotFoundException;
import example.com.taskmanagement.models.Task;

import java.util.List;

public interface taskService {

    Task createTask(Task task) throws TaskAlreadyExistsException;

    List<Task> getTasks() throws TaskNotFoundException;

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);
}
