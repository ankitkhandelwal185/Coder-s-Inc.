package app.the.iway.codersinc.Contests;

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


import app.the.iway.codersinc.DataAdapter;
import app.the.iway.codersinc.DataModel;
import app.the.iway.codersinc.MainActivity;
import app.the.iway.codersinc.R;


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
        //v.setBackgroundColor(Color.rgb(55,71,79));
        data = new ArrayList<>();
        db=new DatabaseHandler(getActivity());
        rv = (RecyclerView) v.findViewById(R.id.tab1_recycler_view);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new DataAdapter(data,getActivity());
        rv.setAdapter(adapter);
        showRecords();
        return v;
    }
    public void onRefresh() {
        Log.e("current", "onrefresh");
        obj.getData();
    }
    public void showRecords() {
        // Reading all contacts
        Log.d("currentcontests: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();
        for (Contact cn : contacts) {

            String typ=cn.getType();
            String link=cn.getLink();
            String type=cn.getType();
            if(typ.equals("present")) {
                List<Contact> contactssite = db.getAllContacts1();
                counter=0;
                for (Contact cn1 : contactssite) {
                    if(cn1.getSitename().equals(cn.getSite()) && cn1.getRes().equals("true")) {

                        DataModel dm = new DataModel(cn.getSite(), cn.getName(), cn.getBegindate(), cn.getBegin(), cn.getEnddate(), cn.getEnd(),link,type);
                        data.add(dm);
                        adapter.notifyDataSetChanged();}
                    else if (cn1.getRes().equals("false")){
                       counter=counter+1;
                    }
                }
                if(counter==4){

                   DataModel dm = new DataModel(cn.getSite(), cn.getName(), cn.getBegindate(), cn.getBegin(), cn.getEnddate(), cn.getEnd(),link,typ);
                    data.add(dm);
                     counter=0;
                    adapter.notifyDataSetChanged();
                }
            }
        }

    }
}