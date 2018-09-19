package ro.pahotech.rwid.actions.tableSurfaceConditions;

import pba.cxn.db.dbNotifier.DbEvent;
import pba.cxn.db.dbReader.DbRow;
import ro.pahotech.rwid.IceData;

import static ro.pahotech.rwid.Data.iceData;
import static ro.pahotech.rwid.Data.properties;
import static ro.pahotech.rwid.IceData.NULL;
import static ro.pahotech.rwid.TracHandler.sendTracMessage;
import static ro.pahotech.rwid.actions.tableConditions.TableConditionsAction.formMessage;
import static ro.pahotech.rwid.actions.tableConditions.TableConditionsData.MESSAGE_ICE;
import static ro.pahotech.rwid.actions.tableConditions.TableConditionsData.MESSAGE_ICE_LIFT;
import static ro.pahotech.rwid.actions.tableSurfaceConditions.TableSurfaceConditionsData.*;

public class TableSurfaceConditionsAction {
    public static void executeActionTableNameSurfaceConditions(DbEvent dbEvent) {
        try {
            DbRow row = dbEvent.getEventRow();
            String siteID = Integer.toString(row.getInt(properties.get(LABEL_ID)));
            executeIceAction(row, siteID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeIceAction(DbRow row, String siteID) {
        int surfTemp;
        try {
            surfTemp = row.getInt(properties.get(LABEL_SURF_TEMP));
        } catch (Exception e) {
            return;
        }
        IceData currentIceData = iceData.get(siteID);
        if (surfTemp < Integer.parseInt(properties.get(LOWER_BOUND_SURF_TEMP))) {
            if (currentIceData != null) {
                currentIceData.setSurfTemp(surfTemp);
                if (currentIceData.getRelativeHumidity() != NULL) {
                    sendTracMessage(formMessage(properties.get(MESSAGE_ICE), siteID));
                }
            } else {
                iceData.put(siteID, new IceData(surfTemp, NULL));
            }
        } else {
            if (currentIceData != null) {
                currentIceData.setSurfTemp(surfTemp);
                if (currentIceData.isAlertSent()) {
                    currentIceData.setAlertSent(false);
                    sendTracMessage(formMessage(properties.get(MESSAGE_ICE_LIFT), siteID));
                }
            }
        }
    }
}
