package services;

import dao.ExamDao;
import dao.ExamResultsDao;
import dao.ProfessorDao;
import dao.StudentDao;
import entities.Exam;
import entities.ExamResult;
import entities.Professor;
import entities.Student;
import entities.dto.ExamDTO;
import entities.dto.ExamResultDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class TransformService {
    private static final Logger LOGGER = Logger.getLogger(TransformService.class.getName());
    private final ExamDao examDao;
    private final ProfessorDao professorDao;
    private final StudentDao studentDao;

    public TransformService(ExamDao examDao, ProfessorDao professorDao, StudentDao studentDao) {
        this.examDao = examDao;
        this.professorDao = professorDao;
        this.studentDao = studentDao;
    }

    public Exam fromExamDTO(ExamDTO dto) {
        Professor professor = Professor.builder().build();

        try {
            Optional<Professor> optionalProfessor = professorDao.get(dto.getProfessorId());
            if (optionalProfessor.isPresent())
                professor = optionalProfessor.get();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }

        return Exam.builder()
                .id(dto.getId())
                .name(dto.getName())
                .date(dto.getDate())
                .professor(professor)
                .build();
    }

    public List<Exam> fromExamDTOs(List<ExamDTO> dtos) {
        List<Exam> exams = new ArrayList<>();

        for (ExamDTO dto : dtos) {
            Exam exam = fromExamDTO(dto);
            exams.add(exam);
        }
        return exams;
    }

    public ExamResult fromExamResultDTO(ExamResultDTO resultDTO) {
        Student student = Student.builder().build();
        Exam exam = Exam.builder().build();

        try {
            Optional<Student> optionalStudent = studentDao.get(resultDTO.getId().getStudentId());
            Optional<ExamDTO> optionalExamDTO = examDao.get(resultDTO.getId().getExamId());

            if (optionalStudent.isPresent())
                student = optionalStudent.get();

            if (optionalExamDTO.isPresent())
                exam = fromExamDTO(optionalExamDTO.get());
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }

        return ExamResult.builder()
                .student(student)
                .exam(exam)
                .grade(resultDTO.getGrade())
                .build();
    }

    public List<ExamResult> fromExamResultDTOs(List<ExamResultDTO> resultDTO) {
        List<ExamResult> results = new ArrayList<>();

        for (ExamResultDTO result : resultDTO) {
            ExamResult examResult = fromExamResultDTO(result);
            results.add(examResult);
        }
        return results;
    }
}
