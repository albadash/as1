package com.example.mjalba.malba_fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by MJ Alba on 2016-01-31.
 *
 * This class handles activity on the Add Entry screen of the app
 * (where users may add a new entry).
 */
public class AddLogEntryActivity extends AppCompatActivity {

    private static final String SAVEFILE = "file.sav"; // local save file name
    private Log log = new Log(); // the current fuel log

    private EditText dateField;
    private EditText stationField;
    private EditText odometerField;
    private EditText gradeField;
    private EditText amountField;
    private EditText unitCostField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dateField = (EditText) findViewById(R.id.date);
        stationField = (EditText) findViewById(R.id.station);
        odometerField = (EditText) findViewById(R.id.odometer);
        gradeField = (EditText) findViewById(R.id.grade);
        amountField = (EditText) findViewById(R.id.amount);
        unitCostField = (EditText) findViewById(R.id.unit_cost);

        // set current date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        dateField.setText(formatter.format(new Date()));

        // handle saving new entries
        FloatingActionButton saveButton = (FloatingActionButton) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);

                String date = dateField.getText().toString();
                String station = stationField.getText().toString();
                Double odometer = Double.parseDouble(odometerField.getText().toString());
                String grade = gradeField.getText().toString();
                Double amount = Double.parseDouble(amountField.getText().toString());
                Double unitCost = Double.parseDouble(unitCostField.getText().toString());

                LogEntry entry = new LogEntry();
                //entry.setDate(new Date(date));
                entry.setStation(station);
                entry.setOdometer(odometer);
                entry.setGrade(grade);
                entry.setAmount(amount);
                entry.setUnitCost(unitCost);

                log.addEntry(entry);
                saveInFile();
                finish();
            }
        });

        // handle cancelling adding new entries
        FloatingActionButton cancelButton = (FloatingActionButton) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    // set up fuel log data
    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile(); // load log
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

    // save fuel log to save file
    // code based on in-class LonelyTwitter example, the original source code for which
    // can be found at https://github.com/joshua2ua/lonelyTwitter
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(SAVEFILE, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(log.getLog(), out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
