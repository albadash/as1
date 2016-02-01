package com.example.mjalba.malba_fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by MJ Alba on 2016-01-31.
 *
 * This class handles activity on the main screen of the app
 * (where the Fuel Log entries are visible).
 */
public class MainActivity extends LogAppCompatActivity {

    private ListView logList; // view to display the current fuel log
    private LogEntryAdapter adapter; // log entry adapter
    private TextView totalCost; // total cost of fuel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        logList = (ListView) findViewById(R.id.logList);
        totalCost = (TextView) findViewById(R.id.total_cost);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        adapter = new LogEntryAdapter(this, Log.getLog());
        logList.setAdapter(adapter);
        totalCost.setText(log.getTotalCost());
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

    // deliver the user to the AddLogEntry activity with index data
    public void editLogEntry(View view) {
        Intent intent = new Intent(this, AddLogEntryActivity.class);
        Integer editIndex = (Integer) view.getTag();
        intent.putExtra("editIndex", editIndex);
        startActivity(intent);
    }
}
