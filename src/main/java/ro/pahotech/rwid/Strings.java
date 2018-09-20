package ro.pahotech.rwid;

public class Strings {
    private static final String APP_NAME = "RWIS Checker";
    public static final String APP_NAME_REPORTS = APP_NAME + " reports: ";
    //properties
    static final String ERROR_ARGS_NOT_PROVIDED = "WARNING: 'properties' file path not provided. Using current directory.";
    static final String ERROR_PROPERTY_NOT_FOUND = "WARNING: Could not read user defined property: ";
    static final String TABLE_NAME_CONDITIONS = "TABLE_NAME_CONDITIONS";
    static final String TABLE_NAME_SURFACE_CONDITIONS = "TABLE_NAME_SURFACE_CONDITIONS";
    static final String DEFAULT_TABLE_NAME_CONDITIONS = "conditions";
    static final String DEFAULT_TABLE_NAME_SURFACE_CONDITIONS = "surface_conditions";

    static final String DATABASE_URL = "DATABASE_URL";
    static final String DATABASE_USER = "DATABASE_USER";
    static final String DATABASE_PASSWORD = "DATABASE_PASSWORD";
    //  odap01-scan
//    static final String DEFAULT_DATABASE_URL = "jdbc:oracle:thin:@//ODAP01-SCAN:1521/odap01";
//    static final String DEFAULT_DATABASE_USER = "sensors";
//    static final String DEFAULT_DATABASE_PASSWORD = "s3ns()rs";
    //  virdev
    static final String DEFAULT_DATABASE_URL = "jdbc:oracle:thin:@10.10.10.6:1521:xe";
    static final String DEFAULT_DATABASE_USER = "virdev";
    static final String DEFAULT_DATABASE_PASSWORD = "co5dgGsoJ";

    static final String TRAC_DATABASE_URL = "TRAC_DATABASE_URL";
    static final String TRAC_DATABASE_USER = "TRAC_DATABASE_USER";
    static final String TRAC_DATABASE_PASSWORD = "TRAC_DATABASE_PASSWORD";

    static final String TRAC_DEFAULT_DATABASE_URL = "jdbc:oracle:thin:@odap01-scan:1521/odap01";
    static final String TRAC_DEFAULT_DATABASE_USER = "tmctools_dev";
    static final String TRAC_DEFAULT_DATABASE_PASSWORD = "D0cHWatsun";

    static final String FILE_PATH_PROPERTIES = "properties.properties";

    static final String DB_MAPPER_NAME = "default";
    static final String ERROR_FATAL_DATABASE_INITIALIZATION = "Could not initialize connection to the database!";

    static final String ERROR_ACTION_NOT_IMPLEMENTED = "An action for this table has not been implemented yet!";

    static final String TRAC_TABLE_NAME = "TRAC_TABLE_NAME";
    static final String TRAC_SEQUENCE_NAME = "TRAC_SEQUENCE_NAME";
    static final String DEFAULT_TRAC_TABLE_NAME = "tmctools_dev.TRAC_TASKS";
    static final String DEFAULT_TRAC_SEQUENCE_NAME = "tmctools_dev.TMCTOOLS_SEQ";
    static final String ERROR_TRAC_INSERT = "Trac insert has failed!";

    static final String ERROR_FATAL_ADD_MAIN_WATCHERS = "Could not successfully add watchers on targeted tables!";
}
