package com.example.mjalba.malba_fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

/**
 * Created by MJ Alba on 2016-01-31.
 *
 * This class handles activity on the Add Entry screen of the app
 * (where users may add a new entry).
 */
public class AddLogEntryActivity extends AppCompatActivity {

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

        stationField = (EditText) findViewById(R.id.station);
        odometerField = (EditText) findViewById(R.id.odometer);
        gradeField = (EditText) findViewById(R.id.grade);
        amountField = (EditText) findViewById(R.id.amount);
        unitCostField = (EditText) findViewById(R.id.unit_cost);

        // handle saving new entries
        FloatingActionButton saveButton = (FloatingActionButton) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                
                String station = stationField.getText().toString();
                Double odometer = Double.parseDouble(stationField.getText().toString());
                String grade = stationField.getText().toString();
                Double amount = Double.parseDouble(stationField.getText().toString());
                Double unitCost = Double.parseDouble(stationField.getText().toString());

                LogEntry entry = new LogEntry();
                entry.setStation(station);
                entry.setOdometer(odometer);
                entry.setGrade(grade);
                entry.setAmount(amount);
                entry.setUnitCost(unitCost);
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
}
