package controllers;

import controllers.commands.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Servlet.class);
    private Map<String, Command> commandsMapping;
    private Map<String, Command> pagesMapping;

    @Override
    public void init() throws ServletException {
        super.init();
        commandsMapping = new HashMap<>();
        pagesMapping = new HashMap<>();
        commandsMapping.put("exams", new ExamCommand());
        commandsMapping.put("select-exam", new SelectExamCommand());
        commandsMapping.put("specialization", new SpecializationCommand());
        pagesMapping.put("/CollegeEntranceSystem/student", new MainPageCommand());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    private void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandPar = req.getParameter("command");
        Command command;

        if (commandPar != null)
            command = commandsMapping.get(commandPar);
        else
            command = pagesMapping.get(req.getRequestURI());

        String page = command.execute(req, resp);

        if (page.contains("redirect:")) {
            resp.sendRedirect(page.replace("redirect:/", ""));
        }
        else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        }
    }
}
