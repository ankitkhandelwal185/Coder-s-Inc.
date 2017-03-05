package com.android.iway.codersinc.Contests;

import android.app.ProgressDialog;
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

import com.android.iway.codersinc.DataAdapter;
import com.android.iway.codersinc.DataModel;
import com.android.iway.codersinc.MainActivity;
import com.android.iway.codersinc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IWAY on 19-09-2016.
 */
public class CurrentContests extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private String TAG = CurrentContests.class.getSimpleName();
    RecyclerView rv;
    ProgressDialog pdialog;
    public DataAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    static View v;
    private static ArrayList<DataModel> data;
    public static DatabaseHandler db;
    public Dialoguebox1 d;
    public int counter=0;
    public MainActivity obj;
    public SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        v = inflater.inflate(R.layout.currentcontests_layout, container, false);
        data = new ArrayList<>();
        db=new DatabaseHandler(getActivity());
        rv = (RecyclerView) v.findViewById(R.id.tab1_recycler_view);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new DataAdapter(data,getActivity());
        rv.setAdapter(adapter);

       /* new GetContacts().execute();*/
        //c.moveToFirst();
        showRecords();
        return v;
    }
    public void onRefresh() {
        Log.e("lolol", "ghussa");
        //  db1.execSQL("DROP TABLE IF EXISTS " + db.TABLE_EVENTS);
        //db.onCreate(db1);

        obj.getData();
    }
    public void showRecords() {
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();
        for (Contact cn : contacts) {

            String typ=cn.getType();
            String link=cn.getLink();
            String type=cn.getType();
            if(typ.equals("present")) {
               // DataModel dm = new DataModel(cn.getSite(), cn.getName(), cn.getBegindate(), cn.getBegin(), cn.getEnddate(), cn.getEnd(),link,type);
                //data.add(dm);
                List<Contact> contactssite = db.getAllContacts1();
                counter=0;
                for (Contact cn1 : contactssite) {
                    //String log = "Sitename: " + cn1.getSitename() + "\nResponse:\t" + cn1.getRes();
                    // Writing Contacts to log
                    if(cn1.getSitename().equals(cn.getSite()) && cn1.getRes().equals("true")) {
                        //Log.d("Name: ", log);
                        //   textViewResult.setText("SiteName:\t" + cn.getSitename());
                        DataModel dm = new DataModel(cn.getSite(), cn.getName(), cn.getBegindate(), cn.getBegin(), cn.getEnddate(), cn.getEnd(),link,type);
                        data.add(dm);

                        //   textViewResult.setText("Name:\t" + name + "\nType:\t" + type + "\n Site:\t" + site + "\nBegindate:\t" + begin_date + "\nBeginTiming:\t" + begin_time + "\nEnddate:\t" + end_date + "\nEndTiming:\t" + end_time + "\nLink:\t" + link);


                        //Log.d("Name: ", log);
                        //textViewResult.setText("Name:\t" + cn.getName() + "\nBeginTiming:\t" + cn.getBegin() + "\nEndTiming:\t" + cn.getEnd() + "\nType:\t" + cn.getType());
                    }
                    else if (cn1.getRes().equals("false")){
                       counter=counter+1;
                    }


                }
                if(counter==3){

                   DataModel dm = new DataModel(cn.getSite(), cn.getName(), cn.getBegindate(), cn.getBegin(), cn.getEnddate(), cn.getEnd(),link,typ);
                    data.add(dm);
                    Log.e("ggg","kamine");
                     counter=0;


                }



            }
           // else
            //{
              //  break;
            //}

        }
        adapter.notifyDataSetChanged();
    }
}