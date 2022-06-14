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
import entities.dto.ExamResultKey;
import exception.DaoException;
import exception.UserNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class ExamService {
    private static final Logger LOGGER = Logger.getLogger(ExamService.class.getName());
    private final ExamDao examDao;
    private final ExamResultsDao examResultsDao;
    private final ProfessorDao professorDao;
    private final StudentDao studentDao;
    private final TransformService transformService;

    public ExamService(ExamDao examDao, ExamResultsDao examResultsDao, ProfessorDao professorDao, StudentDao studentDao) {
        this.examDao = examDao;
        this.examResultsDao = examResultsDao;
        this.professorDao = professorDao;
        this.studentDao = studentDao;
        this.transformService = new TransformService(examDao, professorDao, studentDao);
    }

    public List<Exam> getProfessorsExams(String professorEmail) throws UserNotFoundException {
        try {
            Optional<Professor> professorOpt = professorDao.get(professorEmail);
            Professor professor;
            if (professorOpt.isPresent())
                professor = professorOpt.get();
            else
                throw new UserNotFoundException();

            List<ExamDTO> dtos = examDao.getProfessorExams(professor.getId());
            return transformService.fromExamDTOs(dtos);
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Exam> getAllExams() {
        try {
            List<ExamDTO> examDTOS = examDao.getAll();
            return transformService.fromExamDTOs(examDTOS);
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveStudentExam(String studentEmail, Integer examId) {
        Student student = null;
        try {
            Optional<Student> studentOptional = studentDao.get(studentEmail);

            if (studentOptional.isPresent())
                student = studentOptional.get();
            else
                throw new UserNotFoundException();

            ExamResultKey key = ExamResultKey.builder()
                    .examId(examId)
                    .studentId(student.getId())
                    .build();

            ExamResultDTO resultDTO = ExamResultDTO.builder()
                    .grade(0)
                    .id(key)
                    .build();

            examResultsDao.save(resultDTO);
        } catch (SQLException | UserNotFoundException | DaoException e) {
            LOGGER.severe(e.getMessage());
        }

    }

    public List<ExamResult> getStudentExams(String studentEmail) throws UserNotFoundException {
        Student student;
        try {
            Optional<Student> studentOptional = studentDao.get(studentEmail);

            if (studentOptional.isPresent())
                student = studentOptional.get();
            else
                throw new UserNotFoundException();

            List<ExamResultDTO> examResultDTOs = examResultsDao.getStudentExamResults(student.getId());
            return transformService.fromExamResultDTOs(examResultDTOs);
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            return new ArrayList<>();
        }
    }
}
