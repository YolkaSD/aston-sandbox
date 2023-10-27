package example.myservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.myservice.model.CourseAndStudentDTO;
import example.myservice.service.Service;
import example.myservice.service.ServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/courses/get_student", "/courses/add-student_in_course"})
public class CoursesController extends HttpServlet {

    private final Service service = new ServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        List<CourseAndStudentDTO> courseAndStudentDTOS;
        if (servletPath.equals("/courses/get_student")) {
            String course = req.getParameter("course");
            if (course == null) {
                courseAndStudentDTOS = service.getAllCourses();
            } else {
                courseAndStudentDTOS = service.getWhereCourse(course);
            }

            resp.getWriter().write(new ObjectMapper().writeValueAsString(courseAndStudentDTOS));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
