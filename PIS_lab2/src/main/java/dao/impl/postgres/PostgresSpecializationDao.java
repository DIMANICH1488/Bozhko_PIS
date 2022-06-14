package dao.impl.postgres;

import dao.ConvertService;
import dao.SpecializationDao;
import entities.Specialization;
import exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record PostgresSpecializationDao(Connection connection) implements SpecializationDao {
    private static final int SUCCESSFUL_UPDATE = 1;

    private String getSelectAllQuery() {
        return "SELECT * FROM specialization;";
    }

    private String getSelectByIdQuery() {
        return "SELECT * FROM specialization WHERE id = ?;";
    }

    private String getCreateQuery() {
        return "INSERT INTO specialization (name,max_student_number) VALUES (?,?);";
    }

    private String getUpdateQuery() {
        return "UPDATE specialization SET name = ?, max_student_number = ? WHERE id = ?;";
    }

    private String getDeleteQuery() {
        return "DELETE FROM specialization WHERE id = ?;";
    }

    private Specialization readObject(ResultSet resultSet) throws SQLException {
        return ConvertService.getSpecialization(resultSet);
    }

    private List<Specialization> readObjects(ResultSet resultSet) throws SQLException {
        List<Specialization> objects = new ArrayList<>();

        while (resultSet.next()) {
            Specialization object = readObject(resultSet);
            objects.add(object);
        }
        return objects;
    }

    private void setObjectStatement(PreparedStatement statement, Specialization object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setInt(2, object.getMaxStudentNumber());
        statement.setInt(3, object.getId());
    }

    private void setIdStatement(PreparedStatement statement, Integer key) throws SQLException {
        statement.setInt(1, key);
    }

    @Override
    public List<Specialization> getAll() throws SQLException {
        String query = getSelectAllQuery();

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        return readObjects(resultSet);
    }

    @Override
    public Optional<Specialization> get(Integer id) throws SQLException {
        String query = getSelectByIdQuery();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Specialization object = readObject(resultSet);
            return Optional.of(object);
        } else
            return Optional.empty();
    }

    @Override
    public void save(Specialization specialization) throws SQLException, DaoException {
        String query = getCreateQuery();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, specialization.getName());
        statement.setInt(2, specialization.getMaxStudentNumber());

        if (statement.executeUpdate() < SUCCESSFUL_UPDATE)
            throw new DaoException("Can't create the object");
    }

    @Override
    public void update(Specialization specialization) throws SQLException, DaoException {
        String query = getUpdateQuery();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, specialization.getName());
        statement.setInt(2, specialization.getMaxStudentNumber());
        statement.setInt(3, specialization.getId());

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
