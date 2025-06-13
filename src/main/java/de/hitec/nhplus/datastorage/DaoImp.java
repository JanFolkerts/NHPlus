package de.hitec.nhplus.datastorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The <code>DaoImp</code> class is an abstract base class that implements the common logic for all DAO classes
 * that interact with a database using a JDBC <code>Connection</code>.
 * <p>
 * It defines the generic structure for CRUD operations and delegates the specifics to subclasses through
 * abstract methods that must be implemented for the concrete entity type.
 *
 * @param <T> The type of the data entity this DAO handles
 */
public abstract class DaoImp<T> implements Dao<T> {

    /**
     * The JDBC database connection used by all subclasses.
     */
    protected Connection connection;

    /**
     * Constructs a new DAO implementation with the given database connection.
     *
     * @param connection the JDBC connection to use
     */
    public DaoImp(Connection connection) {
        this.connection = connection;
    }

    /**
     * Executes the SQL statement to insert a new record of type <code>T</code> into the database.
     *
     * @param t the entity to be inserted
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void create(T t) throws SQLException {
        getCreateStatement(t).executeUpdate();
    }

    /**
     * Reads an entity of type <code>T</code> from the database by its key (usually the ID).
     *
     * @param key the ID of the entity to be read
     * @return the entity found or <code>null</code> if no record was found
     * @throws SQLException if a database access error occurs
     */
    @Override
    public T read(long key) throws SQLException {
        T object = null;
        ResultSet result = getReadByIDStatement(key).executeQuery();
        if (result.next()) {
            object = getInstanceFromResultSet(result);
        }
        return object;
    }

    /**
     * Reads all entities of type <code>T</code> from the database.
     *
     * @return a list of all records
     * @throws SQLException if a database access error occurs
     */
    @Override
    public List<T> readAll() throws SQLException {
        return getListFromResultSet(getReadAllStatement().executeQuery());
    }

    /**
     * Updates an existing entity in the database.
     *
     * @param t the entity with updated data
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void update(T t) throws SQLException {
        getUpdateStatement(t).executeUpdate();
    }

    /**
     * Deletes an entity from the database by its ID.
     *
     * @param key the ID of the entity to be deleted
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void deleteById(long key) throws SQLException {
        getDeleteStatement(key).executeUpdate();
    }

    /**
     * Creates an object of type <code>T</code> from a given <code>ResultSet</code> row.
     *
     * @param set the result set pointing to a valid row
     * @return an object of type <code>T</code> built from the row data
     * @throws SQLException if accessing the result set fails
     */
    protected abstract T getInstanceFromResultSet(ResultSet set) throws SQLException;

    /**
     * Creates a list of <code>T</code> objects from a <code>ResultSet</code>.
     *
     * @param set the result set containing multiple rows
     * @return a list of objects created from the result set
     * @throws SQLException if accessing the result set fails
     */
    protected abstract ArrayList<T> getListFromResultSet(ResultSet set) throws SQLException;

    /**
     * Returns the SQL <code>PreparedStatement</code> to insert a new record.
     *
     * @param t the entity to be inserted
     * @return the prepared SQL statement
     */
    protected abstract PreparedStatement getCreateStatement(T t);

    /**
     * Returns the SQL <code>PreparedStatement</code> to read a record by its ID.
     *
     * @param key the ID of the entity to be read
     * @return the prepared SQL statement
     */
    protected abstract PreparedStatement getReadByIDStatement(long key);

    /**
     * Returns the SQL <code>PreparedStatement</code> to read all records.
     *
     * @return the prepared SQL statement
     */
    protected abstract PreparedStatement getReadAllStatement();

    /**
     * Returns the SQL <code>PreparedStatement</code> to update an existing record.
     *
     * @param t the entity with updated data
     * @return the prepared SQL statement
     */
    protected abstract PreparedStatement getUpdateStatement(T t);

    /**
     * Returns the SQL <code>PreparedStatement</code> to delete a record by ID.
     *
     * @param key the ID of the entity to be deleted
     * @return the prepared SQL statement
     */
    protected abstract PreparedStatement getDeleteStatement(long key);
}