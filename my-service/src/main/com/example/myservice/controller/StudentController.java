package example.myservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.myservice.model.StudentDTO;
import example.myservice.service.Service;
import example.myservice.service.ServiceImpl;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/student/get", "/student/add"})
public class StudentController extends HttpServlet {

    private final Service service = new ServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        String servletPath = req.getServletPath();
        if (servletPath.equals("/student/get")) {
            List<StudentDTO> studentDTOList = service.getAllStudent();
            resp.getWriter().write(new ObjectMapper().writeValueAsString(studentDTOList));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String servletPath = req.getServletPath();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        if (servletPath.equals("/student/add")) {
            String firstName = req.getParameter("first_name");
            String middleName = req.getParameter("middle_name");
            String lastName = req.getParameter("last_name");
            if (firstName == null || middleName == null || lastName == null) return;
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setFirstName(firstName);
            studentDTO.setMiddleName(middleName);
            studentDTO.setLastName(lastName);
            service.addStudent(studentDTO);
        }
    }
}
