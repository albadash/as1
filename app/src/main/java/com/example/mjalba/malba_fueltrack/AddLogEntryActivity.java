package com.example.mjalba.malba_fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by MJ Alba on 2016-01-31.
 *
 * This class handles activity on the Add Entry screen of the app
 * (where users may add a new entry).
 */
public class AddLogEntryActivity extends LogAppCompatActivity {

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

        // get edit information
        Intent intent = getIntent();
        final Integer editIndex = intent.getIntExtra("editIndex", -1);

        // set date formatter
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        // set fields whether adding or editing
        if (editIndex == -1) {
            dateField.setText(formatter.format(new Date()));
        } else {
            setTitle(R.string.title_activity_edit_log);
            dateField.setText(log.getLog().get(editIndex).getDate());
            stationField.setText(log.getLog().get(editIndex).getStation());
            odometerField.setText(log.getLog().get(editIndex).getOdometer());
            gradeField.setText(log.getLog().get(editIndex).getGrade());
            amountField.setText(log.getLog().get(editIndex).getAmount());
            unitCostField.setText(log.getLog().get(editIndex).getUnitCost());
        }

        // handle saving new entries
        FloatingActionButton saveButton = (FloatingActionButton) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);

                // validate input data
                Boolean goodData = true;
                Scanner scanner = new Scanner(dateField.getText().toString());
                goodData = goodData && scanner.hasNext("\\d{4}-\\d{2}-\\d{2}");
                scanner = new Scanner(odometerField.getText().toString());
                goodData = goodData && scanner.hasNextDouble();
                scanner = new Scanner(amountField.getText().toString());
                goodData = goodData && scanner.hasNextDouble();
                scanner = new Scanner(unitCostField.getText().toString());
                goodData = goodData && scanner.hasNextDouble();

                // add, edit, or warn user about bad data
                if (!goodData) {
                    Snackbar.make(view, R.string.bad_data_warning, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                } else if (editIndex == -1) {
                    addNewEntry();
                } else {
                    editEntry(editIndex);
                }
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

    // add a new entry to the fuel log
    private void addNewEntry() {

        String date = dateField.getText().toString();
        String station = stationField.getText().toString();
        Double odometer = Double.parseDouble(odometerField.getText().toString());
        String grade = gradeField.getText().toString();
        Double amount = Double.parseDouble(amountField.getText().toString());
        Double unitCost = Double.parseDouble(unitCostField.getText().toString());

        // create new entry and set fields
        LogEntry entry = new LogEntry();
        entry.setDate(date);
        entry.setStation(station);
        entry.setOdometer(odometer);
        entry.setGrade(grade);
        entry.setAmount(amount);
        entry.setUnitCost(unitCost);

        // add entry, save, and return to main
        log.addEntry(entry);
        saveInFile();
        finish();
    }

    // edit an existing entry in the fuel log
    private void editEntry(Integer index) {

        String date = dateField.getText().toString();
        String station = stationField.getText().toString();
        Double odometer = Double.parseDouble(odometerField.getText().toString());
        String grade = gradeField.getText().toString();
        Double amount = Double.parseDouble(amountField.getText().toString());
        Double unitCost = Double.parseDouble(unitCostField.getText().toString());

        // update log entry at provided index
        log.getLog().get(index).setDate(date);
        log.getLog().get(index).setStation(station);
        log.getLog().get(index).setOdometer(odometer);
        log.getLog().get(index).setGrade(grade);
        log.getLog().get(index).setAmount(amount);
        log.getLog().get(index).setUnitCost(unitCost);

        // save and return to main
        saveInFile();
        finish();
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
