package ro.pahotech.rwid.tableConditions;

import pba.cxn.db.dbNotifier.DbEvent;
import pba.cxn.db.dbReader.DbRow;
import static ro.pahotech.rwid.TracHandler.sendTracMessage;
import static ro.pahotech.rwid.Data.properties;
import static ro.pahotech.rwid.tableConditions.TableConditionsData.*;

public class TableConditionsAction {
    public static void executeActionTableNameConditions(DbEvent dbEvent) {
        try {
            DbRow row = dbEvent.getEventRow();
            executeActionWind(row);
            executeVisibilityAction(row);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void executeActionWind(DbRow row) {
        try {
            int gusts = row.getInt(properties.get(GUST_LABEL));
            if (gusts > Integer.parseInt(properties.get(C2LHPV_EBOR_LOWER_BOUND))) {
                sendTracMessage(properties.get(MESSAGE_C2LHPV_EBOR));
                return;
            }
            if (gusts > Integer.parseInt(STRONG_WIND_50_LOWER_BOUND)) {
                sendTracMessage(MESSAGE_STRONG_WIND_50);
                return;
            }
            if (gusts > Integer.parseInt(STRONG_WIND_LOWER_BOUND)) {
                sendTracMessage(MESSAGE_STRONG_WIND);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeVisibilityAction(DbRow row) {

    }
}
