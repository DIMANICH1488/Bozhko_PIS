package controllers.commands;

import controllers.CurrentDaoFactory;
import controllers.Navigation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import services.ExamService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class SelectExamCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SelectExamCommand.class);
    private final ExamService examService = new ExamService(CurrentDaoFactory.daoFactory.getExamDao(),
            CurrentDaoFactory.daoFactory.getExamResultDao(),
            CurrentDaoFactory.daoFactory.getProfessorDao(),
            CurrentDaoFactory.daoFactory.getStudentDao());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer examId = Integer.valueOf(req.getParameter("exam-id"));
        String email = req.getRemoteUser();
        examService.saveStudentExam(email, examId);

        Map<String, String> navbar = Navigation.studentNavigationBar;
        req.setAttribute("navbar", navbar);

        return "redirect:/student?command=exams";
    }
}
