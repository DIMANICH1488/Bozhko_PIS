import dao.ExamDao;
import dao.StudentDao;
import entities.dto.ExamDTO;
import entities.Student;
import enums.DB;
import exception.UnimplementedDB;
import factory.DaoFactory;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String args[]) throws UnimplementedDB, SQLException {
        DaoFactory factory = DaoFactory.getDaoFactory(DB.PostgreSQL);
        StudentDao studentDao = factory.getStudentDao();
        List<Student> students = studentDao.getAll();

        System.out.println("Get all students:");

        for(Student student:students){
            System.out.println(String.format("Student with %d, name -> %s, surname -> %s",student.getId(),
                    student.getFirstName(), student.getLastName()));
        }

        ExamDao examDao = factory.getExamDao();
        List<ExamDTO> examDTOS = examDao.getAll();

        System.out.println("Get all examDTOS:");

        for(ExamDTO examDTO : examDTOS){
            System.out.println(String.format("Student with %d, name -> %s, surname -> %s", examDTO.getId(),
                    examDTO.getName(), examDTO.getDate()));
        }
    }
}
