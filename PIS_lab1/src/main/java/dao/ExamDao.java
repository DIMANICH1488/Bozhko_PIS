package dao;

import entities.dto.ExamDTO;

import java.sql.SQLException;
import java.util.List;

public interface ExamDao extends CRUDDao<ExamDTO, Integer>{
    List<ExamDTO> getProfessorExams(int id) throws SQLException;
}


