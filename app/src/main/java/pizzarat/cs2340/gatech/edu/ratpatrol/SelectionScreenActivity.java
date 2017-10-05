package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import pizzarat.cs2340.gatech.edu.Model.RatSightingReport;
import pizzarat.cs2340.gatech.edu.exception.DuplicateReportDbException;
import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;


/**
 * This is the selection screen after logging in which allow the
 * user to switch to different screens with different functions
 * using the displayed buttons.
 *
 * @author Harrison Banh
 */
public class SelectionScreenActivity extends AppCompatActivity {
    private View logoutButton;
    private View ratArchiveButton;
    private View userReportsButton;
    private View ratMapButton;
    private BackgroundDataTask bdTask = null;
    private SQLiteReportBroker reportBroker;

    /**
     * Creates the SelectionScreenActivity
     * @param savedInstanceState the bundle from the last activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_screen);

        // Logout Function
        logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               logout();
            }
        });

        // New York Rat Archive
        ratArchiveButton = findViewById(R.id.ratArchiveButton);
        ratArchiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToNYRatArchiveActivity();
            }
        });

        // User Reports Activity
        userReportsButton = findViewById(R.id.userReportsButton);
        userReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToUserRatReportsActivity();
            }
        });

        // Rat Map Activity
        ratMapButton = findViewById(R.id.ratMapButton);
        ratMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToRatMapActivity();
            }
        });

        reportBroker = new SQLiteReportBroker();

        //second test button
//        Button mTestPop = (Button) findViewById(R.id.test_pop);
//        mTestPop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Gets the data repository in write mode
//                TextView tv = (TextView) findViewById(R.id.textView);
//                try {
//                    Context c = getApplicationContext();
//                    tv.setText(reportBroker.getDbContent(c));
//                }catch (Exception e) {
//                    tv.setText(e.getLocalizedMessage());
//                }
//            }
//        });

        bdTask = new BackgroundDataTask();
        bdTask.execute();
        //readRatData();
    }

    /**
     * Switches to the LoginActivity from the SelectionScreenActivity.
     */
    public void switchBackToLoginActivity() {
        Intent startSelectionScreenActivity = new Intent(SelectionScreenActivity.this, LoginActivity.class);
        SelectionScreenActivity.this.startActivity(startSelectionScreenActivity);
    }

    /**
     * Switches to the WelcomeActivity from the SelectionScreenActivity.
     */
    public void switchBackToWelcomeActivity() {
        Intent switchToWelcomeActivity = new Intent(SelectionScreenActivity.this, Welcome.class);
        SelectionScreenActivity.this.startActivity(switchToWelcomeActivity);
    }

    /**
     * Switches to the NewYorkRatArchiveActivity from the SelectionScreenActivity.
     */
    public void switchToNYRatArchiveActivity() {
        Intent switchToNYRatArchiveActivity = new Intent(this, NewYorkRatArchiveActivity.class);
        this.startActivity(switchToNYRatArchiveActivity);
    }

    /**
     * Switches to the UserRatReportsActivity.
     */
    public void switchToUserRatReportsActivity() {
        Intent switchToUserRatReportsActivity = new Intent(this, UserRatReportsActivity.class);
        this.startActivity(switchToUserRatReportsActivity);
    }

    /**
     * Switches to the RatMapActivity.
     */
    public void switchToRatMapActivity() {
        Intent switchToRatMapActivity = new Intent(this, RatMapActivity.class);
        this.startActivity(switchToRatMapActivity);
    }

    /**
     * Closes the SelectionScreenActivity thus "logging out" the user
     */
    // Make change later
    public void logout() {
       finish();
    }

    /**
     * Read in offline rat data from csv
     */
    private void readRatData()  {
        String csvFile = "raw/ratsightings.csv";
        InputStream inputStream;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        Log.d("hidden","in function...");

        RatSightingReport rsrTest;
        try {
            rsrTest = new RatSightingReport(12345, "my house", "10/10/2000", "12:00:00 AM", "101 Cool Dude Rd", "30309", "New York", "little one");
            reportBroker.writeToReportDb(rsrTest, this.getApplicationContext());
            Log.d("hidden",rsrTest.getDate());
        } catch (DuplicateReportDbException e) {
            Log.d("hidden",e.getLocalizedMessage());
        }

        try {
            Log.d("hidden","trying to read file...");
            inputStream = getResources().openRawResource(R.raw.ratsightings);
            br = new BufferedReader(new InputStreamReader(inputStream));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] ratSighting = line.split(cvsSplitBy);

                int key = Integer.parseInt(ratSighting[0]);
                String location = ratSighting[7];
                String date = getDate(ratSighting[1]);
                String time = getTime(ratSighting[1]);
                String address = ratSighting[9];
                String zip = ratSighting[8];
                String city = ratSighting[16];
                String borough = ratSighting[23];
                RatSightingReport rsr = new RatSightingReport(key,location,date,time,address,zip,city,borough);

                reportBroker.writeToReportDb(rsr,this.getApplicationContext());
            }

        } catch (FileNotFoundException e) {
            Log.d("hidden","FILE NOT FOUND");
            e.printStackTrace();
        } catch (DuplicateReportDbException e) {
            Log.d("hidden",e.getLocalizedMessage());
        } catch (IOException e) {
            Log.d("hidden","IOEXCEPTION");
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private String getDate(String dateAndTime) {
        return dateAndTime.split(" ")[0];
    }

    private String getTime(String dateAndTime) {
        return dateAndTime.substring(dateAndTime.indexOf(" "));
    }


    public class BackgroundDataTask extends AsyncTask<Context, Void, Void> {

        @Override
        protected Void doInBackground(Context... contexts) {
            readRatData();
            return null;
        }

    }

}
