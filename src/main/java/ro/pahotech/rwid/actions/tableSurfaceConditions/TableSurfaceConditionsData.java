package ro.pahotech.rwid.actions.tableSurfaceConditions;

import static ro.pahotech.rwid.Data.properties;

public class TableSurfaceConditionsData {
    static final String LABEL_ID = "LABEL_ID";
    static final String LABEL_SURF_TEMP = "LABEL_SURF_TEMP";
    static final String LOWER_BOUND_SURF_TEMP = "LOWER_BOUND_SURF_TEMP";
    private static final String DEFAULT_LABEL_ID = "SITEID";
    private static final String DEFAULT_LABEL_SURF_TEMP = "SURFACE_TEMP";
    private static final String DEFAULT_LOWER_BOUND_SURF_TEMP = "32";

    public static void initializeTableSurfaceConditionsData() {
        properties.put(LABEL_ID, DEFAULT_LABEL_ID);
        properties.put(LABEL_SURF_TEMP, DEFAULT_LABEL_SURF_TEMP);

        properties.put(LOWER_BOUND_SURF_TEMP, DEFAULT_LOWER_BOUND_SURF_TEMP);
    }
}
