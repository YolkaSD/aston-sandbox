package example.myservice.service;

import example.myservice.model.CourseAndStudentDTO;
import example.myservice.model.StudentDTO;
import example.myservice.repository.DAO;
import example.myservice.repository.DAOImpl;
import java.util.List;


public class ServiceImpl implements Service {
    private final DAO dao = new DAOImpl();
    @Override
    public List<StudentDTO> getAllStudent() {
        return dao.getAllStudent();
    }

    @Override
    public void addStudent(StudentDTO studentDTO) {
        dao.addStudent(studentDTO);
    }

    @Override
    public List<CourseAndStudentDTO> getAllCourses() {
        return dao.getAllCourses();
    }

    @Override
    public List<CourseAndStudentDTO> getWhereCourse(String course) {
        return dao.getWhereCourse(course);
    }
}
