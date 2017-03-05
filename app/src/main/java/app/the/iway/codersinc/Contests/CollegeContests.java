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
import android.widget.FrameLayout;
import android.widget.TextView;



import app.the.iway.codersinc.DataModel;
import app.the.iway.codersinc.MainActivity;
import app.the.iway.codersinc.R;

import java.util.ArrayList;
import java.util.List;


public class CollegeContests extends Fragment {
    TextView text;
    RecyclerView rv;
    ProgressDialog pdialog;
    public collegeDataAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    static View v;
    private static ArrayList<DataModel> data;
    public static DatabaseHandler db;
    public Dialoguebox1 d;
    public int counter=0;
    public MainActivity obj;
    FrameLayout frameLayout;
    public SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        v = inflater.inflate(R.layout.collegecontests_layout, container, false);
        data = new ArrayList<>();
        db=new DatabaseHandler(getActivity());
        rv = (RecyclerView) v.findViewById(R.id.college_recycler_view);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new collegeDataAdapter(data,getActivity());
        rv.setAdapter(adapter);
        //obj.getData1();
        showRecords();
        return v;
    }

    public void showRecords() {
        // Reading all contacts
        //obj.getData1();
        Log.d("college: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts7();
        for (Contact cn : contacts) {

            String typ=cn.getType();
            Log.d("typ",typ);
            String link=cn.getLink();
            String type=cn.getType();
            if(typ.equals("college")) {
                DataModel dm = new DataModel(cn.getSite(), cn.getName(), cn.getBegindate(), cn.getBegin(), cn.getEnddate(), cn.getEnd(),link,type);
                data.add(dm);
                adapter.notifyDataSetChanged();
            }
        }

    }
}

