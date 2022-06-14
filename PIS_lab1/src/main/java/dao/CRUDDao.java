package dao;

import exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CRUDDao<T, K> {
    List<T> getAll() throws SQLException;

    Optional<T> get(K id) throws SQLException;

    void save(T t) throws SQLException, DaoException;

    void update(T t) throws SQLException, DaoException;

    void delete(K id) throws SQLException, DaoException;
}
