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
            tracTask.setSource("RWIS");
            tracTask.setPriority(3);
            tracTask.setDistrict(0);
            tracTask.setCreatedAt(new Whenstamp());
            tracTask.setCreatedBy("RWIS");
            DatabaseReader databaseReader = new DatabaseReader(
                    Data.properties.get(TRAC_DATABASE_URL),
                    Data.properties.get(TRAC_DATABASE_USER),
                    new PlainText(Data.properties.get(TRAC_DATABASE_PASSWORD)));
            databaseReader.open();
            int updatedRowsNum = tracTask.insertRow(databaseReader,
                    new SqlTableName(Data.properties.get(TRAC_TABLE_NAME)),
                    new SqlSequenceName(Data.properties.get(TRAC_SEQUENCE_NAME)));
            if (updatedRowsNum != 1) {
                System.out.println(ERROR_TRAC_INSERT);
            }
            tracTask.updateRow(databaseReader, new SqlTableName(Data.properties.get(TRAC_TABLE_NAME)));
            databaseReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
