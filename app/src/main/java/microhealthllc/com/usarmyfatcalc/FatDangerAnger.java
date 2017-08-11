package microhealthllc.com.usarmyfatcalc;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import microhealthllc.com.usarmyfatcalc.DB.BmiLogs;
import microhealthllc.com.usarmyfatcalc.DB.DBHandler;
import microhealthllc.com.usarmyfatcalc.chart.ColorArcProgressBar;

public class FatDangerAnger extends AppCompatActivity {
    private ColorArcProgressBar bar1;
    TextView  age;
    TextView height;
    TextView waist;
    TextView neck;
    TextView hip;
    int user_age;
    Double bodyfat;
    TextView bf ;
    private RecyclerView recyclerView;
    private LogListAdapter mAdapter;
    private List<LogModel> logList = new ArrayList<>();
    DBHandler db;

    FloatingActionButton home;
    FloatingActionButton about;
    FloatingActionButton info;
    FloatingActionButton history;


    private static DecimalFormat df2 = new DecimalFormat(".#");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fat_danger_anger);

        Bundle extras = getIntent().getExtras();
        height = (TextView) findViewById(R.id.display_height) ;
        waist = (TextView) findViewById(R.id.display_waist);
        //   hip = (TextView) findViewById(R.id.display_hip);
        bf = (TextView) findViewById(R.id.bf_note);
        neck = (TextView) findViewById(R.id.display_neck);
        bodyfat =  extras.getDouble("bodyfat");
        double[] values= extras.getDoubleArray("values");
        boolean isfemale = extras.getBoolean("isfemale");

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("Info ");

        }
        db  = new DBHandler(this);

        home = (FloatingActionButton) findViewById(R.id.home) ;
        about = (FloatingActionButton)findViewById(R.id.about);
        info = (FloatingActionButton)findViewById(R.id.info);

        history = (FloatingActionButton) findViewById(R.id.loghistory);
        mAdapter = new LogListAdapter(logList);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        user_age  = extras.getInt("age");
        height.setText(""+df2.format(values[0]));
        waist.setText(""+df2.format(values[1]));
        neck.setText(""+df2.format(values[2]));
       // analysis(isfemale);
        bar1 = (ColorArcProgressBar) findViewById(R.id.bar1);
        bar1.setUnit("body fat %");
        if(!(bodyfat >0)){
            bodyfat =0.0;
            bar1.setCurrentValues(bodyfat);
        }
        else {
            int max = (int) (bodyfat.intValue() * 1.5);
            bar1.setMaxValues(max);
            bar1.setCurrentValues(bodyfat);

        }
        bf.setText("You do not meet the Department of Defense goal");

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is = new Intent(FatDangerAnger.this, LogHistory.class);
                startActivity(is);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FatDangerAnger.this, MainActivity.class);
                startActivity(i);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(FatDangerAnger.this, About.class);
                startActivity(in);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent(FatDangerAnger.this, Info.class);
                startActivity(info);
            }
        });
        prepareMovieData();
    }



    public void analysis(Boolean isfemale){
        //If is female is true
        if(isfemale){
            if(((user_age >=17 && user_age <=27)&&(bodyfat>0 && bodyfat <=36))|| ((user_age >=28 && user_age <=39)&&(bodyfat>0 && bodyfat <=34))|| ((user_age >=40)&&(bodyfat>0 && bodyfat <=36))){
                bf.setText("You meet the Department of Defense goal");
                Log.i("Bf :","You meet the Department of Defense goal");
            }

            else{
                bf.setText("You do not meet the Department of Defense goal");
                Log.i("Bf :","You do not meet the Department of Defense goal");
            }
        }
        else{
            if(((user_age >=17 && user_age <=27)&&(bodyfat>0 && bodyfat <=26))|| ((user_age >=28 && user_age <=39)&&(bodyfat>=0 && bodyfat <=28))|| ((user_age >=40)&&(bodyfat>0 && bodyfat <=30))){
                bf.setText("You meet the Department of Defense goal");
                bf.setTextColor(getResources().getColor(R.color.text_dark));
                Log.i("Bf :","You meet the Department of Defense goall");
            }

            else{

                bf.setText("You do not meet the Department of Defense goal ");
                bf.setTextColor(getResources().getColor(R.color.red));



                Log.i("Bf :","You do not meet the Department of Defense goal");
            }

        }
    }

    private List<BmiLogs> reverseList(List<BmiLogs> myList) {
        List<BmiLogs> invertedList = new ArrayList<BmiLogs>();
        for (int i = myList.size() - 1; i >= 0; i--) {
            invertedList.add(myList.get(i));
        }
        return invertedList;
    }

    private void prepareMovieData() {
        LogModel loghistory ;


        try {
            // Reading all shops
            Log.d("Reading:", "Reading all Logs.");
            List<BmiLogs> logs = db.getAllShops();
            List<BmiLogs> reverse;
            reverse =  reverseList(logs);


            // for (BmiLogs log : logs) {
            //     loghistory = new LogModel(log.getBmi(), log.getWeight(), log.getDateTime());
//  List<BmiLogs> logs = db.getAllShops();
            for (int i=logs.size()-1;i>logs.size()-3; --i){
                loghistory = new LogModel(logs.get(i).getBodyfat(), logs.get(i).getWaist(), logs.get(i).getNeck(), logs.get(i).getHeight(),logs.get(i).getDateTime());
                Log.i("Logss",""+logs.get(i).getBodyfat()+" ," +logs.get(i).getWaist()+", " + logs.get(i).getNeck()+" ," +logs.get(i).getHeight()+", " +logs.get(i).getDateTime()+"\n");
                logList.add(loghistory);
            }




// Writing shops to log
            //      Log.d("BMILO: : ", "weight:" + log.getWeight() + "  BMI:" + log.getBmi() + "  DateTime:" + log.getDateTime());

            //  }

        } catch (Exception e) {
            Log.i("Thisexcept", e.toString());
        }


        mAdapter.notifyDataSetChanged();
    }

    /**
     * Created by ubuntuadmin on 8/9/17.
     */

}
