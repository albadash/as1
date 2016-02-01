package com.example.mjalba.malba_fueltrack;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MJ Alba on 2016-02-01.
 *
 * This class represents a Log Entry as a list item. Its purpose is
 * to display entries as list items according to the markup defined by
 * log_entry.xml.
 */
public class LogEntryAdapter extends ArrayAdapter<LogEntry> {

    private Context context;
    private ArrayList<LogEntry> arrayList;

    public LogEntryAdapter(Context context, ArrayList<LogEntry> arrayList) {
        super(context, R.layout.log_entry, arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    // define how to get a view for a ListView
    // based on code by hmkcode found on http://hmkcode.com/android-custom-listview-items-row/
    @Override
    public View getView(int index, View convert, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View logEntryView = inflater.inflate(R.layout.log_entry, parent, false);

        TextView dateStationView = (TextView) logEntryView.findViewById(R.id.date_station);
        TextView odometerView = (TextView) logEntryView.findViewById(R.id.odometer);
        TextView gradeView = (TextView) logEntryView.findViewById(R.id.grade);
        TextView costView = (TextView) logEntryView.findViewById(R.id.cost);

        String gradeString = "of <b>" + arrayList.get(index).getGrade() + "</b> fuel (<b>" + arrayList.get(index).getAmount() + " L</b> at <b>" + arrayList.get(index).getUnitCost() + " cents/L)</b>";
        String dateStationString = "on <i>" + arrayList.get(index).getDate() + "</i> at <u>" + arrayList.get(index).getStation() + "</u>";
        String odometerString = "where your odometer read <i>" + arrayList.get(index).getOdometer() + " km</i>";

        costView.setText(arrayList.get(index).getCost());
        gradeView.setText(Html.fromHtml(gradeString));
        dateStationView.setText(Html.fromHtml(dateStationString));
        odometerView.setText(Html.fromHtml(odometerString));

        Button editButton = (Button) logEntryView.findViewById(R.id.edit_button);
        editButton.setTag(index); // unique tag representing array index

        return logEntryView;
    }
}
