package com.example.mjalba.malba_fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by MJ Alba on 2016-01-31.
 *
 * This class handles activity on the main screen of the app
 * (where the Fuel Log entries are visible).
 */
public class MainActivity extends AppCompatActivity {

    private static final String SAVEFILE = "file.sav"; // local save file name
    private ListView logList; // view display the current fuel log
    private ArrayList<LogEntry> log = new ArrayList<LogEntry>();
    private LogEntryAdapter adapter; // log entry adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        logList = (ListView) findViewById(R.id.logList);

        // button to add a new log entry
//        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addButton);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter = new LogEntryAdapter(this, log);
        logList.setAdapter(adapter);

        LogEntry entry = new LogEntry();
        entry.setStation("TEST STATION NAME");
        entry.setOdometer(10.1);
        entry.setGrade("grade");
        entry.setAmount(23.45);
        entry.setUnitCost(64.5);
        log.add(entry);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // deliver the user to the AddLogEntry activity
    public void addLogEntry(View view) {
        Intent intent = new Intent(this, AddLogEntryActivity.class);
        startActivity(intent);
    }
}
