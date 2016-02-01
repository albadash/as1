package com.example.mjalba.malba_fueltrack;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by MJ Alba on 2016-01-31.
 *
 * This class represents an entire Fuel Log of Log Entries. Its purpose is
 * to keep track of all the user's Entries, as well as manage their Fuel Log
 * (by viewing Entries, adding new ones, etc).
 */
public class Log {

    protected static ArrayList<LogEntry> log = new ArrayList<LogEntry>();

    // gets the log array list
    public static ArrayList<LogEntry> getLog() {
        return log;
    }

    // sets the log array list
    public static void setLog(ArrayList<LogEntry> log) {
        Log.log = log;
    }

    // adds a LogEntry to the Log
    public void addEntry(LogEntry entry) {
        log.add(entry);
    }

    // checks if an entry is in the log
    public boolean hasEntry(LogEntry entry) {
        return log.contains(entry);
    }

    // gets the total fuel cost of all the entries as a string
    public String getTotalCost() {
        Double total = 0.0;
        for (LogEntry entry : log) {
            total += entry.getCostValue();
        }
        String costString = new DecimalFormat("#.00").format(total);
        return "$" + costString;
    }
}
