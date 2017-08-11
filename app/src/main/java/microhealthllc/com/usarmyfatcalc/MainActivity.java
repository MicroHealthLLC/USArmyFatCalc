package microhealthllc.com.usarmyfatcalc;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import microhealthllc.com.usarmyfatcalc.DB.BmiLogs;
import microhealthllc.com.usarmyfatcalc.DB.DBHandler;
import microhealthllc.com.usarmyfatcalc.chart.ColorArcProgressBar;

public class MainActivity extends AppCompatActivity {

    EditText age;
    EditText hieght_ft;
    EditText  hieght_in;
    EditText neck_ft;
    EditText neck_in;
    EditText waist_ft;
    EditText waist_in;

    TextView errortext;
    int user_age;
    double heightfeets;
    double heightInches;
    double neckfeets;
    double neckInches;
    double waistfeets;
    double waistInches;

    double hipfeets;
    double hipInches;
    LinearLayout hiplayout;

    double value_heights;
    double value_waists;
    double value_necks;
    double value_hips;
    // female only
    EditText hip_ft;
    EditText hip_in;
    Button calc;
    RadioGroup malefemale;
    public boolean isFemale;
    DBHandler db;
    double results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hiplayout   = (LinearLayout)findViewById(R.id.hip_layout);
        age = (EditText) findViewById(R.id.years);
        hieght_ft = (EditText) findViewById(R.id.height_feets);
        hieght_in =(EditText) findViewById(R.id.height_inches);
        neck_ft = (EditText) findViewById(R.id.neck_feets);
        neck_in = (EditText) findViewById(R.id.neck_inches);
        waist_ft = (EditText) findViewById(R.id.waist_feets);
        waist_in =(EditText) findViewById(R.id.waist_inches);
        hip_ft = (EditText) findViewById(R.id.hip_feets);
        hip_in = (EditText) findViewById(R.id.hip_inches);
        malefemale = (RadioGroup) findViewById(R.id.gender);
        errortext = (TextView) findViewById(R.id.error_check);
        errortext.setVisibility(View.GONE);
        db  = new DBHandler(this);
        calc = (Button) findViewById(R.id.calc);
        malefemale.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.male){
                    isFemale =false;
                  hiplayout.setVisibility(View.GONE);
                  //  validateInput(isInputValid());
                }
                else{
                    isFemale = true;
                    hiplayout.setVisibility(View.VISIBLE);
                   // validateInput(isInputValid());
                }
            }
        });


            validateInput(isInputValid());
            calc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        user_age = Integer.parseInt(age.getText().toString());
        heightfeets = Double.parseDouble(hieght_ft.getText().toString());
        heightInches = Double.parseDouble(hieght_in.getText().toString());

         neckfeets = Double.parseDouble(neck_ft.getText().toString());

         neckInches = Double.parseDouble(neck_in.getText().toString());

         waistfeets = Double.parseDouble(waist_ft.getText().toString());
         waistInches = Double.parseDouble(waist_in.getText().toString());



    if(hip_ft.getText().length() !=0 && hip_in.getText().length()!=0) {
    hipfeets = Double.parseDouble(hip_ft.getText().toString());
    hipInches = Double.parseDouble(hip_in.getText().toString());
}

else {
    hipfeets = 0.0;
    hipInches = 0.0;
}

        value_heights = (heightfeets *12)+(heightInches);
        value_necks = (neckfeets *12)+ (neckInches);
        value_waists = (waistfeets *12)+ (waistInches);
        value_hips = (hipfeets *12)+(hipInches);

        Log.i("height :",value_heights+"");
        Log.i("necks:",value_necks+"");
        Log.i("waist:",value_waists+"");
        Log.i("hips:",value_hips+"");

      results   = calcfat(value_heights,value_necks,value_waists,value_hips,user_age,isFemale);
        Log.i("Results", results+"");

        analysis(isFemale,results,user_age,value_heights,value_necks,value_waists,value_hips,user_age);
       /* if (results<=0 ||Double.isNaN(results)){
           // errortext.setVisibility(View.VISIBLE);

            results=0;
            Intent bo = new  Intent(MainActivity.this, FatDangerAnger.class);
            double values[] = new double[4];
            values[0]=  value_heights;
            values[1] = value_waists;
            values[2] = value_necks;
            values[3] = value_hips;
            DB_ENTRY(results,value_waists,value_heights,value_necks);
            bo.putExtra("bodyfat",results);
            bo.putExtra("values",values);
            bo.putExtra("age",user_age);
            bo.putExtra("isfemale",isFemale);
            startActivity(bo);
        }
        else {
            errortext.setVisibility(View.GONE);

            Intent bo = new  Intent(MainActivity.this, FatCalActivity.class);
            double values[] = new double[4];
            values[0]=  value_heights;
            values[1] = value_waists;
            values[2] = value_necks;
            values[3] = value_hips;
            DB_ENTRY(results,value_waists,value_heights,value_necks);
            bo.putExtra("bodyfat",results);
            bo.putExtra("values",values);
            bo.putExtra("age",user_age);
            bo.putExtra("isfemale",isFemale);
            startActivity(bo);
        }
*/
    }
});


    }
