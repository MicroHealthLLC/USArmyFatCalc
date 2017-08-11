package microhealthllc.com.usarmyfatcalc.DB;

/**
 * Created by ubuntuadmin on 3/13/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME ="BMILogs";
    // Contacts table name
    private static final String TABLE_BMILOGS ="LOGS";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_BODYFAT = "bodyfat";
    private static final String KEY_WAIST ="waist";
    private static final String KEY_DATETIME = "datetime";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_NECK = "neck";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BMILOG_TABLE = "CREATE TABLE "+ TABLE_BMILOGS+ " ( " +KEY_ID+ " INTEGER PRIMARY KEY," +KEY_BODYFAT+ " TEXT,"+KEY_WAIST+" TEXT,"+KEY_HEIGHT+" TEXT,"+KEY_NECK+ " TEXT,"+KEY_DATETIME+ " TEXT"+" )";
        db.execSQL(CREATE_BMILOG_TABLE);
                ;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_BMILOGS);
// Creating tables again
        onCreate(db);
    }

    // Adding new shop
    public void addLog(BmiLogs log) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BODYFAT, log.getBodyfat()); // Shop Name
        values.put(KEY_WAIST, log.getWaist()); // Shop Phone Number
        values.put(KEY_HEIGHT,log.getHeight());
        values.put(KEY_NECK,log.getNeck());
        values.put(KEY_DATETIME, log.getDateTime());
// Inserting Row
        db.insert(TABLE_BMILOGS, null, values);
        db.close(); // Closing database connection
    }


    // Getting one shop
    public BmiLogs getLog(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BMILOGS, new String[] { KEY_ID,
                        KEY_BODYFAT, KEY_WAIST,KEY_HEIGHT,KEY_NECK,KEY_DATETIME }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        BmiLogs log = new BmiLogs(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
// return shop
        return log;
    }

 public void deleteEntry(){
     SQLiteDatabase db = this.getReadableDatabase();
     db.execSQL("delete from "+ TABLE_BMILOGS);

 }

    // Getting All Shops
    public List<BmiLogs> getAllShops() {
        List<BmiLogs> logList = new ArrayList<BmiLogs>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_BMILOGS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BmiLogs log = new BmiLogs();
                log.setId(Integer.parseInt(cursor.getString(0)));
                log.setBodyfat(cursor.getString(1));
                log.setWaist(cursor.getString(2));
                log.setHieght(cursor.getString(3));
                log.setNeck(cursor.getString(4));
                log.setDateTime(cursor.getString(5));

// Adding contact to list
                logList.add(log);
            } while (cursor.moveToNext());
        }
// return contact list
        return logList;
    }

    public BmiLogs getLast(){
      //  BmiLogs bmiLog = new BmiLogs();
        String selectQuery = "SELECT * FROM " + TABLE_BMILOGS;
        BmiLogs bmiLog = new BmiLogs();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToLast())  {

            bmiLog.setId(Integer.parseInt(cursor.getString(0)));
            bmiLog.setBodyfat(cursor.getString(1));
            bmiLog.setWaist(cursor.getString(2));
            bmiLog.setNeck(cursor.getString(3));
            bmiLog.setHieght(cursor.getString(4));
            bmiLog.setDateTime(cursor.getString(5));

        };
                return bmiLog;
    }
    public void updateLastEntry(long rowId, String bodyfat, String waist, String height, String neck,String datetime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(KEY_BODYFAT,bodyfat);
        args.put(KEY_WAIST, waist);
        args.put(KEY_NECK, neck);
        args.put(KEY_HEIGHT, height);
        args.put(KEY_DATETIME, datetime);
        db.update(TABLE_BMILOGS,args,"id="+rowId,null);
    }

    // Getting shops Count
    public int getLogsCount() {
        String countQuery = "SELECT * FROM " + TABLE_BMILOGS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
// return count
        return cursor.getCount();
    }

    // Updating a shop
    public int updateShop(BmiLogs log) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BODYFAT, log.getBodyfat());
        values.put(KEY_WAIST, log.getWaist());
        values.put(KEY_NECK, log.getNeck());
        values.put(KEY_HEIGHT, log.getHeight());
        values.put(KEY_DATETIME,log.getDateTime());
// updating row
        return db.update(TABLE_BMILOGS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(log.getId())});
    }

    // Deleting a shop
    public void deleteShop(BmiLogs log) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BMILOGS, KEY_ID + " = ?",
                new String[] { String.valueOf(log.getId()) });
        db.close();
    }


}
