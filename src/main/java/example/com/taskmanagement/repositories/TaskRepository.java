package example.com.taskmanagement.repositories;

import example.com.taskmanagement.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task save(Task task);

    Optional<Task> findById(Long id);

    void deleteById(Long id);
}
