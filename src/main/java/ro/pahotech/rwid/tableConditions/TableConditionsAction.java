package ro.pahotech.rwid.tableConditions;

import pba.cxn.db.dbNotifier.DbEvent;
import pba.cxn.db.dbReader.DbRow;

import static ro.pahotech.rwid.Data.properties;
import static ro.pahotech.rwid.TracHandler.sendTracMessage;
import static ro.pahotech.rwid.tableConditions.TableConditionsData.*;

public class TableConditionsAction {
    private static WindsPower currentWindAction;
    private static int COUNTER_C2LHPV_EBOR_FINISH;

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
            int gusts;
            try {
                gusts = row.getInt(properties.get(LABEL_GUST));
            } catch (Exception e) {
                return;
            }
            if (gusts > Integer.parseInt(properties.get(LOWER_BOUND_C2LHPV_EBOR))) {
                if (currentWindAction != WindsPower.C2LHPV_EBOR)
                    sendTracMessage(properties.get(MESSAGE_C2LHPV_EBOR));
                COUNTER_C2LHPV_EBOR_FINISH = 0;
                currentWindAction = WindsPower.C2LHPV_EBOR;
                return;
            }
            if (gusts > Integer.parseInt(properties.get(LOWER_BOUND_STRONG_WIND_50))) {
                if (currentWindAction != WindsPower.C2LHPV_EBOR && currentWindAction != WindsPower.STRONG_WIND_50)
                    sendTracMessage(properties.get(MESSAGE_STRONG_WIND_50));
                currentWindAction = WindsPower.STRONG_WIND_50;
                return;
            }
            if (gusts > Integer.parseInt(properties.get(LOWER_BOUND_STRONG_WIND))) {
                if (currentWindAction == WindsPower.C2LHPV_EBOR) {
                    COUNTER_C2LHPV_EBOR_FINISH++;
                    if (COUNTER_C2LHPV_EBOR_FINISH < Integer.parseInt(properties.get(COUNTER_C2LHPV_EBOR_FINISH_LIMIT)))
                        return;
                }
                if (currentWindAction != WindsPower.STRONG_WIND_50 && currentWindAction != WindsPower.STRONG_WIND)
                    sendTracMessage(properties.get(MESSAGE_STRONG_WIND));
                currentWindAction = WindsPower.STRONG_WIND;
                return;
            }
            //wind is not strong
            if (currentWindAction != WindsPower.C2LHPV_EBOR) {
                //drop alert
                sendTracMessage(properties.get(MESSAGE_STRONG_WIND_ALERT_DROP));
                currentWindAction = WindsPower.NONE;
                return;
            }
            //C2LHPV_EBOR
            COUNTER_C2LHPV_EBOR_FINISH++;
            if (COUNTER_C2LHPV_EBOR_FINISH < Integer.parseInt(properties.get(COUNTER_C2LHPV_EBOR_FINISH_LIMIT)))
                return;
            sendTracMessage(properties.get(MESSAGE_STRONG_WIND_ALERT_DROP));
            currentWindAction = WindsPower.NONE;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeVisibilityAction(DbRow row) {
        try {
            int visibility;
            try {
                visibility = row.getInt(properties.get(LABEL_VISIBILITY));
            } catch (Exception e) {
                return;
            }
            if (visibility > Integer.parseInt(properties.get(LOWER_BOUND_NORMAL_VISIBILITY))) {
                return;
            }
            if (visibility > Integer.parseInt(properties.get(LOWER_BOUND_DROPPING_VISIBILITY))) {
                sendTracMessage(properties.get(MESSAGE_DROPPING_VISIBILITY));
                return;
            }
            if (visibility > Integer.parseInt(properties.get(LOWER_BOUND_REDUCE_VISIBILITY))) {
                sendTracMessage(properties.get(MESSAGE_REDUCE_VISIBILITY));
                return;
            }
            if (visibility > Integer.parseInt(properties.get(LOWER_BOUND_REDUCE_VISIBILITY_50))) {
                sendTracMessage(properties.get(MESSAGE_REDUCE_VISIBILITY_50));
                return;
            }
            if (visibility > Integer.parseInt(properties.get(LOWER_BOUND_REDUCE_VISIBILITY_35))) {
                sendTracMessage(properties.get(MESSAGE_REDUCE_VISIBILITY_35));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
