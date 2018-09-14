package ro.pahotech.rwid;

import pba.cxn.db.dbNotifier.DbEvent;
import pba.cxn.db.dbNotifier.OnDatabaseChange;
import pba.cxn.db.dbNotifier.oracle.OracleWatcher;

import static ro.pahotech.rwid.Data.dbMapper;
import static ro.pahotech.rwid.Strings.*;
import static ro.pahotech.rwid.tableConditions.TableConditionsAction.executeActionTableNameConditions;
import static ro.pahotech.rwid.actions.ActionTableSurfaceConditions.executeActionTableNameSurfaceConditions;

class Main {
    public static void main(String[] args) {
        Data.initialize(args);

        createWatcher(TABLE_NAME_CONDITIONS);
        createWatcher(TABLE_NAME_SURFACE_CONDITIONS);
    }

    private static void createWatcher(String tableName) {
        OracleWatcher watcher = new OracleWatcher(tableName, dbMapper, new OnDatabaseChange() {
            @Override
            public void onChange(DbEvent dbEvent) {
            }

            @Override
            public void onInsert(DbEvent dbEvent) {
                executeAction(dbEvent,tableName);
            }

            @Override
            public void onUpdate(DbEvent dbEvent) {
            }

            @Override
            public void onDelete(DbEvent dbEvent) {
            }
        });
    }

    private static void executeAction(DbEvent dbEvent, String tableName) {
        if (dbEvent == null || tableName == null)
            return;
        switch (tableName) {
            case TABLE_NAME_CONDITIONS:
                executeActionTableNameConditions(dbEvent);
                break;
            case TABLE_NAME_SURFACE_CONDITIONS:
                executeActionTableNameSurfaceConditions();
                break;
            default: System.out.println(ERROR_ACTION_NOT_IMPLEMENTED);
        }
    }
}
