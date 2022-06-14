package services;

import dao.SpecializationDao;
import entities.Specialization;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SpecializationService {
    private static final Logger LOGGER = Logger.getLogger(SpecializationService.class.getName());
    private final SpecializationDao specializationDao;

    public SpecializationService(SpecializationDao specializationDao) {
        this.specializationDao = specializationDao;
    }

    public List<Specialization> getAll() {
        try {
            return specializationDao.getAll();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            return new ArrayList<>();
        }
    }
}
