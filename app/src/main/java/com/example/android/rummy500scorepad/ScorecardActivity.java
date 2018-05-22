package com.example.android.rummy500scorepad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScorecardActivity extends AppCompatActivity implements PlayerAdapter.PlayerClickHandler {
    private String LOG_TAG = ScorecardActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private PlayerAdapter adapter;
    private List<String[]> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard);

        recyclerView = findViewById(R.id.players_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PlayerAdapter(this);
        data = getFakeData();
        adapter.setData(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(int buttonId) {
        Log.d(LOG_TAG, "Clicked on button " + buttonId);
    }

    private List<String[]> getFakeData() {
        String [][] data = {
                {"Player 1", "0"},
                {"Player 2", "0"}};
        return new ArrayList<>(Arrays.asList(data));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                resetData();
                break;
            case R.id.action_add_player:
                String[] newPlayer = new String[] {"Player " + (data.size()+1), "0"};
                data.add(newPlayer);
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void resetData() {
        for(int i=0; i<data.size(); i++) {
            data.get(i)[1] = "0";
        }
        adapter.setData(data);
    }
}
