package factory;

import dao.*;
import enums.DB;
import exception.UnimplementedDB;

public abstract class DaoFactory {
    public static DaoFactory getDaoFactory(DB db) throws UnimplementedDB {
        switch (db) {
            case PostgreSQL -> {
                return new PostgreSQLFactory();
            }
            case MySQL, MongoDB -> {
                throw new UnimplementedDB();
            }
            default -> {
                return null;
            }
        }
    }

    public abstract EnrollmentDao getEnrollmentDao();

    public abstract ExamDao getExamDao();

    public abstract ProfessorDao getProfessorDao();

    public abstract ExamResultsDao getExamResultDao();

    public abstract SpecializationDao getSpecializationDao();

    public abstract StudentDao getStudentDao();
}
