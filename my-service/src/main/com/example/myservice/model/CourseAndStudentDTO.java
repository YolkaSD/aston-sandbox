package example.myservice.model;

import lombok.Data;

@Data
public class CourseAndStudentDTO {
    private CoursesDTO coursesDTO;
    private StudentDTO studentDTO;
}
