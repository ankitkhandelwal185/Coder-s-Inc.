package app.the.iway.codersinc;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import app.the.iway.codersinc.Contests.CollegeContests;
import app.the.iway.codersinc.Contests.ConnectivityReceiver;
import app.the.iway.codersinc.Contests.Contact;
import app.the.iway.codersinc.Contests.CurrentContests;
import app.the.iway.codersinc.Contests.DatabaseHandler;
import app.the.iway.codersinc.Contests.Dialoguebox1;
import app.the.iway.codersinc.Contests.MyApplication;
import app.the.iway.codersinc.Contests.UpcommingContests;
import app.the.iway.codersinc.Contests.config;
import app.the.iway.codersinc.Drawer.AboutUsActivity;
import app.the.iway.codersinc.Drawer.ProfileActivity;
import app.the.iway.codersinc.Drawer.graph.GraphActivity;
import app.the.iway.codersinc.LoginSignup.LoginActivity;
import app.the.iway.codersinc.recommendation.Recommendation;

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
    private static ArrayList<DataModel> data;
    public static DatabaseHandler db;
    public EditText edittext;
    public static  int counter1=1;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String name, begin_time, end_time, type, begin_date, end_date, link, site;
    private String NAME;
    public CurrentContests c1;
    View vi;
    // URL to get contacts JSON
    private static String url = "http://meghna.net16.net/codechef_json_first.php";
    ArrayList<HashMap<String, String>> contactList;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout ll = (LinearLayout) findViewById(R.id.layout_content);
        vi = inflater.inflate(R.layout.content_main, null);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener( this);
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                         swipeRefreshLayout.setRefreshing(true);
                                        getData();
                                        getData1();

                                    }
                                }
        );
        Fragment fragment=new UpcommingContests();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment,fragment.getClass().getSimpleName()).addToBackStack(null).commit();
        db = new DatabaseHandler(this);
        data = new ArrayList<>();
       // Fragment fragment1=new CurrentContests();
        //getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment1,fragment1.getClass().getSimpleName()).addToBackStack(null).commit();


        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.rgb(55,71,79));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nvView);
        View hView = navigationView.getHeaderView(0);


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


        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                swipeRefreshLayout.setEnabled(false);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        swipeRefreshLayout.setEnabled(true);
                        break;
                }
                return false;
            }
        });
      /*  // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(viewPager);*/

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //getData();
        function();
        function1();

        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
            //CurrentContests.adapter.notifyDataSetChanged();
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
        if(id== R.id.filter){
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
        for(int i=0;i<4;i++){
            if(i==0){
                sitename = "Codechef";
                res = "false";

                db.addContact1(new Contact(sitename, res));
            }
            if(i==1){
                sitename = "Hackerrank";
                res = "false";

                db.addContact1(new Contact(sitename, res));
            }
            if(i==2){
                sitename = "Hackerearth";
                res = "false";

                db.addContact1(new Contact(sitename, res));
            }
           if(i==3){
                sitename = "Codeforces";
                res = "false";

                db.addContact1(new Contact(sitename, res));
            }



        }
    }
    public void function1(){
        Log.e("lll", "function1");
        for(int i=0;i<4;i++){
            if(i==0){
                sitename = "Codechef";
                res = "false";
                Handle="xxxxx";
                db.addContact2(new Contact(sitename, res, Handle));
            }
            if(i==1){
                sitename = "Hackerrank";
                res = "false";
                Handle="xxxxx";
                db.addContact2(new Contact(sitename, res, Handle));
            }
            if(i==2){
                sitename = "Hackerearth";
                res = "false";
                Handle="xxxxx";
                db.addContact2(new Contact(sitename, res, Handle));
            }
            if(i==3){

                sitename="Codeforces";
                res="false";
                Handle="xxxxx";
                db.addContact2(new Contact(sitename,res,Handle));
            }


        }
    }


    public void getData() {
        Log.e("mainactivity","getdata");
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
            Log.e("mainactivity","showjson");
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

         // setContentView(R.layout.content_main);
         //log.xml is your file.
        //TextView tv = (TextView)vi.findViewById(R.id.fab1);
        //tv.setText("hELLLLOO");

       /*Snackbar item = Snackbar.make(findViewById(R.id.fab),message,Snackbar.LENGTH_LONG);
        View sbView = item.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);

        //ll.addView(vi);
        item.show();*/
        //LayoutInflater layoutInflater = LayoutInflater.from(this);
        //View pView = layoutInflater.inflate(R.layout.content_main, null);
        /*LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootview = inflater.inflate(R.layout.content_main,null);*/
        //Snackbar snackbar = Snackbar.make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);


    }



    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        Log.e("mainactivity", "connected");
        showSnack(isConnected);
    }

  //select the drawer
  public void selectDrawerItem(MenuItem menuItem) {
      // Create a new fragment and specify the fragment to show based on nav item clicked
      // Fragment fragment = null;
      // Class fragmentClass;
       switch (menuItem.getItemId()) {
            case R.id.navprofile:
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                MainActivity.this.startActivity(intent);
                //menuItem.setChecked(true);
                //setTitle(menuItem.getTitle());
                break;
            case R.id.navmanage:
                showInputDialog();
                break;
           case R.id.recommendation:
               Intent intent3 = new Intent(MainActivity.this, Recommendation.class);
               MainActivity.this.startActivity(intent3);
               setTitle(menuItem.getTitle());
               break;
           case R.id.aboutus:
               Intent intent4 = new Intent(MainActivity.this, AboutUsActivity.class);
               MainActivity.this.startActivity(intent4);
               setTitle(menuItem.getTitle());
               break;

        }

        try {
            Intent intent = new Intent(MainActivity.this, GraphActivity.class);
            // MainActivity.this.startActivity(intent);
            menuItem.setChecked(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

      // Insert the fragment by replacing any existing fragment
      //  FragmentManager fragmentManager = getSupportFragmentManager();
      //fragmentManager.beginTransaction().replace(R.id.tab_pager, fragment).commit();

      // Highlight the selected item has been done by NavigationView
      menuItem.setChecked(false);
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
        final EditText editText3 = (EditText) promptView.findViewById(R.id.editText3);
        Log.d("response", editText.getText().toString());
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
                else if (cn.getSitename().equals("Codeforces")){
                    editText3.setText(cn.getHandle());
                }

            }
        }

        //db.onUpgrade1(eventsManager);
        //db.onCreate1(s);
        Log.e("kkk", "ddd");
        //addListenerOnChkIos();
         alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public String NULL = "  ";

                    public void onClick(DialogInterface dialog, int id) {
                        //  addListenerOnChkIos();
                        if (editText.getText().toString() != NULL) {

                            sitename = "Codechef";
                            res = "true";
                            Handle = editText.getText().toString();
                            db.updateContact2(new Contact(sitename, res, Handle));
                        }
                        if (editText.getText().toString() == NULL) {
                            Log.e("xxx", "www");
                            sitename = "Codechef";
                            res = "false";
                            Handle = "  ";
                            c = 1;
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
                        }
                            List<Contact> contactssite = db.getAllContacts1();

                            if (editText1.getText().toString() == NULL) {
                                Log.e("xxx", "www");
                                sitename = "Hackerrank";
                                res = "false";
                                Handle = "  ";
                                c = 2;
                                db.updateContact2(new Contact(sitename, res, Handle));
                            }
                            if (editText2.getText().toString() != NULL) {

                                sitename = "Hackerearth";
                                res = "true";
                                Handle = editText2.getText().toString();
                                Log.e("nnn", "pehla");
                                System.out.println(c);
                                db.updateContact2(new Contact(sitename, res, Handle));
                            }
                            if (editText2.getText().toString() == NULL) {

                                Log.e("xxx", "www");
                                sitename = "Hackerearth";
                                res = "false";
                                Handle = "  ";
                                c = 3;
                                db.updateContact2(new Contact(sitename, res, Handle));

                            }
                            if (editText3.getText().toString() != NULL) {

                                sitename = "Codeforces";
                                res = "true";
                                Handle = editText3.getText().toString();
                                db.updateContact2(new Contact(sitename, res, Handle));
                            }
                            if (editText3.getText().toString() == NULL) {
                                Log.e("xxx", "www");
                                sitename = "Codeforces";
                                res = "false";
                                Handle = "  ";
                                c = 4;
                                db.updateContact2(new Contact(sitename, res, Handle));
                            }



                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }

                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
        Dialog dialog=new Dialog(getBaseContext());
        dialog.setCanceledOnTouchOutside(true);
    }


    @Override
    public void onRefresh() {
        Log.e("lolol", "ghussa");
      //  db1.execSQL("DROP TABLE IF EXISTS " + db.TABLE_EVENTS);
        //db.onCreate(db1);
        getData();
        getData1();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.getSupportActionBar().setTitle("Coder's Inc");
        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
        checkConnection();
    }


    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            //finish();
            moveTaskToBack(true);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
    public void getData1() {
        swipeRefreshLayout.setRefreshing(true);
        // Log.e("getdata1","1");
        // loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);
        String college_url = "http://codersinc.esy.es/college.php".trim();
        StringRequest stringRequest = new StringRequest(college_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  loading.dismiss();
                swipeRefreshLayout.setRefreshing(false);
                //Log.e("maincollege", "responce");
                showJSON1(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        swipeRefreshLayout.setRefreshing(false);
                        //Log.e("getdata1", "3");
                        //Toast.makeText(this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        // Log.e("getdata1", "4");
    }


    public void showJSON1(String response) {
        try {

            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("college_contests");


            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);
                name = collegeData.getString("name");

                type = "college";
                site = collegeData.getString("site");
                begin_date = collegeData.getString("begin_date");
                begin_time = collegeData.getString("begin_time");
                end_date = collegeData.getString("end_date");
                end_time = collegeData.getString("end_time");
                link = collegeData.getString("link");
                //Log.d("meghna "," "+site);
                db.addContact7(new Contact(name, type, site, begin_date, begin_time, end_date, end_time, link));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    }






