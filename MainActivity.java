package com.android.iway.codersinc;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.iway.codersinc.Contests.CollegeContests;
import com.android.iway.codersinc.Contests.ConnectivityReceiver;
import com.android.iway.codersinc.Contests.Contact;
import com.android.iway.codersinc.Contests.CurrentContests;
import com.android.iway.codersinc.Contests.DatabaseHandler;
import com.android.iway.codersinc.Contests.Dialoguebox1;
import com.android.iway.codersinc.Contests.MyApplication;
import com.android.iway.codersinc.Contests.UpcommingContests;
import com.android.iway.codersinc.Contests.config;
import com.android.iway.codersinc.Drawer.graph.GraphActivity;
import com.android.iway.codersinc.LoginSignup.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener,ConnectivityReceiver.ConnectivityReceiverListener, SwipeRefreshLayout.OnRefreshListener {

    // Declaring Your View and Variables
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public int c=0;
    SQLiteDatabase db1;
    CharSequence Titles[] = {"Current", "Upcoming", "College"};
    int Numboftabs = 3;
    //listview through jsonparsing
    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv;
    ProgressDialog pdialog;
    public  String sitename;
    public String res;
    public String Handle;
    public MyApplication m;
    private ProgressDialog loading;
    private static ArrayList<DataModel> data;
    public static DatabaseHandler db;
    public CheckBox buttonhac;
    public CheckBox buttonc;
    public CheckBox buttonear;
    public EditText edittext;
    public EditText edittext1;
    public EditText edittext2;
    public static  int counter1=1;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String name, begin_time, end_time, type, begin_date, end_date, link, site;
    private String NAME;
    public CurrentContests c1;
    // URL to get contacts JSON
    private static String url = "http://meghna.net16.net/codechef_json_first.php";
    ArrayList<HashMap<String, String>> contactList;
    private CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener( this);
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        getData();
                                    }
                                }
        );
        db = new DatabaseHandler(this);
        data = new ArrayList<>();

        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        //Intent intent1=new Intent(MainActivity.this,UpcommingContests.class);
        //MainActivity.this.startActivity(intent1);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nvView);
        View hView = navigationView.getHeaderView(0);
        TextView nav_user = (TextView) hView.findViewById(R.id.username);


        // Find our drawer view

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);


        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        Intent intent = getIntent();
        Bundle intentBundle = intent.getExtras();
        NAME = intentBundle.getString("username");
        Log.e("main",NAME+" ");
        nav_user.setText(NAME);
        function();
        function1();
        //coordinatorLayout = (CoordinatorLayout) findViewById(R.id.three_buttons_activity);
       /* BottomBar bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.setItemsFromMenu(R.menu.bottom_bar, new OnMenuTabSelectedListener() {
            @Override
            public void onMenuItemSelected(int itemId) {
                switch (itemId) {
                    case R.id.sort:
                        break;
                    case R.id.filter:
                        break;
                }
            }
        });*/
        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });


    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

