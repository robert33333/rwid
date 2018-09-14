package ro.pahotech.rwid.tableConditions;

import static ro.pahotech.rwid.Data.properties;

public class TableConditionsData {
    static final String GUST_LABEL = "GUST_LABEL";
    static final String DEFAULT_GUST_LABEL = "WINDSPEED_GUST";
    
    static final String C2LHPV_EBOR_LOWER_BOUND = "C2LHPV_EBOR_LOWER_BOUND";
    static final String DEFAULT_C2LHPV_EBOR_LOWER_BOUND = "60";

    static final String MESSAGE_C2LHPV_EBOR= "C2LHPV_EBOR_LOWER_BOUND";
    static final String DEFAULT_MESSAGE_C2LHPV_EBOR = "RWIS reports C2LHPV or EBOR";

    static final String STRONG_WIND_50_LOWER_BOUND = "STRONG_WIND_50_LOWER_BOUND";
    static final String DEFAULT_STRONG_WIND_50_LOWER_BOUND_LOWER_BOUND = "50";

    static final String MESSAGE_STRONG_WIND_50= "C2LHPV_EBOR_LOWER_BOUND";
    static final String DEFAULT_MESSAGE_STRONG_WIND_50 = "RWIS reports Strong wind 50";
    
    static final String STRONG_WIND_LOWER_BOUND = "STRONG_WIND_LOWER_BOUND";
    static final String DEFAULT_STRONG_WIND_LOWER_BOUND_LOWER_BOUND = "40";

    static final String MESSAGE_STRONG_WIND = "C2LHPV_EBOR_LOWER_BOUND";
    static final String DEFAULT_MESSAGE_STRONG_WIND = "RWIS reports Strong wind";

    static final String VISIBILITY_LABEL = "VISIBILITY_LABEL";
    static final String DEFAULT_VISIBILITY_LABEL = "VISIBILITY";

    public static void initializeTableConditionsData() {
        properties.put(GUST_LABEL, DEFAULT_GUST_LABEL);
        properties.put(C2LHPV_EBOR_LOWER_BOUND, DEFAULT_C2LHPV_EBOR_LOWER_BOUND);
        properties.put(STRONG_WIND_50_LOWER_BOUND, DEFAULT_STRONG_WIND_50_LOWER_BOUND_LOWER_BOUND);
        properties.put(STRONG_WIND_LOWER_BOUND, DEFAULT_STRONG_WIND_LOWER_BOUND_LOWER_BOUND);

        properties.put(MESSAGE_C2LHPV_EBOR,DEFAULT_MESSAGE_C2LHPV_EBOR);
        properties.put(MESSAGE_STRONG_WIND_50,DEFAULT_MESSAGE_STRONG_WIND_50);
        properties.put(MESSAGE_STRONG_WIND,DEFAULT_MESSAGE_STRONG_WIND);


        properties.put(VISIBILITY_LABEL,DEFAULT_VISIBILITY_LABEL);
    }
}
