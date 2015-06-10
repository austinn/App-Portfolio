package com.refect.appportfolio;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.refect.appportfolio.adapters.AppAdapter;
import com.refect.appportfolio.listeners.OnRecyclerViewItemClickListener;
import com.refect.appportfolio.models.AppModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvPortfolio;
    private ArrayList<AppModel> appModels;
    private AppAdapter appAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    /**
     *
     */
    private void initUI() {
        appAdapter = new AppAdapter(this);
        rvPortfolio = (RecyclerView) findViewById(R.id.rv_portfolio);
        rvPortfolio.setLayoutManager(new LinearLayoutManager(this));
        rvPortfolio.setItemAnimator(new DefaultItemAnimator());
        rvPortfolio.setAdapter(appAdapter);

        appAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener<AppModel>() {
            @Override
            public void onItemClick(View view, AppModel model) {
                if(model.getPackageName() == null) {
                    Toast.makeText(getApplicationContext(), "App has not been installed yet", Toast.LENGTH_SHORT).show();
                } else {
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage(model.getPackageName());
                    startActivity(launchIntent);
                }
            }
        });

        getModels();
    }

    /**
     * Creates and adds all the app models to the adapter
     * TODO: add colors to colors.xml
     */
    private void getModels() {
        appAdapter.add(createAppModel("Spotify Streamer", "...", null, Color.parseColor("#FFEB3B")), 0);
        appAdapter.add(createAppModel("Scores App", "...", null, Color.parseColor("#FFEB3B")), 1);
        appAdapter.add(createAppModel("Library App", "...", null, Color.parseColor("#FFEB3B")), 2);
        appAdapter.add(createAppModel("Build It Bigger", "...", null, Color.parseColor("#FFEB3B")), 3);
        appAdapter.add(createAppModel("XYZ Reader", "...", null, Color.parseColor("#FFEB3B")), 4);
        appAdapter.add(createAppModel("Capstone Project", "...", null, Color.parseColor("#F44336")), 5);
    }

    private AppModel createAppModel(String name, String description, String packageName, int color) {
        AppModel app = new AppModel();
        app.setName(name);
        app.setDescription(description);
        app.setPackageName(packageName);
        app.setResId(R.mipmap.ic_launcher);
        app.setBackgroundColor(color);

        return app;
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
}