//set up view pager all functions

    private void setupViewPager(ViewPager viewPager) {

        TabsPagerAdapter adapter = new TabsPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new CurrentContests(), "Current");
        adapter.addFrag(new UpcommingContests(), "Upcoming");
        adapter.addFrag(new CollegeContests(), "College");
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    //drawer toolbar
    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }


    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
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
            logout();
            return true;
        }
        if(id==R.id.filter){
            Intent intent=new Intent(MainActivity.this,Dialoguebox1.class);
            MainActivity.this.startActivity(intent);
            return true;
        }
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    public void logout(){
                SharedPreferences preferences =getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();

                                editor.commit();
                Toast.makeText(MainActivity.this, "You Have Successfully Logged out", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                 startActivity(intent);

    }
      public void function(){
        for(int i=0;i<3;i++){
            if(i==0){
                sitename = "Codechef";
                res = "false";

                db.addContact1(new Contact(sitename,res));
            }
            if(i==1){
                sitename = "Hackerrank";
                res = "false";

                db.addContact1(new Contact(sitename,res));
            }
            if(i==2){
                sitename = "Hackerearth";
                res = "false";

                db.addContact1(new Contact(sitename,res));
            }

        }
    }
    public void function1(){
        Log.e("lll","function1");
        for(int i=0;i<3;i++){
            if(i==0){
                sitename = "Codechef";
                res = "false";
                Handle="xxxxx";


                db.addContact2(new Contact(sitename,res,Handle));
            }
            if(i==1){
                sitename = "Hackerrank";
                res = "false";
                Handle="xxxxx";

                db.addContact2(new Contact(sitename,res,Handle));
            }
            if(i==2){
                sitename = "Hackerearth";
                res = "false";
                Handle="xxxxx";
                db.addContact2(new Contact(sitename,res,Handle));
            }

        }
    }


    public void getData() {
        swipeRefreshLayout.setRefreshing(true);
       // loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);
        String url = config.DATA_URL.trim();
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              //  loading.dismiss();
                swipeRefreshLayout.setRefreshing(false);
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        swipeRefreshLayout.setRefreshing(false);
                       //  Toxast.makeText(this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void showJSON(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(config.JSON_ARRAY);
            if(db.counter==0){

            }
            else{
                counter1=db.counter;

            }

            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);
                name = collegeData.getString(config.KEY_NAME);
                type = collegeData.getString(config.KEY_TYPE);
                site = collegeData.getString(config.KEY_SITE);
                begin_date = collegeData.getString(config.KEY_BEGINDATE);
                begin_time = collegeData.getString(config.KEY_BEGINTIMING);
                end_date = collegeData.getString(config.KEY_ENDDATE);
                end_time = collegeData.getString(config.KEY_ENDTIMING);
                link = collegeData.getString(config.KEY_LINK);

                    db.addContact(new Contact(name, type, site, begin_date, begin_time, end_date, end_time, link));



             }
            db.counter=db.counter+1;


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    public void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;
            //getData();
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
            Log.e("main","else");
        }
        Snackbar snackbar = Snackbar.make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();

    }

    @Override
    public void onResume() {
        super.onResume();
        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
        checkConnection();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        Log.e("qqq", "connected");
        showSnack(isConnected);

    }

  /*  @Override
    public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.navprofile) {
                return true;
                // Handle the camera action
            } else if (id == R.id.navmanage) {
                return true;

            } else if (id == R.id.navgraph) {
                Log.e("main", "navgraph");

                graph();
                return true;


            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        //super.onOptionsItemSelected(item);
            return onOptionsItemSelected(item);
        }*/
  //select the drawer
  public void selectDrawerItem(MenuItem menuItem) {
      // Create a new fragment and specify the fragment to show based on nav item clicked
      // Fragment fragment = null;
      // Class fragmentClass;
       switch (menuItem.getItemId()) {
            case R.id.navprofile:
               /* Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                MainActivity.this.startActivity(intent);*/
                //setTitle(menuItem.getTitle());
                break;
            case R.id.navmanage:
                //Intent intent1 = new Intent(MainActivity.this, ManageHandlesActivity.class);
                //MainActivity.this.startActivity(intent1);
                //setTitle(menuItem.getTitle());
                showInputDialog();
                break;
            case R.id.navgraph:
                Intent intent2 = new Intent(MainActivity.this, GraphActivity.class);
                MainActivity.this.startActivity(intent2);
                setTitle(menuItem.getTitle());
                break;
        }

        try {
            Intent intent = new Intent(MainActivity.this, GraphActivity.class);
            //MainActivity.this.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

      // Insert the fragment by replacing any existing fragment
      //  FragmentManager fragmentManager = getSupportFragmentManager();
      //fragmentManager.beginTransaction().replace(R.id.tab_pager, fragment).commit();

      // Highlight the selected item has been done by NavigationView
      menuItem.setChecked(true);
      // Set action bar title

      // Close the navigation drawer
      mDrawer.closeDrawers();
  }



    public void showInputDialog() {

        // get prompts.xml view
        Log.e("kkkkkkk","input");
        LayoutInflater layoutInflater = LayoutInflater.from(this);


        View promptView = layoutInflater.inflate(R.layout.manager, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.editText);
        final EditText editText1 = (EditText) promptView.findViewById(R.id.editText1);
        final EditText editText2 = (EditText) promptView.findViewById(R.id.editText2);
        Log.d("response", editText.getText().toString());

        // setup a dialog window
       // buttonhac=(CheckBox)promptView.findViewById(R.id.hackerrank);
       // buttonc=(CheckBox)promptView.findViewById(R.id.codechef);
        //buttonear=(CheckBox)promptView.findViewById(R.id.hackerearth);




        List<Contact> contactssite = db.getAllContacts2();
        for (Contact cn : contactssite) {
            Log.e("lol","bho");
            String log = "Sitename: " + cn.getSitename() + "\nHandle:\t" + cn.getHandle();
            // Writing Contacts to log
            Log.d("Name: ", log);
            //   textViewResult.setText("SiteName:\t" + cn.getSitename());
            if(cn.getRes().equals("true")){
                Log.e("ll","gg");
//                buttonc=(CheckBox)findViewById(R.id.codechef);

                if(cn.getSitename().equals("Codechef")) {
                   // buttonc.setChecked(true);
                    editText.setText(cn.getHandle());
                    Log.e("jj", "hhh");
                }
                else if(cn.getSitename().equals("Hackerrank")){
                    //buttonhac.setChecked(true);
                    editText1.setText(cn.getHandle());
                }
                else if(cn.getSitename().equals("Hackerearth")){
                   // buttonear.setChecked(true);
                    editText2.setText(cn.getHandle());
                }
            }
        }

        //db.onUpgrade1(eventsManager);
        //db.onCreate1(s);
        Log.e("kkk","ddd");
        //addListenerOnChkIos();
        final AlertDialog.Builder builder = alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public String NULL = "  ";

                    public void onClick(DialogInterface dialog, int id) {
                        //  addListenerOnChkIos();
                        if (editText.getText().toString() != NULL) {
                            Log.e("lll", "hhh");
                            //buttonc=(CheckBox)findViewById(R.id.codechef);
                            //buttonc.setChecked(true);
                            sitename = "Codechef";
                            res = "true";
                            Handle = editText.getText().toString();


                            Log.e("nnn1111", Handle);
                            //  System.out.println(c);
                            db.updateContact2(new Contact(sitename, res, Handle));
                            //   c++;


                        }
                        if (editText.getText().toString() == NULL) {
                            Log.e("xxx", "www");
                            sitename = "Codechef";
                            res = "false";
                            Handle = "  ";
                            c = 1;
                            // db.addContact1(new Contact(sitename, res));


                            db.updateContact2(new Contact(sitename, res, Handle));
                        }
                        if (editText1.getText().toString() != NULL) {
                            Log.e("kkk", "fff");
                            Log.e("lll", "hhh");
                            //buttonc=(CheckBox)findViewById(R.id.codechef);
                            //buttonc.setChecked(true);
                            sitename = "Hackerrank";
                            res = "true";
                            Handle = editText1.getText().toString();
                            Log.e("nnn", "pehla");
                            System.out.println(c);
                            db.updateContact2(new Contact(sitename, res, Handle));
                            //   c++;

                            List<Contact> contactssite = db.getAllContacts1();

                           /* for (Contact cn1 : contactssite) {
                                String log = "Sitename: " + cn1.getSitename() + "\nResponse:\t" + cn1.getRes();
                                // Writing Contacts to log
                                Log.d("Name: ", log);
                                //   textViewResult.setText("SiteName:\t" + cn.getSitename());


                                if (cn1.getRes().equals("false")) {
                                    //         c++;
                                    Log.e("dial", "update");
                                    System.out.println(c);
                                    db.updateContact(new Contact(sitename, res));
                                }
                            }*/

                            Log.d("Reading: ", "Reading all contacts..");
                            // List<Contact> contactssite = db.getAllContacts1();
                            Log.e("ddddd", "hello555555");

                         /*   for (Contact cn1 : contactssite) {
                                String log = "Sitename: " + cn1.getSitename() + "\nResponse:\t" + cn1.getRes();
                                // Writing Contacts to log
                                Log.d("Name: ", log);
                             //   textViewResult.setText("SiteName:\t" + cn.getSitename());
                            }*/
                            //Intent intent=new Intent(Dialoguebox1.this,MainActivity.class);
                            //Dialoguebox1.this.startActivity(intent);

                        }
                        if (editText1.getText().toString() == NULL) {
                            Log.e("xxx", "www");
                            sitename = "Hackerrank";
                            res = "false";
                            Handle = "  ";
                            c = 2;
                            // db.addContact1(new Contact(sitename, res));


                            db.updateContact2(new Contact(sitename, res, Handle));
                        }
                        if (editText2.getText().toString() != NULL) {

                            Log.e("lll", "hhh");
                            //buttonc=(CheckBox)findViewById(R.id.codechef);
                            //buttonc.setChecked(true);
                            sitename = "Hackerearth";
                            res = "true";
                            Handle = editText2.getText().toString();
                            Log.e("nnn", "pehla");
                            System.out.println(c);
                            db.updateContact2(new Contact(sitename, res, Handle));
                            //   c++;

                            List<Contact> contactssite = db.getAllContacts1();
/*
                            for (Contact cn1 : contactssite) {
                                String log = "Sitename: " + cn1.getSitename() + "\nResponse:\t" + cn1.getRes();
                                // Writing Contacts to log
                                Log.d("Name: ", log);
                                //   textViewResult.setText("SiteName:\t" + cn.getSitename());


                                if (cn1.getRes().equals("false")) {
                                    //         c++;
                                    Log.e("dial", "update");
                                    System.out.println(c);
                                    db.updateContact(new Contact(sitename, res));
                                }
                            }*/

                            Log.d("Reading: ", "Reading all contacts..");
                            // List<Contact> contactssite = db.getAllContacts1();
                            Log.e("ddddd", "hello555555");




                        }
                        if (editText2.getText().toString() == NULL) {

                            Log.e("xxx", "www");
                            sitename = "Hackerearth";
                            res = "false";
                            Handle = "  ";
                            c = 3;
                            // db.addContact1(new Contact(sitename, res));


                            db.updateContact2(new Contact(sitename, res, Handle));
                            //Intent intent=new Intent(Dialoguebox1.this,MainActivity.class);
                            //Dialoguebox1.this.startActivity(intent);

                        }

//                        resultText.setText("Hello, " );
                        //                      Log.e("kk","ll");
                        //install();
                        //  StringBuffer result = new StringBuffer();
                        //result.append("IPhone check : ").append(buttonhac.isChecked());


                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                //Intent intent = new Intent(this, MainActivity.class);
                                //this.startActivity(intent);


                            }

                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();

        alert.show();

        //addListenerOnChkIos();
    }

    @Override
    public void onRefresh() {
        Log.e("lolol","ghussa");
      //  db1.execSQL("DROP TABLE IF EXISTS " + db.TABLE_EVENTS);
        //db.onCreate(db1);

        getData();

    }
}






