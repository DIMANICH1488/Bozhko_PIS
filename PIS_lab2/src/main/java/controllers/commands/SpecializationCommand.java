package controllers.commands;

import controllers.CurrentDaoFactory;
import controllers.Navigation;
import entities.Specialization;
import services.SpecializationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecializationCommand implements Command {
    private final SpecializationService specializationService
            = new SpecializationService(CurrentDaoFactory.daoFactory.getSpecializationDao());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String role = req.getAuthType();
        Map<String, String> navbar = new HashMap<>();
        String email = req.getRemoteUser();

        if (role.equals("professor"))
            navbar = Navigation.professorNavigationBar;
        else
            navbar = Navigation.studentNavigationBar;

        List<Specialization> specializations = specializationService.getAll();
        req.setAttribute("specializations", specializations);
        req.setAttribute("navbar", navbar);

        return Navigation.SPECIALIZATION_PAGE;
    }
}
