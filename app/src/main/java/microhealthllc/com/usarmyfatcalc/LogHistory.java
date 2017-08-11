package microhealthllc.com.usarmyfatcalc;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.ArrayList;
import java.util.List;

import microhealthllc.com.usarmyfatcalc.DB.BmiLogs;
import microhealthllc.com.usarmyfatcalc.DB.DBHandler;

public class LogHistory extends AppCompatActivity {
    private List<LogModel> logList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LogListAdapter mAdapter;
    DBHandler db;
FloatingActionButton home;
    FloatingActionButton about;
    FloatingActionButton info;
    FloatingActionButton delete_fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loghistory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.def_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        db = new DBHandler(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new LogListAdapter(logList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        home = (FloatingActionButton) findViewById(R.id.home) ;
        about = (FloatingActionButton)findViewById(R.id.about);
        info = (FloatingActionButton)findViewById(R.id.info);

        delete_fab = (FloatingActionButton) findViewById(R.id.delete);
        delete_fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new LovelyStandardDialog(LogHistory.this)
                        .setTopColorRes(R.color.accent)
                        .setButtonsColorRes(R.color.accent)

                        .setTitle("Warning!!")
                        .setMessage(" This will delete all stored data,This cannot be undone")
                        .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(LogHistory.this, "positive clicked", Toast.LENGTH_SHORT).show();
                                db.deleteEntry();
                                startActivity(new Intent(LogHistory.this, MainActivity.class));


                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogHistory.this, MainActivity.class);
                startActivity(i);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LogHistory.this, About.class);
                startActivity(in);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent(LogHistory.this, Info.class);
                startActivity(info);
            }
        });
        prepareMovieData();

    }

    private void prepareMovieData() {
        LogModel loghistory ;


        try {
            // Reading all shops
            Log.d("Reading:", "Reading all Logs.");
            List<BmiLogs> logs = db.getAllShops();

            for (BmiLogs log : logs) {
                loghistory = new LogModel(log.getBodyfat(), log.getWaist(), log.getNeck(),log.getHeight(),log.getDateTime());
               // loghistory = new LogModel(logs.get(i).getBodyfat(), logs.get(i).getWaist(), logs.get(i).getNeck(), logs.get(i).getHeight(),logs.get(i).getDateTime());

                logList.add(loghistory);

// Writing shops to log
//                Log.d("BMILO: : ", "weight:" + log.getWeight() + "  BMI:" + log.getBmi() + "  DateTime:" + log.getDateTime());

            }

        } catch (Exception e) {
            Log.i("Thisexcept", e.toString());
        }


        mAdapter.notifyDataSetChanged();
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }




}