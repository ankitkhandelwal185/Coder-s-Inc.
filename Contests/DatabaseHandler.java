package com.android.iway.codersinc.Contests;

/**
 * Created by Shashank on 25-Sep-16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.android.iway.codersinc.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shashank on 24-Sep-16.
 */


public class DatabaseHandler extends SQLiteOpenHelper {





    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    public  static final String DATABASE_NAME = "eventsManager";

    // Contacts table name
    public static final String TABLE_EVENTS = "events";
    private static final String TABLE_EVENTS1="events1";
    private static final String TABLE_EVENTS2="events2";

    // Contacts Table Columns names
    private static final String KEY_NAME= "Name";
    private static final  String KEY_TYPE="Type";
    private static final String KEY_SITE="Site";
    private static final String KEY_BEGINDATE="Begindate";
    private static final String KEY_BEGIN_TIMING = "Begin";
    private static final String KEY_ENDDATE="Enddate";
    private static final String KEY_END_TIMING = "End";
    private static  final String KEY_SITENAME="Sitename";
    private static final String KEY_RES="Res";
    private static final String KEY_HANDLE="Handle";
    public  static  int counter=0;
    public static  MainActivity obj;




    private static final String KEY_LINK="Link";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
public void OnCreate1(SQLiteDatabase db){
    Log.e("sanuek pal","chain");
    String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
            + KEY_NAME + " STRING PRIMARY KEY," + KEY_TYPE + " TEXT ," + KEY_SITE + " TEXT  ," + KEY_BEGINDATE + " TEXT ," +  KEY_BEGIN_TIMING + " TEXT," + KEY_ENDDATE + " TEXT ," +
            KEY_END_TIMING + " TEXT," + KEY_LINK + " TEXT " + ")";
    db.execSQL(CREATE_EVENTS_TABLE);
}
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Log.e("aaaa","shashank");

        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + KEY_NAME + " STRING PRIMARY KEY," + KEY_TYPE + " TEXT ," + KEY_SITE + " TEXT  ," + KEY_BEGINDATE + " TEXT ," +  KEY_BEGIN_TIMING + " TEXT," + KEY_ENDDATE + " TEXT ," +
                KEY_END_TIMING + " TEXT," + KEY_LINK + " TEXT " + ")";
        db.execSQL(CREATE_EVENTS_TABLE);
        Log.e("oncreate","dvz");
        String CREATE_EVENTS_TABLE1 = "CREATE TABLE " + TABLE_EVENTS1 + "("
                + KEY_SITENAME + " STRING PRIMARY KEY, " +  KEY_RES + " TEXT " + " )";
        db. execSQL(CREATE_EVENTS_TABLE1);
        Log.e("oncreate","dddddddddddd");
        String CREATE_EVENTS_TABLE2 = "CREATE TABLE " + TABLE_EVENTS2 + "("
                + KEY_SITENAME + " STRING PRIMARY KEY, " + KEY_RES + " TEXT ," +  KEY_HANDLE + " TEXT " + " )";
        db. execSQL(CREATE_EVENTS_TABLE2);
        // onCreate1(db);

    }
   /* public void onCreate1(SQLiteDatabase db){
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS1 + "("
                + KEY_SITENAME + " STRING PRIMARY KEY," + " )";
       db. execSQL(CREATE_EVENTS_TABLE);
        Log.e("oncreate","dddddddddddd");

    }*/



    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //     // Drop older table if existed
        Log.e("onupgrade","hello");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS2);
        Log.e("chgjkl","Sfvs");
        // Create tables again
        onCreate(db);
        //onCreate1(db);
    }
   /* public void onUpgrade1(SQLiteDatabase db, int oldVersion, int newVersion) {
        //     // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS1);
        Log.e("chgjkl","Sfvsaaaaaaaaaaa");
        // Create tables again
        onCreate1(db);
    }*/

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding ne
    // w contact
  public   void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
      if(counter==obj.counter1) {
          System.out.print(counter);
          db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
          OnCreate1(db);
      }
        Log.e("vhbjnkl;l","yash");
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_TYPE,contact.getType());
        values.put(KEY_SITE,contact.getSite());
        values.put(KEY_BEGINDATE,contact.getBegindate());
        values.put(KEY_BEGIN_TIMING, contact.getBegin());
        values.put(KEY_ENDDATE,contact.getEnddate());
        values.put(KEY_END_TIMING,contact.getEnd());
        values.put(KEY_LINK,contact.getLink());

        // Inserting Row
      // counter=counter+1;
         db.insert(TABLE_EVENTS, null, values);
      obj.counter1=obj.counter1+1;
        db.close();
        Log.e("aaaaaa;1","sha");
        // Closing database connection
    }
   public void addContact1(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("vhbjnkl;l","yashll");
        ContentValues values = new ContentValues();
        values.put(KEY_SITENAME, contact.getSitename()); // Contact Name
        values.put(KEY_RES,contact.getRes());
        /*values.put(KEY_SITE,contact.getSite());
        values.put(KEY_BEGINDATE,contact.getBegindate());
        values.put(KEY_BEGIN_TIMING, contact.getBegin());
        values.put(KEY_ENDDATE,contact.getEnddate());
        values.put(KEY_END_TIMING,contact.getEnd());
        values.put(KEY_LINK,contact.getLink());*/

        // Inserting Row
        db.insert(TABLE_EVENTS1, null, values);
        db.close();
        Log.e("aaaaaa;1","shashank");
        // Closing database connection
    }
    public void addContact2(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("vhbjnkl;l","ssss");
        ContentValues values = new ContentValues();
        values.put(KEY_SITENAME, contact.getSitename());
        values.put(KEY_RES,contact.getRes());// Contact Name
        values.put(KEY_HANDLE,contact.getHandle());
        /*values.put(KEY_SITE,contact.getSite());
        values.put(KEY_BEGINDATE,contact.getBegindate());
        values.put(KEY_BEGIN_TIMING, contact.getBegin());
        values.put(KEY_ENDDATE,contact.getEnddate());
        values.put(KEY_END_TIMING,contact.getEnd());
        values.put(KEY_LINK,contact.getLink());*/

        // Inserting Row
        db.insert(TABLE_EVENTS2, null, values);
        db.close();
        Log.e("aaaaaa;1","shashank");
        // Closing database connection
    }
    // Getting single contact
    /*Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }*/

    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setName(cursor.getString(0));
                contact.setType(cursor.getString(1));
                contact.setSite(cursor.getString(2));
                contact.setBegindate(cursor.getString(3));
                contact.setBegin(cursor.getString(4));
                contact.setEnddate(cursor.getString(5));
                contact.setEnd(cursor.getString(6));
                contact.setLink(cursor.getString(7));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public List<Contact> getAllContacts1() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS1;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setSitename(cursor.getString(0));
                contact.setRes(cursor.getString(1));
                /*contact.setSite(cursor.getString(2));
                contact.setBegindate(cursor.getString(3));
                contact.setBegin(cursor.getString(4));
                contact.setEnddate(cursor.getString(5));
                contact.setEnd(cursor.getString(6));
                contact.setLink(cursor.getString(7));*/
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public List<Contact> getAllContacts2() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS2;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setSitename(cursor.getString(0));
                contact.setRes(cursor.getString(1));
                contact.setHandle(cursor.getString(2));
                /*contact.setSite(cursor.getString(2));
                contact.setBegindate(cursor.getString(3));
                contact.setBegin(cursor.getString(4));
                contact.setEnddate(cursor.getString(5));
                contact.setEnd(cursor.getString(6));
                contact.setLink(cursor.getString(7));*/
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SITENAME, contact.getSitename());
        values.put(KEY_RES, contact.getRes());


        // updating row
        return db.update(TABLE_EVENTS1, values, KEY_SITENAME + " = ?",
                new String[] { String.valueOf(contact.getSitename()) });
    }
    public int updateContact2(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SITENAME, contact.getSitename());
        values.put(KEY_RES, contact.getRes());
        // values.put(KEY_TYPE, contact.getType());
        values.put(KEY_HANDLE,contact.getHandle());


        // updating row
        return db.update(TABLE_EVENTS2, values, KEY_SITENAME + " = ?",
                new String[] { String.valueOf(contact.getSitename()) });
    }
    public int updateContact1(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getSitename());
        values.put(KEY_TYPE, contact.getRes());
         values.put(KEY_SITE, contact.getType());
        values.put(KEY_BEGINDATE, contact.getType());
        values.put(KEY_BEGIN_TIMING, contact.getType());
        values.put(KEY_ENDDATE, contact.getType());

        values.put(KEY_LINK, contact.getType());



        // updating row
        return db.update(TABLE_EVENTS1, values, KEY_SITENAME + " = ?",
                new String[] { String.valueOf(contact.getSitename()) });
    }

    //Deleting single contact*/
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENTS, KEY_NAME + " = ?",
                new String[] { String.valueOf(contact.getName()) });
        db.close();
    }




    // Getting contacts Count
   /* public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }*/

}

