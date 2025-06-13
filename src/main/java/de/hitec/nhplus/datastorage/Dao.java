package de.hitec.nhplus.datastorage;

import java.sql.SQLException;
import java.util.List;

/**
 * The <code>Dao</code> interface defines the basic CRUD operations for all
 * data access objects (DAOs) in the application.
 * <p>
 * It is a generic interface that can be used with any type <code>T</code>
 * representing a persistent entity.
 * </p>
 *
 * @param <T> the type of the entity this DAO handles
 */
public interface Dao<T> {
    /**
     * Persists a new entity of type <code>T</code> in the data source.
     *
     * @param t the entity to be created
     * @throws SQLException if a database access error occurs
     */
    void create(T t) throws SQLException;

    /**
     * Reads a single entity of type <code>T</code> from the data source by its unique key.
     *
     * @param key the identifier of the entity
     * @return the entity matching the given key
     * @throws SQLException if a database access error occurs
     */
    T read(long key) throws SQLException;

    /**
     * Reads all entities of type <code>T</code> from the data source.
     *
     * @return a list containing all persisted entities
     * @throws SQLException if a database access error occurs
     */
    List<T> readAll() throws SQLException;

    /**
     * Updates the state of an existing entity in the data source.
     *
     * @param t the entity with updated values
     * @throws SQLException if a database access error occurs
     */
    void update(T t) throws SQLException;

    /**
     * Deletes the entity identified by the given key from the data source.
     *
     * @param key the identifier of the entity to be deleted
     * @throws SQLException if a database access error occurs
     */

    void deleteById(long key) throws SQLException;
}
