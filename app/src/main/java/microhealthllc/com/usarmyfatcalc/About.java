package microhealthllc.com.usarmyfatcalc;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.github.clans.fab.FloatingActionButton;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import butterknife.ButterKnife;
import microhealthllc.com.usarmyfatcalc.R;

/**
 * Created by ubuntuadmin on 3/24/17.
 */

public class About  extends AppCompatActivity {


    private RecyclerView recyclerView;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    Button twitterview;
    Button websitetxt;
    Button privacypolicy;
    Button termsofUse;
    FloatingActionButton home;

    FloatingActionButton info;
    FloatingActionButton history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        home = (FloatingActionButton) findViewById(R.id.home) ;
        history = (FloatingActionButton) findViewById(R.id.loghistory);
        info = (FloatingActionButton)findViewById(R.id.info);

        twitterview = (Button) findViewById(R.id.followus);
        websitetxt =(Button) findViewById(R.id.webaddress);
        privacypolicy =(Button) findViewById(R.id.privacyp);
        termsofUse = (Button) findViewById(R.id.termsofuse);


        twitterview.setText("@microhealthtalk");
        twitterview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURl("https://twitter.com/MicroHealthTalk");
            }
        });
        websitetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURl("https://www.microhealthllc.com/");
            }
        });
        termsofUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURl("https://www.microhealthllc.com/about/terms-of-use/");

            }
        });
        privacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gotoURl("https://www.microhealthllc.com/about/privacy-policy/");

            }
        });

        ButterKnife.bind(this);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("");

        }



        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is = new Intent(About.this, LogHistory.class);
                startActivity(is);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(About.this, MainActivity.class);
                startActivity(i);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent(About.this, Info.class);
                startActivity(info);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onContextItemSelected(MenuItem item) {
        onOptionsItemSelected(item);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*switch(item.getItemId()){

        }*/
        return super.onOptionsItemSelected(item);
    }

    private void gotoURl(String url){

        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);

    }




    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();

    }




    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }




}
