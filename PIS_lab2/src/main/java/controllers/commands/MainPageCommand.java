package controllers.commands;

import controllers.CurrentDaoFactory;
import controllers.Navigation;
import entities.Professor;
import entities.Student;
import exception.UserNotFoundException;
import services.ProfessorService;
import services.StudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class MainPageCommand implements Command {
    private final ProfessorService professorService = new ProfessorService(CurrentDaoFactory.daoFactory.getProfessorDao());
    private final StudentService studentService = new StudentService(CurrentDaoFactory.daoFactory.getStudentDao());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String role = req.getAuthType();
        String userEmail = req.getRemoteUser();
        String name = "";
        Map<String, String> navbar = new HashMap<>();

        try {
            if (role.equals("professor")) {
                Professor professor = professorService.getByEmail(userEmail);
                name += String.format("Professor %s", professor.getLastName());
                navbar = Navigation.professorNavigationBar;
            } else {
                Student student = studentService.getByEmail(userEmail);
                name += String.format("%s %s", student.getFirstName(), student.getLastName());
                navbar = Navigation.studentNavigationBar;
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        req.setAttribute("name", name);
        req.setAttribute("navbar", navbar);

        return Navigation.MAIN_PAGE;
    }
}
