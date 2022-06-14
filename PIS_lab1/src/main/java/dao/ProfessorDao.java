package dao;

import entities.Professor;

import java.sql.SQLException;
import java.util.Optional;

public interface ProfessorDao extends CRUDDao<Professor, Integer> {
    Optional<Professor> get(String email) throws SQLException;
}
