package dao.impl.postgres;

import dao.ConvertService;
import dao.EnrollmentDao;
import entities.dto.EnrollmentDTO;
import entities.dto.EnrollmentKey;
import exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record PostgresEnrollmentDao(Connection connection) implements EnrollmentDao {
    private static final int SUCCESSFUL_UPDATE = 1;

    private String getSelectAllQuery() {
        return "SELECT * FROM enrollment;";
    }

    private String getSelectByIdQuery() {
        return "SELECT * FROM enrollment WHERE (student_id = ?) AND (specialization_id = ?);";
    }

    private String getCreateQuery() {
        return "INSERT INTO enrollment (student_id,specialization_id,priority) VALUES (?,?,?);";
    }

    private String getUpdateQuery() {
        return "UPDATE enrollment SET student_id = ?, specialization_id = ?, priority = ? WHERE" +
                " (student_id = ?) AND (specialization_id = ?);";
    }

    private String getDeleteQuery() {
        return "DELETE FROM enrollment WHERE (student_id = ?) AND (specialization_id = ?);";
    }

    private EnrollmentDTO readObject(ResultSet resultSet) throws SQLException {
        return ConvertService.getEnrollment(resultSet);
    }

    private List<EnrollmentDTO> readObjects(ResultSet resultSet) throws SQLException {
        List<EnrollmentDTO> objects = new ArrayList<>();

        while (resultSet.next()) {
            EnrollmentDTO object = readObject(resultSet);
            objects.add(object);
        }
        return objects;
    }

    private void setObjectStatement(PreparedStatement statement, EnrollmentDTO object) throws SQLException {
        statement.setInt(1, object.getId().getStudentId());
        statement.setInt(2, object.getId().getSpecializationId());
        statement.setInt(3, object.getPriority());
        statement.setInt(4, object.getId().getStudentId());
        statement.setInt(5, object.getId().getSpecializationId());
    }

    private void setIdStatement(PreparedStatement statement, EnrollmentKey key) throws SQLException {
        statement.setInt(1, key.getStudentId());
        statement.setInt(2, key.getSpecializationId());
    }

    @Override
    public List<EnrollmentDTO> getAll() throws SQLException {
        String query = getSelectAllQuery();

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        return readObjects(resultSet);
    }

    @Override
    public Optional<EnrollmentDTO> get(EnrollmentKey id) throws SQLException {
        String query = getSelectByIdQuery();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id.getStudentId());
        statement.setInt(2, id.getSpecializationId());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            EnrollmentDTO object = readObject(resultSet);
            return Optional.of(object);
        } else
            return Optional.empty();
    }

    @Override
    public void save(EnrollmentDTO enrollmentDTO) throws SQLException, DaoException {
        String query = getCreateQuery();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, enrollmentDTO.getId().getStudentId());
        statement.setInt(2, enrollmentDTO.getId().getSpecializationId());
        statement.setInt(3, enrollmentDTO.getPriority());

        if (statement.executeUpdate() < SUCCESSFUL_UPDATE)
            throw new DaoException("Can't create the object");
    }

    @Override
    public void update(EnrollmentDTO enrollmentDTO) throws SQLException, DaoException {
        String query = getUpdateQuery();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, enrollmentDTO.getId().getStudentId());
        statement.setInt(2, enrollmentDTO.getId().getSpecializationId());
        statement.setInt(3, enrollmentDTO.getPriority());
        statement.setInt(4, enrollmentDTO.getId().getStudentId());
        statement.setInt(5, enrollmentDTO.getId().getSpecializationId());

        if (statement.executeUpdate() < SUCCESSFUL_UPDATE)
            throw new DaoException("Can't update the object");
    }

    @Override
    public void delete(EnrollmentKey id) throws SQLException, DaoException {
        String query = getDeleteQuery();
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, id.getStudentId());
        statement.setInt(2, id.getSpecializationId());
        if (statement.executeUpdate() < SUCCESSFUL_UPDATE)
            throw new DaoException("Can't delete the object");
    }
}