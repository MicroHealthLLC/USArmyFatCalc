package microhealthllc.com.usarmyfatcalc;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

public class Info extends AppCompatActivity {

    FloatingActionButton home;
    FloatingActionButton about;
TextView src;
    FloatingActionButton history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    src = (TextView) findViewById(R.id.src);
src.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      gotoURl("https://goo.gl/QgQjTz");
    }
});
        home = (FloatingActionButton) findViewById(R.id.home) ;
        about = (FloatingActionButton)findViewById(R.id.about);


        history = (FloatingActionButton) findViewById(R.id.loghistory);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is = new Intent(Info.this, LogHistory.class);
                startActivity(is);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Info.this, MainActivity.class);
                startActivity(i);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Info.this, About.class);
                startActivity(in);
            }
        });

    }
    private void gotoURl(String url){

        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
