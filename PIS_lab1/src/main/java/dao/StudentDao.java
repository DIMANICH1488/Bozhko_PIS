package dao;

import entities.Professor;
import entities.Student;

import java.sql.SQLException;
import java.util.Optional;

public interface StudentDao extends CRUDDao<Student, Integer> {
    Optional<Student> get(String email) throws SQLException;
}
