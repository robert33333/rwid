package ro.pahotech.rwid;

class Strings {
    //properties
    static final String ERROR_ARGS_NOT_PROVIDED = "WARNING: 'properties' file path not provided. Using current directory.";
    static final String ERROR_PROPERTY_NOT_FOUND = "WARNING: Could not read user defined property: ";
    static final String TABLE_NAME_CONDITIONS = "TABLE_NAME_CONDITIONS";
    static final String TABLE_NAME_SURFACE_CONDITIONS = "TABLE_NAME_SURFACE_CONDITIONS";
    static final String DEFAULT_TABLE_NAME_CONDITIONS = "CONDITIONS";
    static final String DEFAULT_TABLE_NAME_SURFACE_CONDITIONS = "SURFACE_CONDITIONS";

    static final String DATABASE_URL = "DATABASE_URL";
    static final String DATABASE_USER = "DATABASE_USER";
    static final String DATABASE_PASSWORD = "DATABASE_PASSWORD";
    static final String DEFAULT_DATABASE_URL = "jdbc:oracle:thin:@//ODAP01-SCAN:1521/odap01";
    static final String DEFAULT_DATABASE_USER = "sensors";
    static final String DEFAULT_DATABASE_PASSWORD = "s3ns()rs";
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
