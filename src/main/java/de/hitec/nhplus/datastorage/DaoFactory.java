package de.hitec.nhplus.datastorage;

/**
 * The <code>DaoFactory</code> class is a singleton that provides access to the
 * different DAO implementations used for accessing the application's data sources.
 * <p>
 * This factory ensures a centralized and consistent way to create DAOs with
 * the correct database connection.
 * </p>
 */
public class DaoFactory {

    /**
     * The single instance of the <code>DaoFactory</code>.
     */
    private static DaoFactory instance;

    /**
     * Private constructor to prevent external instantiation.
     */
    private DaoFactory() {
    }

    /**
     * Returns the single instance of the <code>DaoFactory</code>.
     * If the instance does not exist, it will be created.
     *
     * @return the singleton instance of the factory
     */
    public static DaoFactory getDaoFactory() {
        if (DaoFactory.instance == null) {
            DaoFactory.instance = new DaoFactory();
        }
        return DaoFactory.instance;
    }

    /**
     * Creates a new instance of {@link TreatmentDao} using the current database connection.
     *
     * @return a new <code>TreatmentDao</code> instance
     */
    public TreatmentDao createTreatmentDao() {
        return new TreatmentDao(ConnectionBuilder.getConnection());
    }

    /**
     * Creates a new instance of {@link PatientDao} using the current database connection.
     *
     * @return a new <code>PatientDao</code> instance
     */
    public PatientDao createPatientDAO() {
        return new PatientDao(ConnectionBuilder.getConnection());
    }

    /**
     * Creates a new instance of {@link CaregiverDao} using the current database connection.
     *
     * @return a new <code>CaregiverDao</code> instance
     */
    public CaregiverDao createCaregiverDAO() {
        return new CaregiverDao(ConnectionBuilder.getConnection());
    }
}