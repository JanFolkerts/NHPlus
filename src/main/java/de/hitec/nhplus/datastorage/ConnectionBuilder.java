package de.hitec.nhplus.datastorage;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The <code>ConnectionBuilder</code> manages a singleton connection to the SQLite database.
 * <p>
 * It provides thread-safe methods to open and close the connection, ensuring that
 * only one connection instance exists throughout the application lifecycle.
 * </p>
 * <p>
 * The SQLite connection is configured to enforce foreign key constraints.
 * </p>
 */
public class ConnectionBuilder {

    /**
     * The database filename.
     */
    private static final String DB_NAME = "nursingHome.db";
    /**
     * The JDBC URL to connect to the SQLite database.
     */
    private static final String URL = "jdbc:sqlite:db/" + DB_NAME;
    /**
     * Singleton instance of the database connection.
     */
    private static Connection connection;


    /**
     * Returns the singleton connection instance.
     * <p>
     * If no connection exists, a new one will be created with foreign key enforcement enabled.
     * This method is thread-safe.
     * </p>
     *
     * @return the active database connection
     */
    synchronized public static Connection getConnection() {
        try {
            if (ConnectionBuilder.connection == null) {
                SQLiteConfig configuration = new SQLiteConfig();
                configuration.enforceForeignKeys(true);
                ConnectionBuilder.connection = DriverManager.getConnection(URL, configuration.toProperties());
            }
        } catch (SQLException exception) {
            System.out.println("Verbindung zur Datenbank konnte nicht aufgebaut werden!");
            exception.printStackTrace();
        }
        return ConnectionBuilder.connection;
    }

    /**
     * Closes the current database connection if it exists.
     * <p>
     * After closing, the connection instance is set to null.
     * This method is thread-safe.
     * </p>
     */
    synchronized public static void closeConnection() {
        try {
            if (ConnectionBuilder.connection != null) {
                ConnectionBuilder.connection.close();
                ConnectionBuilder.connection = null;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
