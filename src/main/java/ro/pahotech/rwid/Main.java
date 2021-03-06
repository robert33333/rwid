package ro.pahotech.rwid;

import pba.cxn.db.dbNotifier.DbEvent;
import pba.cxn.db.dbNotifier.DbNotificator;
import pba.cxn.db.dbNotifier.OnDatabaseChange;
import pba.cxn.db.dbNotifier.oracle.OracleWatcher;

import static java.lang.System.exit;
import static ro.pahotech.rwid.Data.dbMapper;
import static ro.pahotech.rwid.Data.properties;
import static ro.pahotech.rwid.Strings.*;
import static ro.pahotech.rwid.actions.tableConditions.TableConditionsAction.executeActionTableNameConditions;
import static ro.pahotech.rwid.actions.tableSurfaceConditions.TableSurfaceConditionsAction.executeActionTableNameSurfaceConditions;

public class Main {
    public static void main(String[] args) {
        Data.initialize(args);

        createWatcher(properties.get(TABLE_NAME_CONDITIONS));
        createWatcher(properties.get(TABLE_NAME_SURFACE_CONDITIONS));
    }

    private static void createWatcher(String tableName) {
        try {
            OracleWatcher watcher = new OracleWatcher(tableName, dbMapper, new OnDatabaseChange() {
                @Override
                public void onChange(DbEvent dbEvent) {
                }

                @Override
                public void onInsert(DbEvent dbEvent) {
                    executeAction(dbEvent, tableName);
                }

                @Override
                public void onUpdate(DbEvent dbEvent) {
                }

                @Override
                public void onDelete(DbEvent dbEvent) {
                }
            });
            DbNotificator.AddWatcher(watcher);
        } catch (Exception e) {
            System.out.print(ERROR_FATAL_ADD_MAIN_WATCHERS);
            exit(1);
        }
    }

    public static void executeAction(DbEvent dbEvent, String tableName) {
        if (dbEvent == null || tableName == null)
            return;
        switch (tableName) {
            case DEFAULT_TABLE_NAME_CONDITIONS:
                executeActionTableNameConditions(dbEvent);
                break;
            case DEFAULT_TABLE_NAME_SURFACE_CONDITIONS:
                executeActionTableNameSurfaceConditions(dbEvent);
                break;
            default: System.out.println(ERROR_ACTION_NOT_IMPLEMENTED);
        }
    }
}
