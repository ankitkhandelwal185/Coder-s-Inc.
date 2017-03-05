package app.the.iway.codersinc.recommendation;

/**
 * Created by IWAY on 27-11-2016.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import app.the.iway.codersinc.R;


public class Recommendation extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    Toolbar  toolbar;
    AllProblemsFragment a;
    MyProblemsFragment m;
    TodolistFragment t;

    @Override
    public void onRefresh() {

    }

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.rgb(55,71,79));
        setTitle("Recommendation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        a=new AllProblemsFragment();
        m=new MyProblemsFragment();
        t=new TodolistFragment();

        // Initilization
        viewPager = (ViewPager) findViewById(R.id.rpager);
        setupViewPager(viewPager);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        //getData();
                                    }
                                }
        );
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);



        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

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
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return  true;
    }

//set up view pager all functions

    private void setupViewPager(ViewPager viewPager) {

        TabspagerAdapter adap = new TabspagerAdapter(getSupportFragmentManager());
        adap.addFrag(a, "All problems");
        adap.addFrag(m, "My Problems");
        adap.addFrag(t, "To do list");
        viewPager.setAdapter(adap);

    }

    @Override
    public void onBackPressed() {
        //toolbar.setTitle(super.getTitle());
        super.onBackPressed();
    }
}
