package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Represents the screen that displays all the rat sightings in New York.
 */
public class NewYorkRatArchiveActivity extends AppCompatActivity {
    private Button switchToReportDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_york_rat_archive);

        // TODO: Design this Activity to list all reports
        switchToReportDetails = (Button) findViewById(R.id.reportDetailsButton);
        switchToReportDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToReportDetailsActivity();
            }
        });
    }

    private void switchToReportDetailsActivity() {
        Intent switchToReportDetails = new Intent(this, DetailedRatReportViewActivity.class);
        this.startActivity(switchToReportDetails);
    }


}
