package dao.impl.postgres;

import dao.ConvertService;
import dao.StudentDao;
import entities.Student;
import exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public record PostgresStudentDao(Connection connection) implements StudentDao {
    private static final int SUCCESSFUL_UPDATE = 1;

    private String getSelectAllQuery() {
        return "SELECT * FROM student;";
    }

    private String getSelectByIdQuery() {
        return "SELECT * FROM student WHERE id = ?;";
    }

    private String getSelectByEmailQuery() {
        return "SELECT * FROM student WHERE email = ?;";
    }

    private String getCreateQuery() {
        return "INSERT INTO student (first_name,last_name,email,password) VALUES (?,?,?,?);";
    }

    private String getUpdateQuery() {
        return "UPDATE student SET first_name = ?, last_name = ?, email = ?, password = ? WHERE id = ?;";
    }

    private String getDeleteQuery() {
        return "DELETE FROM student WHERE id = ?;";
    }

    private Student readObject(ResultSet resultSet) throws SQLException {
        return ConvertService.getStudent(resultSet);
    }

    private List<Student> readObjects(ResultSet resultSet) throws SQLException {
        List<Student> objects = new ArrayList<>();

        while (resultSet.next()) {
            Student object = readObject(resultSet);
            objects.add(object);
        }
        return objects;
    }

    private void setObjectStatement(PreparedStatement statement, Student object) throws SQLException {
        statement.setString(1, object.getFirstName());
        statement.setString(2, object.getLastName());
        statement.setString(3, object.getEmail());
        statement.setString(4, object.getPassword());
        statement.setInt(5, object.getId());
    }

    private void setIdStatement(PreparedStatement statement, Integer key) throws SQLException {
        statement.setInt(1, key);
    }

    @Override
    public Optional<Student> get(String email) throws SQLException {
        String query = getSelectByEmailQuery();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Student object = readObject(resultSet);
            return Optional.of(object);
        } else
            return Optional.empty();
    }

    @Override
    public List<Student> getAll() throws SQLException {
        String query = getSelectAllQuery();

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        return readObjects(resultSet);
    }

    @Override
    public Optional<Student> get(Integer id) throws SQLException {
        String query = getSelectByIdQuery();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Student object = readObject(resultSet);
            return Optional.of(object);
        } else
            return Optional.empty();
    }

    @Override
    public void save(Student student) throws SQLException, DaoException {
        String query = getCreateQuery();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setString(3, student.getEmail());
        statement.setString(4, student.getPassword());

        if (statement.executeUpdate() < SUCCESSFUL_UPDATE)
            throw new DaoException("Can't create the object");
    }

    @Override
    public void update(Student student) throws SQLException, DaoException {
        String query = getUpdateQuery();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setString(3, student.getEmail());
        statement.setString(4, student.getPassword());
        statement.setInt(5, student.getId());

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
