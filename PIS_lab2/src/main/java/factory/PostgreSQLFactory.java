package factory;

import dao.*;
import dao.impl.postgres.*;
import db.ConnectionPool;

import java.sql.Connection;

public class PostgreSQLFactory extends DaoFactory {
    private final Connection connection;

    public PostgreSQLFactory() {
        connection = ConnectionPool.getInstance().getConnection();
    }

    @Override
    public EnrollmentDao getEnrollmentDao() {
        return new PostgresEnrollmentDao(connection);
    }

    @Override
    public ExamDao getExamDao() {
        return new PostgresExamDao(connection);
    }

    @Override
    public ProfessorDao getProfessorDao() {
        return new PostgresProfessorDao(connection);
    }

    @Override
    public ExamResultsDao getExamResultDao() {
        return new PostgresExamResultDao(connection);
    }

    @Override
    public SpecializationDao getSpecializationDao() {
        return new PostgresSpecializationDao(connection);
    }

    @Override
    public StudentDao getStudentDao() {
        return new PostgresStudentDao(connection);
    }
}
