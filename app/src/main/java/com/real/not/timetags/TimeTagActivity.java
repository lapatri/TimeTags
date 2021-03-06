package com.real.not.timetags;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This TimeTagActivity provides an interface component that displays a button and a list.
 * When the user presses the button, the elapsed time (in seconds) between startup and the
 * button being presses, is added to the list.
 */

public class TimeTagActivity extends ActionBarActivity {

    private ArrayList<String>timeTags = new ArrayList<String>();    //List of Time tags

    private ArrayAdapter<String> adapter; //Updater for list

    private DateFormat dateFormat =new SimpleDateFormat("HH:mm:ss, MM/dd/yyyy");

    private long startTime;
    private long currentTime;
    private long elapsedTimeSec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_tag);

        Date todayDate = new Date();
        startTime = System.currentTimeMillis();

        timeTags.add("Start: "+ dateFormat.format(todayDate));

        ListView listview = (ListView) findViewById(R.id.listViewTags);


        Button mTagButton = (Button) findViewById(R.id.tag_button);

        mTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTime = System.currentTimeMillis();
                elapsedTimeSec = (currentTime - startTime) / 1000;

                timeTags.add(0, Long.toString(elapsedTimeSec));
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,timeTags);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_time_tag, menu);
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
}