public boolean isInputValid(){
    return (age.getText().length() >0 && hieght_ft.getText().length()>0 && hieght_in.getText().length()>0 && neck_ft.getText().length()>0 && neck_in.getText().length()>0 && waist_ft.getText().length()>0 &&  waist_in.getText().length() >0);
}
    public boolean isInputValidF(){
        return (age.getText().length() >0 && hieght_ft.getText().length()>0 && hieght_in.getText().length()>0 && neck_ft.getText().length()>0 && neck_in.getText().length()>0 && waist_ft.getText().length()>0 &&  waist_in.getText().length() >0 && hip_in.getText().length()>0 && hip_ft.getText().length()>0);
    }

    public void validateInput(final boolean validate){
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (isInputValid()) {
                    calc.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() == 0){
                    calc.setVisibility(View.GONE);
                }



            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isInputValid()) {
                    calc.setVisibility(View.VISIBLE);
                }
            }
        };

        age.addTextChangedListener(watcher);
        hieght_in.addTextChangedListener(watcher);
        hieght_ft.addTextChangedListener(watcher);
        neck_ft.addTextChangedListener(watcher);
        neck_in.addTextChangedListener(watcher);
        waist_in.addTextChangedListener(watcher);
        waist_ft.addTextChangedListener(watcher);
        hip_ft.addTextChangedListener(watcher);
        hip_in.addTextChangedListener(watcher);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());
        popup.show();
    }


    public double calcfat(double hieght, double neck, double waist, double hip, int age, boolean isfemale){
        double bodyfat= 0.0;
        if (isfemale){
            //female
           bodyfat = 163.205 *Math.log10(waist +hip - neck) - (97.684*Math.log10(hieght))- 78.387;



        }
        else{
            //male
            bodyfat = (86.010 *(Math.log10(waist-neck))) - (70.041 * Math.log10(hieght))+ 36.76;

        }
        return bodyfat;
    }

    public void analysis(Boolean isfemale, double bodyfat, double user_age,double height, double neck, double waist, double hip,int age){
        //If is female is true



        if(isfemale){
            if(((user_age >=17 && user_age <=27)&&(bodyfat <=36))|| ((user_age >=28 && user_age <=39)&&(bodyfat <=34))|| ((user_age >=40)&&(bodyfat <=36))){
                Intent bo = new  Intent(MainActivity.this, FatCalActivity.class);
                double values[] = new double[4];
                values[0]=  height;
                values[1] = waist;
                values[2] = neck;
                values[3] = hip;

                if(!(bodyfat >0)){
                    bodyfat = 0.0;
                }
                DB_ENTRY(bodyfat,waist,height,neck);
                bo.putExtra("bodyfat",bodyfat);
                bo.putExtra("values",values);
                bo.putExtra("age",user_age);
                bo.putExtra("isfemale",isFemale);
                startActivity(bo);
            }

            else{

                Intent bo = new  Intent(MainActivity.this, FatDangerAnger.class);
                double values[] = new double[4];
                values[0]=  height;
                values[1] = waist;
                values[2] = neck;
                values[3] = hip;

                if(!(bodyfat >0)){
                    bodyfat = 0.0;
                }
                DB_ENTRY(bodyfat,waist,height,neck);
                bo.putExtra("bodyfat",bodyfat);
                bo.putExtra("values",values);
                bo.putExtra("age",user_age);
                bo.putExtra("isfemale",isFemale);
                startActivity(bo);

            }
        }
        else{
            if(((user_age >=17 && user_age <=27)&&(bodyfat <=26))|| ((user_age >=28 && user_age <=39)&&(bodyfat <=28))|| ((user_age >=40)&&(bodyfat <=30))){
                Intent bo = new  Intent(MainActivity.this, FatCalActivity.class);
                if(!(bodyfat >0)){
                    bodyfat = 0.0;
                }
                double values[] = new double[4];
                values[0]=  height;
                values[1] = waist;
                values[2] = neck;
                values[3] = hip;
                DB_ENTRY(bodyfat,waist,height,neck);
                bo.putExtra("bodyfat",bodyfat);
                bo.putExtra("values",values);
                bo.putExtra("age",user_age);
                bo.putExtra("isfemale",isFemale);
                startActivity(bo);
            }

            else{

                if(!(bodyfat >0)){
                    bodyfat = 0.0;
                }
                Intent bo = new  Intent(MainActivity.this, FatDangerAnger.class);
                double values[] = new double[4];
                values[0]=  height;
                values[1] = waist;
                values[2] = neck;
                values[3] = hip;
                DB_ENTRY(bodyfat,waist,height,neck);
                bo.putExtra("bodyfat",bodyfat);
                bo.putExtra("values",values);
                bo.putExtra("age",user_age);
                bo.putExtra("isfemale",isFemale);
                startActivity(bo);

            }

        }
    }


    public void DB_ENTRY(Double results, Double waist, Double height, Double neck){


        try {
            Log.i("LOG DATETIME","DB date and time: "+db.getLast().getDateTime()+", curr date time: "+getDateTime()+"\n");

            if(db.getLast().getDateTime()==null) {

                db.addLog(new BmiLogs(String.format("%.1f", results), String.format("%.1f",waist), String.format("%.1f", neck),String.format("%.1f",height), getDateTime()));

            }
            else if(db.getLast().getDateTime().equals(getDateTime())){
                db.updateLastEntry(db.getLast().getId(),String.format("%.1f", results), String.format("%.1f",waist), String.format("%.1f", neck),String.format("%.1f",height), getDateTime());
            }


            else {
                db.addLog(new BmiLogs(String.format("%.1f", results), String.format("%.1f",waist), String.format("%.1f", neck),String.format("%.1f",height), getDateTime()));

            }
        }
        catch(Exception e){
            Log.d("Error  Exception",e.toString());
        }
    }


    public String getDateTime() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EE MM/dd", Locale.getDefault());
        //Date date = new Date();
        return dateFormat.format(date);
    }

}
