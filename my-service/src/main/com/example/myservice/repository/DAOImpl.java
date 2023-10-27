package example.myservice.repository;

import example.myservice.config.ConfigJDBC;
import example.myservice.model.CourseAndStudentDTO;
import example.myservice.model.CoursesDTO;
import example.myservice.model.StudentDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOImpl implements DAO {
    private final ConfigJDBC configJDBC = new ConfigJDBC();

    @Override
    public List<StudentDTO> getAllStudent() {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        String query = "SELECT * FROM sv.students";
        try (PreparedStatement preparedStatement = configJDBC.connect().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setId(resultSet.getInt("id"));
                studentDTO.setFirstName(resultSet.getString("first_name"));
                studentDTO.setMiddleName(resultSet.getString("middle_name"));
                studentDTO.setLastName(resultSet.getString("last_name"));
                studentDTOList.add(studentDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentDTOList;
    }

    @Override
    public void addStudent(StudentDTO studentDTO) {
        String query = "INSERT INTO sv.students (first_name, middle_name, last_name) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = configJDBC.connect().prepareStatement(query)) {
            preparedStatement.setString(1, studentDTO.getFirstName());
            preparedStatement.setString(2, studentDTO.getMiddleName());
            preparedStatement.setString(3, studentDTO.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CourseAndStudentDTO> getAllCourses() {
        String query = "SELECT course_id, name_course, student_id, first_name, middle_name, last_name\n" +
                       "FROM sv.student_courses sc\n" +
                       "JOIN sv.students st ON sc.student_id = st.id\n" +
                       "JOIN sv.courses cs ON sc.course_id = cs.id";
        List<CourseAndStudentDTO> courseAndStudentDTOS = new ArrayList<>();
        try (PreparedStatement preparedStatement = configJDBC.connect().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setId(resultSet.getInt("student_id"));
                studentDTO.setFirstName(resultSet.getString("first_name"));
                studentDTO.setMiddleName(resultSet.getString("middle_name"));
                studentDTO.setLastName(resultSet.getString("last_name"));

                CoursesDTO coursesDTO = new CoursesDTO();
                coursesDTO.setId(resultSet.getInt("course_id"));
                coursesDTO.setCourses(resultSet.getString("name_course"));

                CourseAndStudentDTO courseAndStudentDTO = new CourseAndStudentDTO();
                courseAndStudentDTO.setCoursesDTO(coursesDTO);
                courseAndStudentDTO.setStudentDTO(studentDTO);

                courseAndStudentDTOS.add(courseAndStudentDTO);

            }
            return courseAndStudentDTOS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CourseAndStudentDTO> getWhereCourse(String course) {
        String query = "SELECT distinct course_id, name_course, student_id, first_name, middle_name, last_name\n" +
                       "FROM sv.student_courses sc\n" +
                       "JOIN sv.students st ON sc.student_id = st.id\n" +
                       "JOIN sv.courses cs ON sc.course_id = cs.id\n" +
                       "where name_course = ?";
        List<CourseAndStudentDTO> courseAndStudentDTOS = new ArrayList<>();
        try (PreparedStatement preparedStatement = configJDBC.connect().prepareStatement(query)) {
            preparedStatement.setString(1, course);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setId(resultSet.getInt("student_id"));
                studentDTO.setFirstName(resultSet.getString("first_name"));
                studentDTO.setMiddleName(resultSet.getString("middle_name"));
                studentDTO.setLastName(resultSet.getString("last_name"));

                CoursesDTO coursesDTO = new CoursesDTO();
                coursesDTO.setId(resultSet.getInt("course_id"));
                coursesDTO.setCourses(resultSet.getString("name_course"));

                CourseAndStudentDTO courseAndStudentDTO = new CourseAndStudentDTO();
                courseAndStudentDTO.setCoursesDTO(coursesDTO);
                courseAndStudentDTO.setStudentDTO(studentDTO);
                courseAndStudentDTOS.add(courseAndStudentDTO);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return courseAndStudentDTOS;
    }
}
