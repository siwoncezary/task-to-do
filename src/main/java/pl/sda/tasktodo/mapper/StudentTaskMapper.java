package pl.sda.tasktodo.mapper;

import pl.sda.tasktodo.dto.StudentTaskDto;
import pl.sda.tasktodo.entity.StudentTask;

public class StudentTaskMapper {
    public static StudentTaskDto toDto(StudentTask task){
        return StudentTaskDto.builder()
                .content(task.getContent())
                .id(task.getId())
                .description(task.getTask().getDescription())
                .name(task.getTask().getName())
                .build();
    }
}
