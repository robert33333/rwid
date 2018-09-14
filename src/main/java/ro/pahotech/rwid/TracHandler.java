package ro.pahotech.rwid;

import wyoroad.scrambler.PlainText;
import wyoroad.wydata.TracTask;
import wyoroad.wydgets.DatabaseReader;
import wyoroad.wydgets.Whenstamp;
import wyoroad.wydgets.sql.SqlSequenceName;
import wyoroad.wydgets.sql.SqlTableName;

import static ro.pahotech.rwid.Strings.*;

public class TracHandler {
    public static void sendTracMessage(String message) {
        try {
            TracTask tracTask = new TracTask();
            tracTask.setDescription(message);
            tracTask.setSource("SSA_APPLICATION");
            tracTask.setPriority(3);
            tracTask.setDistrict(0);
            tracTask.setCreatedAt(new Whenstamp());
            tracTask.setCreatedBy("RWIS_MONITORING_APPLICATION");
            DatabaseReader databaseReader = new DatabaseReader(
                    Data.properties.get(DATABASE_URL),
                    Data.properties.get(DATABASE_USER),
                    new PlainText(Data.properties.get(DATABASE_PASSWORD)));
            databaseReader.open();
            int updatedRowsNum = tracTask.insertRow(databaseReader,
                    new SqlTableName(TRAC_TABLE_NAME),
                    new SqlSequenceName(TRAC_SEQUENCE_NAME));
            if (updatedRowsNum != 1) {
                //ceva nu e bine!
                System.out.println(ERROR_TRAC_INSERT);
            }
            tracTask.updateRow(databaseReader, new SqlTableName(TRAC_TABLE_NAME));
            databaseReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
