package example.myservice.repository;

import example.myservice.model.CourseAndStudentDTO;
import example.myservice.model.StudentDTO;
import java.util.List;

public interface DAO {
    List<StudentDTO> getAllStudent();

    void addStudent(StudentDTO studentDTO);

    List<CourseAndStudentDTO> getAllCourses();

    List<CourseAndStudentDTO> getWhereCourse(String course);
}
