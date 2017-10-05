package pizzarat.cs2340.gatech.edu.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import pizzarat.cs2340.gatech.edu.Model.RatSightingReport;
import pizzarat.cs2340.gatech.edu.exception.DuplicateReportDbException;


/**
 * Created by evieb on 10/4/2017.
 */

public class SQLiteReportBroker extends AppCompatActivity { //TODO: duplicate exception logging
    public long writeToReportDb(RatSightingReport rReport, Context context) throws DuplicateReportDbException {
        final CredentialDb ratDb = new CredentialDb(context);
        //throw DuplicateReportDbException if report already exists
//        if (containsDuplicateReport(username, context))
//            throw new DuplicateReportDbException();
        // Gets the data repository in write mode
        SQLiteDatabase writableDb = ratDb.getWritableDatabase();
        ContentValues values = new ContentValues();
        //in X column, set X
        values.put(RatSightingDb.getReportTableKeyCol(), rReport.getKey());
        values.put(RatSightingDb.getReportTableLocationCol(), rReport.getLocation());
        values.put(RatSightingDb.getReportTableDateCol(), rReport.getDate());
        values.put(RatSightingDb.getReportTableTimeCol(), rReport.getTime());
        values.put(RatSightingDb.getReportTableAddressCol(), rReport.getAddress());
        values.put(RatSightingDb.getReportTableZipcodeCol(), rReport.getZipCode());
        values.put(RatSightingDb.getReportTableCityCol(), rReport.getCity());
        values.put(RatSightingDb.getReportTableBoroughCol(), rReport.getBorough());

        // Insert the new row, returning the primary key value of the new row
        return writableDb.insert(RatSightingDb.getTableName(), null, values);
    }
}
