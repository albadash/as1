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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by MJ Alba on 2016-01-31.
 *
 * This class handles activity on the main screen of the app
 * (where the Fuel Log entries are visible).
 */
public class MainActivity extends AppCompatActivity {

    private static final String SAVEFILE = "file.sav"; // local save file name
    private ListView logList; // view to display the current fuel log
    private Log log = new Log(); // the current fuel log
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
        loadFromFile();
        adapter = new LogEntryAdapter(this, Log.getLog());
        logList.setAdapter(adapter);

//        LogEntry entry = new LogEntry();
//        entry.setStation("TEST STATION NAME");
//        entry.setOdometer(10.1);
//        entry.setGrade("grade");
//        entry.setAmount(23.45);
//        entry.setUnitCost(64.5);
//        log.add(entry);
//        saveInFile();
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

    // load fuel log from save file
    // code based on in-class LonelyTwitter example, the original source code for which
    // can be found at https://github.com/joshua2ua/lonelyTwitter
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(SAVEFILE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // taken from https://googlecode.com/svn/trunk/gson/docs/javadocs
            Type listType = new TypeToken<ArrayList<LogEntry>>() {}.getType();
            log.setLog((ArrayList<LogEntry>) gson.fromJson(in, listType));

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            log.setLog(new ArrayList<LogEntry>());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
