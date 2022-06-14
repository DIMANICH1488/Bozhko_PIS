package dao.impl.postgres;

import dao.ConvertService;
import dao.ExamResultsDao;
import entities.dto.ExamResultDTO;
import entities.dto.ExamResultKey;
import exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record PostgresExamResultDao(Connection connection) implements ExamResultsDao {
    private static final int SUCCESSFUL_UPDATE = 1;

    private String getSelectAllQuery() {
        return "SELECT * FROM exam_result;";
    }

    private String getSelectByIdQuery() {
        return "SELECT * FROM exam_result WHERE (exam_id = ?) AND (student_id = ?);";
    }

    private String getCreateQuery() {
        return "INSERT INTO exam_result (exam_id,student_id,grade) VALUES (?,?,?);";
    }

    private String getUpdateQuery() {
        return "UPDATE exam_result SET exam_id = ?, student_id = ?, grade = ? WHERE" +
                " (exam_id = ?) AND (student_id = ?);";
    }

    private String getDeleteQuery() {
        return "DELETE FROM exam_result WHERE (exam_id = ?) AND (student_id = ?);";
    }

    private String getStudentExamResults() {
        return "SELECT * FROM exam_result WHERE student_id = ?;";
    }

    private ExamResultDTO readObject(ResultSet resultSet) throws SQLException {
        return ConvertService.getExamResult(resultSet);
    }

    private List<ExamResultDTO> readObjects(ResultSet resultSet) throws SQLException {
        List<ExamResultDTO> objects = new ArrayList<>();

        while (resultSet.next()) {
            ExamResultDTO object = readObject(resultSet);
            objects.add(object);
        }
        return objects;
    }

    @Override
    public List<ExamResultDTO> getStudentExamResults(int studentId) throws SQLException {
        String query = getStudentExamResults();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, studentId);
        ResultSet resultSet = statement.executeQuery();

        return readObjects(resultSet);
    }

    @Override
    public List<ExamResultDTO> getAll() throws SQLException {
        String query = getSelectAllQuery();

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        return readObjects(resultSet);
    }

    @Override
    public Optional<ExamResultDTO> get(ExamResultKey id) throws SQLException {
        String query = getSelectByIdQuery();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id.getExamId());
        statement.setInt(2, id.getStudentId());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            ExamResultDTO object = readObject(resultSet);
            return Optional.of(object);
        } else
            return Optional.empty();
    }

    @Override
    public void save(ExamResultDTO resultDTO) throws SQLException, DaoException {
        String query = getCreateQuery();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, resultDTO.getId().getExamId());
        statement.setInt(2, resultDTO.getId().getStudentId());
        statement.setInt(3, resultDTO.getGrade());

        if (statement.executeUpdate() < SUCCESSFUL_UPDATE)
            throw new DaoException("Can't create the object");
    }

    @Override
    public void update(ExamResultDTO resultDTO) throws SQLException, DaoException {
        String query = getUpdateQuery();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, resultDTO.getId().getExamId());
        statement.setInt(2, resultDTO.getId().getStudentId());
        statement.setInt(3, resultDTO.getGrade());
        statement.setInt(4, resultDTO.getId().getExamId());
        statement.setInt(5, resultDTO.getId().getStudentId());

        if (statement.executeUpdate() < SUCCESSFUL_UPDATE)
            throw new DaoException("Can't update the object");
    }

    @Override
    public void delete(ExamResultKey id) throws SQLException, DaoException {
        String query = getDeleteQuery();
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, id.getExamId());
        statement.setInt(2, id.getStudentId());
        if (statement.executeUpdate() < SUCCESSFUL_UPDATE)
            throw new DaoException("Can't delete the object");
    }
}
