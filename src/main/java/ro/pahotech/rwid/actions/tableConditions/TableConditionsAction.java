package ro.pahotech.rwid.actions.tableConditions;

import pba.cxn.db.dbNotifier.DbEvent;
import pba.cxn.db.dbReader.DbRow;
import ro.pahotech.rwid.IceData;

import static ro.pahotech.rwid.Data.iceData;
import static ro.pahotech.rwid.Data.properties;
import static ro.pahotech.rwid.IceData.NULL;
import static ro.pahotech.rwid.TracHandler.sendTracMessage;
import static ro.pahotech.rwid.actions.tableConditions.TableConditionsData.*;

public class TableConditionsAction {
    private static WindsPower currentWindAction = WindsPower.NONE;
    private static int COUNTER_C2LHPV_EBOR_FINISH;
    private static VisibilityLevel currentVisibilityLevel = VisibilityLevel.NORMAL;

    public static void executeActionTableNameConditions(DbEvent dbEvent) {
        try {
            DbRow row = dbEvent.getEventRow();
            String siteID = Integer.toString(row.getInt(properties.get(LABEL_ID)));
            executeActionWind(row, siteID);
            executeVisibilityAction(row, siteID);
            executeIceAction(row, siteID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeIceAction(DbRow row, String siteID) {
        int relativeHumidity;
        try {
            relativeHumidity = row.getInt(properties.get(LABEL_RELATIVE_HUMIDITY));
        } catch (Exception e) {
            return;
        }
        IceData currentIceData = iceData.get(siteID);
        if (relativeHumidity > Integer.parseInt(properties.get(LOWER_BOUND_RELATIVE_HUMIDITY))) {
            if (currentIceData != null) {
                currentIceData.setRelativeHumidity(relativeHumidity);
                if (currentIceData.getSurfTemp() != NULL) {
                    sendTracMessage(formMessage(properties.get(MESSAGE_ICE), siteID));
                }
            } else {
                iceData.put(siteID, new IceData(relativeHumidity, NULL));
            }
        } else {
            if (currentIceData != null) {
                currentIceData.setRelativeHumidity(relativeHumidity);
                if (currentIceData.isAlertSent()) {
                    currentIceData.setAlertSent(false);
                    sendTracMessage(formMessage(properties.get(MESSAGE_ICE_LIFT), siteID));
                }
            }
        }
    }

    private static void executeActionWind(DbRow row, String siteID) {
        try {
            int gusts;
            try {
                gusts = row.getInt(properties.get(LABEL_GUST));
            } catch (Exception e) {
                return;
            }
            if (gusts > Integer.parseInt(properties.get(LOWER_BOUND_C2LHPV_EBOR))) {
                if (currentWindAction != WindsPower.C2LHPV_EBOR)
                    sendTracMessage(formMessage(properties.get(MESSAGE_C2LHPV_EBOR), siteID));
                COUNTER_C2LHPV_EBOR_FINISH = 0;
                currentWindAction = WindsPower.C2LHPV_EBOR;
                return;
            }
            if (gusts > Integer.parseInt(properties.get(LOWER_BOUND_STRONG_WIND_50))) {
                if (currentWindAction != WindsPower.C2LHPV_EBOR && currentWindAction != WindsPower.STRONG_WIND_50)
                    sendTracMessage(formMessage(properties.get(MESSAGE_STRONG_WIND_50), siteID));
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
                    sendTracMessage(formMessage(properties.get(MESSAGE_STRONG_WIND), siteID));
                currentWindAction = WindsPower.STRONG_WIND;
                return;
            }
            //wind is not strong
            if (currentWindAction != WindsPower.C2LHPV_EBOR) {
                //drop alert
                sendTracMessage(formMessage(properties.get(MESSAGE_STRONG_WIND_ALERT_DROP), siteID));
                currentWindAction = WindsPower.NONE;
                return;
            }
            //C2LHPV_EBOR
            COUNTER_C2LHPV_EBOR_FINISH++;
            if (COUNTER_C2LHPV_EBOR_FINISH < Integer.parseInt(properties.get(COUNTER_C2LHPV_EBOR_FINISH_LIMIT)))
                return;
            sendTracMessage(formMessage(properties.get(MESSAGE_STRONG_WIND_ALERT_DROP), siteID));
            currentWindAction = WindsPower.NONE;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeVisibilityAction(DbRow row, String siteID) {
        try {
            int visibility;
            try {
                visibility = row.getInt(properties.get(LABEL_VISIBILITY));
            } catch (Exception e) {
                return;
            }
            if (visibility > Integer.parseInt(properties.get(LOWER_BOUND_NORMAL_VISIBILITY))) {
                if (currentVisibilityLevel != VisibilityLevel.NORMAL) {
                    currentVisibilityLevel = VisibilityLevel.NORMAL;
                    sendTracMessage(formMessage(properties.get(MESSAGE_NORMAL_VISIBILITY), siteID));
                }
                return;
            }
            if (visibility > Integer.parseInt(properties.get(LOWER_BOUND_DROPPING_VISIBILITY))) {
                if (currentVisibilityLevel != VisibilityLevel.DROPPING) {
                    sendTracMessage(formMessage(properties.get(MESSAGE_DROPPING_VISIBILITY), siteID));
                    currentVisibilityLevel = VisibilityLevel.DROPPING;
                }
                return;
            }
            if (visibility > Integer.parseInt(properties.get(LOWER_BOUND_REDUCE_VISIBILITY))) {
                if (currentVisibilityLevel != VisibilityLevel.REDUCE) {
                    sendTracMessage(formMessage(properties.get(MESSAGE_REDUCE_VISIBILITY), siteID));
                    currentVisibilityLevel = VisibilityLevel.REDUCE;
                }
                return;
            }
            if (visibility > Integer.parseInt(properties.get(LOWER_BOUND_REDUCE_VISIBILITY_50))) {
                if (currentVisibilityLevel != VisibilityLevel.REDUCE_50) {
                    sendTracMessage(formMessage(properties.get(MESSAGE_REDUCE_VISIBILITY_50), siteID));
                    currentVisibilityLevel = VisibilityLevel.REDUCE_50;
                }
                return;
            }
            if (visibility > Integer.parseInt(properties.get(LOWER_BOUND_REDUCE_VISIBILITY_35))) {
                if (currentVisibilityLevel != VisibilityLevel.REDUCE_35) {
                    sendTracMessage(formMessage(properties.get(MESSAGE_REDUCE_VISIBILITY_35), siteID));
                    currentVisibilityLevel = VisibilityLevel.REDUCE_35;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.jetbrains.annotations.NotNull
    public static String formMessage(String message, String addedInformation) {
        try {
            return message + properties.get(ADDED_MESSAGE_DESCRIPTION) + addedInformation;
        } catch (Exception e) {
            return properties.get(BAD_MESSAGE);
        }
    }
}
