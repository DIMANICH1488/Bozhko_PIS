package dao.impl.postgres;

import dao.ConvertService;
import dao.ProfessorDao;
import entities.Professor;
import exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record PostgresProfessorDao(Connection connection) implements ProfessorDao {
    private static final int SUCCESSFUL_UPDATE = 1;

    private String getSelectAllQuery() {
        return "SELECT * FROM professor;";
    }

    private String getSelectByIdQuery() {
        return "SELECT * FROM professor WHERE id = ?;";
    }

    private String getSelectByEmailQuery() {
        return "SELECT * FROM professor WHERE email = ?;";
    }

    private String getCreateQuery() {
        return "INSERT INTO professor (first_name,last_name,email,password) VALUES (?,?,?,?);";
    }

    private String getUpdateQuery() {
        return "UPDATE professor SET first_name = ?, last_name = ?, email = ?, password = ? WHERE id = ?;";
    }

    private String getDeleteQuery() {
        return "DELETE FROM professor WHERE id = ?;";
    }

    private Professor readObject(ResultSet resultSet) throws SQLException {
        return ConvertService.getExaminer(resultSet);
    }

    private List<Professor> readObjects(ResultSet resultSet) throws SQLException {
        List<Professor> objects = new ArrayList<>();

        while (resultSet.next()) {
            Professor object = readObject(resultSet);
            objects.add(object);
        }
        return objects;
    }

    @Override
    public Optional<Professor> get(String email) throws SQLException {
        String query = getSelectByEmailQuery();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Professor object = readObject(resultSet);
            return Optional.of(object);
        } else
            return Optional.empty();
    }

    @Override
    public List<Professor> getAll() throws SQLException {
        String query = getSelectAllQuery();

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        return readObjects(resultSet);
    }

    @Override
    public Optional<Professor> get(Integer id) throws SQLException {
        String query = getSelectByIdQuery();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Professor object = readObject(resultSet);
            return Optional.of(object);
        } else
            return Optional.empty();
    }

    @Override
    public void save(Professor professor) throws SQLException, DaoException {
        String query = getCreateQuery();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, professor.getFirstName());
        statement.setString(2, professor.getLastName());
        statement.setString(3, professor.getEmail());
        statement.setString(4, professor.getPassword());

        if (statement.executeUpdate() < SUCCESSFUL_UPDATE)
            throw new DaoException("Can't create the object");
    }

    @Override
    public void update(Professor professor) throws SQLException, DaoException {
        String query = getUpdateQuery();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, professor.getFirstName());
        statement.setString(2, professor.getLastName());
        statement.setString(3, professor.getEmail());
        statement.setString(4, professor.getPassword());
        statement.setInt(5, professor.getId());

        if (statement.executeUpdate() < SUCCESSFUL_UPDATE)
            throw new DaoException("Can't update the object");
    }

    @Override
    public void delete(Integer id) throws SQLException, DaoException {
        String query = getDeleteQuery();
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, id);
        if (statement.executeUpdate() < SUCCESSFUL_UPDATE)
            throw new DaoException("Can't delete the object");
    }
}
