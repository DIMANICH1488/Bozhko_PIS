package services;

import dao.StudentDao;
import entities.Student;
import exception.UserNotFoundException;

import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;

public class StudentService {
    private static final Logger LOGGER = Logger.getLogger(StudentService.class.getName());
    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public Student getByEmail(String email) throws UserNotFoundException {
        try {
            Optional<Student> student = studentDao.get(email);

            if (student.isPresent())
                return student.get();
            else
                throw new UserNotFoundException();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }

        return null;
    }
}
