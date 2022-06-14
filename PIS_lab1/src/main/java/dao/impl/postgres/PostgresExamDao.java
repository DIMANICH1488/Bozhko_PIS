package dao.impl.postgres;

import dao.ConvertService;
import dao.ExamDao;
import entities.dto.ExamDTO;
import exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record PostgresExamDao(Connection connection) implements ExamDao {
    private static final int SUCCESSFUL_UPDATE = 1;

    private String getSelectAllQuery() {
        return "SELECT * FROM exam;";
    }

    private String getSelectByIdQuery() {
        return "SELECT * FROM exam WHERE id = ?;";
    }

    private String getCreateQuery() {
        return "INSERT INTO exam (name,date,examiner_id) VALUES (?,?,?);";
    }

    private String getUpdateQuery() {
        return "UPDATE exam SET name = ?, date = ?, examiner_id = ? WHERE id = ?;";
    }

    private String getDeleteQuery() {
        return "DELETE FROM exam WHERE id = ?;";
    }

    private String getSelectProfessorsExamQuery() {
        return "SELECT * FROM exam WHERE professor_id = ?;";
    }

    private ExamDTO readObject(ResultSet resultSet) throws SQLException {
        return ConvertService.getExam(resultSet);
    }

    private List<ExamDTO> readObjects(ResultSet resultSet) throws SQLException {
        List<ExamDTO> objects = new ArrayList<>();

        while (resultSet.next()) {
            ExamDTO object = readObject(resultSet);
            objects.add(object);
        }
        return objects;
    }

    @Override
    public List<ExamDTO> getProfessorExams(int professorId) throws SQLException {
        String query = getSelectProfessorsExamQuery();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, professorId);
        ResultSet resultSet = statement.executeQuery();

        return readObjects(resultSet);
    }

    @Override
    public List<ExamDTO> getAll() throws SQLException {
        String query = getSelectAllQuery();

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        return readObjects(resultSet);
    }

    @Override
    public Optional<ExamDTO> get(Integer id) throws SQLException {
        String query = getSelectByIdQuery();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            ExamDTO object = readObject(resultSet);
            return Optional.of(object);
        } else
            return Optional.empty();
    }

    @Override
    public void save(ExamDTO examDTO) throws SQLException, DaoException {
        String query = getCreateQuery();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, examDTO.getName());
        statement.setTimestamp(2, examDTO.getDate());
        statement.setInt(3, examDTO.getProfessorId());

        if (statement.executeUpdate() < SUCCESSFUL_UPDATE)
            throw new DaoException("Can't create the object");
    }

    @Override
    public void update(ExamDTO examDTO) throws SQLException, DaoException {
        String query = getUpdateQuery();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, examDTO.getName());
        statement.setTimestamp(2, examDTO.getDate());
        statement.setInt(3, examDTO.getProfessorId());
        statement.setInt(4, examDTO.getId());

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
