package app.the.iway.codersinc.Contests;

/**
 * Created by IWAY on 21-09-2016.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import app.the.iway.codersinc.DataModel;
import app.the.iway.codersinc.R;


import java.util.ArrayList;
import java.util.List;

public class UpcommingContests extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private String TAG = UpcommingContests.class.getSimpleName();
    RecyclerView rv;
    public upcomingDataAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    static View v;
    private static ArrayList<DataModel> data;
    public static DatabaseHandler db;
    public Dialoguebox1 d;
    public  int counter=0;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Log.e("hh","aa");
        v = inflater.inflate(R.layout.upcomingcontests_layout, container, false);
        data = new ArrayList<>();
        db=new DatabaseHandler(getActivity());
        rv = (RecyclerView) v.findViewById(R.id.rv_tab2);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new upcomingDataAdapter(data,getActivity());
        rv.setAdapter(adapter);
        showRecords();
        return v;
    }
    public void showRecords() {
        Log.d("upcoming ", "showrecords");
        List<Contact> contacts = db.getAllContacts();
        for (Contact cn : contacts) {
            String typ=cn.getType();
            String link=cn.getLink();
            //counter=0;
            if(typ.equals("upcoming")) {
                List<Contact> contactssite = db.getAllContacts1();
                counter = 0;
                for (Contact cn1 : contactssite) {
                    if (cn1.getSitename().equals(cn.getSite()) && cn1.getRes().equals("true")) {
                        Log.d("codeforces"," " +cn1.getSitename());
                        DataModel dm = new DataModel(cn.getSite(), cn.getName(), cn.getBegindate(), cn.getBegin(), cn.getEnddate(), cn.getEnd(), link, typ);
                        data.add(dm);
                        adapter.notifyDataSetChanged();
                    } else if (cn1.getRes().equals("false")) {
                        counter = counter + 1;
                        //Log.e("upcoming", "elseif");
                    }
                }
                if (counter == 4) {

                    DataModel dm = new DataModel(cn.getSite(), cn.getName(), cn.getBegindate(), cn.getBegin(), cn.getEnddate(), cn.getEnd(), link, typ);
                    data.add(dm);
                    adapter.notifyDataSetChanged();
                    //Log.e("upcoming", "counter3");
                    counter = 0;
                }
            }
        }
    }

    @Override
    public void onRefresh() {

    }
}