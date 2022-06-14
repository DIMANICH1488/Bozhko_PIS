package controllers.commands;

import controllers.CurrentDaoFactory;
import controllers.Navigation;
import entities.Exam;
import entities.ExamResult;
import exception.UserNotFoundException;
import services.ExamService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamCommand implements Command {
    private final ExamService examService = new ExamService(CurrentDaoFactory.daoFactory.getExamDao(),
            CurrentDaoFactory.daoFactory.getExamResultDao(),
            CurrentDaoFactory.daoFactory.getProfessorDao(),
            CurrentDaoFactory.daoFactory.getStudentDao());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String role = req.getAuthType();
        List<Exam> exams = new ArrayList<>();
        Map<String, String> navbar = new HashMap<>();
        String email = req.getRemoteUser();


        try {
            if (role.equals("professor")) {
                navbar = Navigation.professorNavigationBar;
                exams = examService.getProfessorsExams(email);
            } else {
                navbar = Navigation.studentNavigationBar;
                exams = examService.getAllExams();
                List<ExamResult> results = examService.getStudentExams(email);
                req.setAttribute("results", results);
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        req.setAttribute("exams", exams);
        req.setAttribute("navbar", navbar);

        return Navigation.EXAM_PAGE;
    }
}
