package services;

import dao.ProfessorDao;
import entities.Professor;
import exception.UserNotFoundException;

import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;

public class ProfessorService {
    private static final Logger LOGGER = Logger.getLogger(ProfessorService.class.getName());
    private final ProfessorDao professorDao;

    public ProfessorService(ProfessorDao professorDao) {
        this.professorDao = professorDao;
    }

    public Professor getByEmail(String email) throws UserNotFoundException {
        try {
            Optional<Professor> professor = professorDao.get(email);

            if (professor.isPresent())
                return professor.get();
            else
                throw new UserNotFoundException();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }

        return null;
    }
}
