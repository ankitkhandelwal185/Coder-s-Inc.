package app.the.iway.codersinc.recommendation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import app.the.iway.codersinc.Contests.Contact;
import app.the.iway.codersinc.Contests.DatabaseHandler;
import app.the.iway.codersinc.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by IWAY on 28-11-2016.
 */
public class TodolistFragment extends Fragment {

    private List<TodolistModel> problemsList = new ArrayList<>();
    public DatabaseHandler db;
    private RecyclerView recyclerView;
    private TodolistAdapter mAdapter;
    public ArrayList<String> index = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.todolist_layout   , container, false);
        super.onCreate(savedInstanceState);
        db=new DatabaseHandler(getActivity());
        recyclerView = (RecyclerView) v.findViewById(R.id.todo_recycler_view);
        recyclerView.setHasFixedSize(true);
        mAdapter = new TodolistAdapter(problemsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareData();

        return v;
    }

    public void prepareData() {
       // Log.e("asasas", "preparedata");
        problemsList.clear();
        List<Contact> contacts = db.getAllContacts5();
        Log.d("todo", " " +contacts.size());
        for (Contact cn : contacts) {
            //Log.d("peparedatabbb",""+cn.getname()+cn.getTags());
            TodolistModel prob = new TodolistModel(cn.getname(), cn.getTags());

            problemsList.add(prob);


        }
       // problemsList.clear();
        mAdapter.notifyDataSetChanged();

    }
}

