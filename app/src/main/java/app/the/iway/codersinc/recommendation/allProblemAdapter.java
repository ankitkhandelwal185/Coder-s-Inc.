package app.the.iway.codersinc.recommendation;

/**
 * Created by IWAY on 28-11-2016.
 */
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;



import app.the.iway.codersinc.Contests.Contact;
import app.the.iway.codersinc.Contests.DatabaseHandler;
import app.the.iway.codersinc.R;


import java.util.ArrayList;
import java.util.List;

public class allProblemAdapter extends RecyclerView.Adapter<allProblemAdapter.MyViewHolder> {

    private List<allProblemModel> problemsList;
    private Context context;
    DatabaseHandler db;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,tag;
        public ImageButton image1,image2;


        public MyViewHolder(View view) {
            super(view);
            db=new DatabaseHandler(view.getContext());

            title = (TextView) view.findViewById(R.id.allproblemtext);
            tag = (TextView) view.findViewById(R.id.alltagstext);
            image1 = (ImageButton) view.findViewById(R.id.image1);
            image2 = (ImageButton) view.findViewById(R.id.image2);

        }
    }


    public allProblemAdapter(List<allProblemModel> moviesList) {
        this.problemsList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recommended_allproblems_text, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final allProblemModel movie =problemsList.get(position);
        holder.title.setText(movie.getTitle());
        holder.tag.setText(movie.getTag());
         ImageButton image1=holder.image1;
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("problemAdapter","onclick");
                Log.d("bind",""+movie.getTitle());
                db.addContact5(new Contact(movie.getTitle(),null,null,movie.getTag(),null));
            }
        });
        ImageButton image2 = holder.image2;
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(movie.getUrl());
                Intent openBrowser2 = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(openBrowser2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return problemsList.size();
    }
    public void setFilter(List<allProblemModel> countryModels) {
        problemsList = new ArrayList<>();
        problemsList.addAll(countryModels);
        notifyDataSetChanged();
    }
}
