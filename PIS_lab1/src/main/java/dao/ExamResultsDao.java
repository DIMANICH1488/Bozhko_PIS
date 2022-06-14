package dao;

import entities.dto.ExamResultDTO;
import entities.dto.ExamResultKey;

import java.sql.SQLException;
import java.util.List;

public interface ExamResultsDao extends CRUDDao<ExamResultDTO, ExamResultKey> {
    List<ExamResultDTO> getStudentExamResults(int id) throws SQLException;
}
