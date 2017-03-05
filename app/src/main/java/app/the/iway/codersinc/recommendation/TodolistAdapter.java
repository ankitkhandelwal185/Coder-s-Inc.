package app.the.iway.codersinc.recommendation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import app.the.iway.codersinc.Contests.DatabaseHandler;
import app.the.iway.codersinc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shashank on 03-Dec-16.
 */
public class TodolistAdapter extends RecyclerView.Adapter<TodolistAdapter.MyViewHolder> {

    private List<TodolistModel> problemsList;
    DatabaseHandler db;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,tag;

        public MyViewHolder(View view) {
            super(view);
            db=new DatabaseHandler(view.getContext());
            //FragmentManager fragmentManager=getFragmentManager();
            title = (TextView) view.findViewById(R.id.todolistproblemtext);
            tag = (TextView) view.findViewById(R.id.todolisttagstext);
            //problemsList.clear();


        }
    }


    public TodolistAdapter(List<TodolistModel> moviesList) {
        this.problemsList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recommended_todolist_text, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final TodolistModel movie =problemsList.get(position);
        holder.title.setText(movie.getTitleName());
        holder.tag.setText(movie.getTags());



    }

    @Override
    public int getItemCount() {
        return problemsList.size();
    }
    public void setFilter(List<TodolistModel> countryModels) {
        problemsList = new ArrayList<>();
        problemsList.addAll(countryModels);
        notifyDataSetChanged();
    }

}
