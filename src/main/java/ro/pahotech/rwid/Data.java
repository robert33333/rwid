package ro.pahotech.rwid;

import pba.cxn.db.dbConnector.DbConnector;
import pba.cxn.db.dbConnector.DbMapper;
import pba.readers.propReader.PropReader;

import java.util.HashMap;

import static java.lang.System.exit;
import static ro.pahotech.rwid.Strings.*;
import static ro.pahotech.rwid.tableConditions.TableConditionsData.initializeTableConditionsData;

public class Data {
    public static final HashMap<String,String> properties = new HashMap<>();

    static DbMapper dbMapper;

    private static void initializeData() {
    properties.put(TABLE_NAME_CONDITIONS, DEFAULT_TABLE_NAME_CONDITIONS);
    properties.put(TABLE_NAME_SURFACE_CONDITIONS,DEFAULT_TABLE_NAME_SURFACE_CONDITIONS);
    properties.put(DATABASE_URL,DEFAULT_DATABASE_URL);
    properties.put(DATABASE_USER,DEFAULT_DATABASE_USER);
    properties.put(DATABASE_PASSWORD,DEFAULT_DATABASE_PASSWORD);
        properties.put(TRAC_DATABASE_URL, TRAC_DEFAULT_DATABASE_URL);
        properties.put(TRAC_DATABASE_USER, TRAC_DEFAULT_DATABASE_USER);
        properties.put(TRAC_DATABASE_PASSWORD, TRAC_DEFAULT_DATABASE_PASSWORD);
    properties.put(TRAC_TABLE_NAME,DEFAULT_TRAC_TABLE_NAME);
    properties.put(TRAC_SEQUENCE_NAME,DEFAULT_TRAC_SEQUENCE_NAME);
    }

    static void initialize(String args[]) {
        initializeData();
        initializeTableConditionsData();

        readPropertiesFile(args);

        org.apache.log4j.BasicConfigurator.configure();

        initializeDatabaseConnection();
    }

    private static void readPropertiesFile(String args[]) {
        if (args == null)
            exit(1);
        String propertiesPath = FILE_PATH_PROPERTIES;
        try {
            if (args[1].equals("-path"))
                propertiesPath = args[2];
        } catch (Exception e) {
            System.out.println(ERROR_ARGS_NOT_PROVIDED);
        }
        PropReader propReader = new PropReader(propertiesPath);

        for (String property : properties.keySet()) {
            try {
                String value = propReader.get(property);
                properties.put(property,value);
            }
            catch (Exception e) {
                System.out.println(ERROR_PROPERTY_NOT_FOUND + property);
            }
        }
    }

    private static void initializeDatabaseConnection() {
        try {
            dbMapper = new DbMapper(
                    DB_MAPPER_NAME,
                    properties.get(DATABASE_URL),
                    properties.get(DATABASE_USER),
                    properties.get(DATABASE_PASSWORD)
            );
            DbConnector.initialize(dbMapper);
        } catch (Exception e) {
            System.out.println(ERROR_FATAL_DATABASE_INITIALIZATION);
            exit(1);
        }
    }
}
