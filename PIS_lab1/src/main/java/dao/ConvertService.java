package dao;

import entities.Professor;
import entities.Specialization;
import entities.Student;
import entities.dto.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConvertService {
    public static ExamDTO getExam(ResultSet resultSet) throws SQLException {
        return ExamDTO.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .date(resultSet.getTimestamp("date"))
                .professorId(resultSet.getInt("professor_id"))
                .build();
    }

    public static Student getStudent(ResultSet resultSet) throws SQLException {
        return Student.builder()
                .id(resultSet.getInt("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .build();
    }

    public static Specialization getSpecialization(ResultSet resultSet) throws SQLException {
        return Specialization.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .maxStudentNumber(resultSet.getInt("max_student_number"))
                .build();
    }

    public static Professor getExaminer(ResultSet resultSet) throws SQLException {
        return Professor.builder()
                .id(resultSet.getInt("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .build();
    }

    public static ExamResultDTO getExamResult(ResultSet resultSet) throws SQLException {
        ExamResultKey key = ExamResultKey.builder()
                .examId(resultSet.getInt("exam_id"))
                .studentId(resultSet.getInt("student_id"))
                .build();

        return ExamResultDTO.builder()
                .id(key)
                .grade(resultSet.getInt("grade"))
                .build();
    }

    public static EnrollmentDTO getEnrollment(ResultSet resultSet) throws SQLException {
        EnrollmentKey key = EnrollmentKey.builder()
                .studentId(resultSet.getInt("student_id"))
                .specializationId(resultSet.getInt("specialization_id"))
                .build();

        return EnrollmentDTO.builder()
                .id(key)
                .priority(resultSet.getInt("priority"))
                .build();
    }

}
