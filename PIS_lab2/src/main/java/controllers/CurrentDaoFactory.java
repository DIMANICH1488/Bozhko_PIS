package controllers;

import enums.DB;
import exception.UnimplementedDB;
import factory.DaoFactory;
import lombok.Data;

@Data
public class CurrentDaoFactory {
    public static DaoFactory daoFactory;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(DB.PostgreSQL);
        } catch (UnimplementedDB e) {
            e.printStackTrace();
        }
    }
}
