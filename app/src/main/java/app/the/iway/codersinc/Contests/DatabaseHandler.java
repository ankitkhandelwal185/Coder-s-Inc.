package app.the.iway.codersinc.Contests;

/**
 * Created by Shashank on 25-Sep-16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import app.the.iway.codersinc.MainActivity;

import java.util.ArrayList;
import java.util.List;

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
    private static final String TABLE_EVENTS3= "events3";
    private static  final  String TABLE_EVENTS4="events4";
    private static  final  String TABLE_EVENTS5="events5";
    private static  final  String TABLE_EVENTS7="events7";
    private static  final  String TABLE_EVENTS8="events8";
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
    private static  final String KEY_CONTESTID="Contestid";
    private static  final String KEY_INDEX1=  "Index1";
    private static  final String KEY_TAGS="Tags";
    private static  final String KEY_URL="Url";
    private static final String KEY_NAME1= "Name";
    private static  final String KEY_ACCURACY="Accuracy";
    private static final String KEY_MYPROBLEM="Myproblem";

    public  static  int counter=0;
    public static MainActivity obj;
    public  static final String KEY_USERNAME = "Username";
    public static final String KEY_IMAGE = "image_data";




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
        Log.e("oncreate", "dvz");
        String CREATE_EVENTS_TABLE1 = "CREATE TABLE " + TABLE_EVENTS1 + "("
                + KEY_SITENAME + " STRING PRIMARY KEY, " +  KEY_RES + " TEXT " + " )";
        db. execSQL(CREATE_EVENTS_TABLE1);
        Log.e("oncreate", "dddddddddddd");
        String CREATE_EVENTS_TABLE2 = "CREATE TABLE " + TABLE_EVENTS2 + "("
                + KEY_SITENAME + " STRING PRIMARY KEY, " + KEY_RES + " TEXT ," +  KEY_HANDLE + " TEXT " + " )";
        db. execSQL(CREATE_EVENTS_TABLE2);
        String CREATE_EVENTS_TABLE3 = "CREATE TABLE " + TABLE_EVENTS3 + "("
                + KEY_USERNAME + " STRING PRIMARY KEY,"  + KEY_IMAGE  +  " BLOB ,"   + KEY_RES + " TEXT ,"  + KEY_SITENAME + " TEXT ,"
                + KEY_URL + " TEXT " + ")";
        db.execSQL(CREATE_EVENTS_TABLE3);

        String CREATE_EVENTS_TABLE4 = "CREATE TABLE " + TABLE_EVENTS4 + "("
                + KEY_NAME1 + " STRING PRIMARY KEY,"  + KEY_CONTESTID  +  " TEXT ,"   + KEY_INDEX1 + " TEXT ,"  + KEY_TAGS + " TEXT ,"
                + KEY_URL + " TEXT " + ")";
        db.execSQL(CREATE_EVENTS_TABLE4);
        String CREATE_EVENTS_TABLE5 = "CREATE TABLE " + TABLE_EVENTS5 + "("
                + KEY_NAME1 + " STRING PRIMARY KEY,"
                + KEY_TAGS + " TEXT " + ")";
        db.execSQL(CREATE_EVENTS_TABLE5);

        String CREATE_EVENTS_TABLE7 = "CREATE TABLE " + TABLE_EVENTS7 + "("
                + KEY_NAME + " STRING PRIMARY KEY," + KEY_TYPE + " TEXT ," + KEY_SITE + " TEXT  ," + KEY_BEGINDATE + " TEXT ," +  KEY_BEGIN_TIMING + " TEXT," + KEY_ENDDATE + " TEXT ," +
                KEY_END_TIMING + " TEXT," + KEY_LINK + " TEXT " + ")";
        db.execSQL(CREATE_EVENTS_TABLE7);
        String CREATE_EVENTS_TABLE8 = "CREATE TABLE " + TABLE_EVENTS8 + "("
                + KEY_MYPROBLEM + " STRING PRIMARY KEY, " + KEY_NAME1 + " TEXT ," +  KEY_ACCURACY + " TEXT " + " )";
        db. execSQL(CREATE_EVENTS_TABLE8);


        // onCreate1(db);

    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //     // Drop older table if existed
        Log.e("onupgrade","hello");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS4);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS5);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS7);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS8);
        Log.e("chgjkl","Sfvs");
        // Create tables again
        onCreate(db);
        //onCreate1(db);
    }
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding ne
    // w contact
    public void drop(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS1);
    }
    public void addEntry( Contact contact)  {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("vhbjnkl;l","nnnnnn");
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, contact.getUsername()); // Contact Name
        values.put(KEY_IMAGE,contact.getImage());
        values.put(KEY_RES,contact.getRes());
        values.put(KEY_SITENAME,contact.getSitename());
        //values.put(KEY_BEGIN_TIMING, contact.getBegin());
        //values.put(KEY_ENDDATE,contact.getEnddate());
        //values.put(KEY_END_TIMING,contact.getEnd());
        //values.put(KEY_LINK,contact.getLink());*/
        Log.d("image",contact.getUsername());
        // Inserting Row
        db.insert(TABLE_EVENTS3, null, values);
        db.close();
        Log.e("aaaaaa;1","shashank55555");
        // Closing database connection

    }
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
        values.put(KEY_LINK, contact.getLink());

        // Inserting Row
      // counter=counter+1;
         db.insert(TABLE_EVENTS, null, values);
      obj.counter1=obj.counter1+1;
        db.close();
        Log.e("aaaaaa;1","sha");
        // Closing database connection
    }
    public   void addContact7(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        if(counter==obj.counter1) {
            System.out.print(counter);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS7);
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
        values.put(KEY_LINK, contact.getLink());

        // Inserting Row
        // counter=counter+1;
        db.insert(TABLE_EVENTS7, null, values);
        obj.counter1=obj.counter1+1;
        db.close();
        Log.e("aaaaaa;1","sha");
        // Closing database connection
    }
    public   void addContact4(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("vhbjnkl;l", "lebhai ho gaya");
        ContentValues values = new ContentValues();
        values.put(KEY_NAME1, contact.getname()); // Contact Name
        values.put(KEY_CONTESTID,contact.getContestid());
        values.put(KEY_INDEX1,contact.getIndex());
        values.put(KEY_TAGS,contact.getTags());
        values.put(KEY_URL, contact.getUrl());
        Log.d("addCon", "" + contact.getname() + "  " + contact.contestid);
        db.insert(TABLE_EVENTS4, null, values);
        //obj.counter1=obj.counter1+1;
        db.close();
        Log.e("aaaaaa;1","naya naya");
        // Closing database connection
    }
   public void addContact1(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("vhbjnkl;l", "yashll");
        ContentValues values = new ContentValues();
        values.put(KEY_SITENAME, contact.getSitename()); // Contact Name
        values.put(KEY_RES, contact.getRes());
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
        // Inserting Row
        db.insert(TABLE_EVENTS2, null, values);
        db.close();
        Log.e("aaaaaa;1","shashank");
        // Closing database connection
    }
    public void addContact5(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("vhbjnkl;l", "ssss");
        ContentValues values = new ContentValues();
        values.put(KEY_NAME1, contact.getname());
        values.put(KEY_TAGS,contact.getTags());// Contact Name
        // Inserting Row
        db.insert(TABLE_EVENTS5, null, values);
        db.close();
        Log.e("aaaaaa;1","shashank5555");
        // Closing database connection
    }
    public void addContact8(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("hello", "myprobdatabase");
        ContentValues values = new ContentValues();
        // Contact Name
        values.put(KEY_MYPROBLEM, contact.getSitename());
        values.put(KEY_NAME1, contact.getRes());
        values.put(KEY_ACCURACY,contact.getHandle());
        Log.e("database11111",contact.getRes());

        // Inserting Row
        db.insert(TABLE_EVENTS8, null, values);
        db.close();
        Log.e("this is it","addmyprob");
        // Closing database connection
    }
    public List<Contact> getAllContacts7() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS7;

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
    public List<Contact> getAllContacts8() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS8;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setMyp(cursor.getString(0));

                contact.setAccuracy(cursor.getString(1));
                contact.setname(cursor.getString(1));

                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

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
    public List<Contact> getAllContacts3() {
        List<Contact> contactList = new ArrayList<Contact>();

        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS3;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.e("while","la");
                Contact contact = new Contact();
                contact.setUsername(cursor.getString(0));
                contact.setImage(cursor.getBlob(1));
                contact.setRes(cursor.getString(2));
                contact.setSitename(cursor.getString(3));
                //contact.setBegindate(cursor.getString(3));
                //contact.setBegin(cursor.getString(4));
                //contact.setEnddate(cursor.getString(5));
                //contact.setEnd(cursor.getString(6));
                //contact.setLink(cursor.getString(7));*/
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public List<Contact> getAllContacts4() {
        List<Contact> contactList = new ArrayList<Contact>();
        Log.e("lo","lele");
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS4;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setname(cursor.getString(0));
                contact.setContestid(cursor.getString(1));
                contact.setIndex(cursor.getString(2));
                contact.setTags(cursor.getString(3));
                contact.setUrl(cursor.getString(4));
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
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public List<Contact> getAllContacts5() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS5;
         Log.e("55","55555");

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setname(cursor.getString(0));
                contact.setTags(cursor.getString(1));
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
    public int updateContact3(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.e("image ","updates");

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, contact.getSitename());
        values.put(KEY_IMAGE, contact.getRes());


        // updating row
        return db.update(TABLE_EVENTS3, values, KEY_USERNAME + " = ?",
                new String[] { String.valueOf(contact.getImage()) });
    }
    public int updateContact4(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_CONTESTID, contact.getContestid());
        values.put(KEY_INDEX1, contact.getIndex());
        values.put(KEY_TAGS, contact.getTags());
        values.put(KEY_URL, contact.getUrl());



        // updating row
        return db.update(TABLE_EVENTS4, values, KEY_NAME + " = ?",
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

