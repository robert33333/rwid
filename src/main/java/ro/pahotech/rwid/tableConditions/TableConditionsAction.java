package ro.pahotech.rwid.tableConditions;

import pba.cxn.db.dbNotifier.DbEvent;
import pba.cxn.db.dbReader.DbRow;

import static ro.pahotech.rwid.Data.properties;
import static ro.pahotech.rwid.TracHandler.sendTracMessage;
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
            int gusts = row.getInt(properties.get(LABEL_GUST));
            if (gusts > Integer.parseInt(properties.get(LOWER_BOUND_C2LHPV_EBOR))) {
                sendTracMessage(properties.get(MESSAGE_C2LHPV_EBOR));
                return;
            }
            if (gusts > Integer.parseInt(LOWER_BOUND_STRONG_WIND_50)) {
                sendTracMessage(MESSAGE_STRONG_WIND_50);
                return;
            }
            if (gusts > Integer.parseInt(LOWER_BOUND_STRONG_WIND)) {
                sendTracMessage(MESSAGE_STRONG_WIND);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeVisibilityAction(DbRow row) {
        try {
            int visibility = row.getInt(properties.get(LABEL_VISIBILITY));
            if (visibility > Integer.parseInt(properties.get(LOWER_BOUND_NORMAL_VISIBILITY))) {
                return;
            }
            if (visibility > Integer.parseInt(properties.get(LOWER_BOUND_DROPPING_VISIBILITY))) {
                sendTracMessage(MESSAGE_DROPPING_VISIBILITY);
                return;
            }
            if (visibility > Integer.parseInt(properties.get(LOWER_BOUND_REDUCE_VISIBILITY))) {
                sendTracMessage(MESSAGE_REDUCE_VISIBILITY);
                return;
            }
            if (visibility > Integer.parseInt(properties.get(LOWER_BOUND_REDUCE_VISIBILITY_50))) {
                sendTracMessage(MESSAGE_REDUCE_VISIBILITY_50);
                return;
            }
            if (visibility > Integer.parseInt(properties.get(LOWER_BOUND_REDUCE_VISIBILITY_35))) {
                sendTracMessage(MESSAGE_REDUCE_VISIBILITY_35);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
