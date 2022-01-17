package pl.sda.tasktodo.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class StudentTaskDto {
    private long id;
    private String name;
    @Length(min=5)
    private String content;

    private String description;
}
