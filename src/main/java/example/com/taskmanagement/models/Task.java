package example.com.taskmanagement.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Task extends BaseModel {

    private String title;
    private String description;
    private String status;
    private String priority;
    private String dueDate;
}
