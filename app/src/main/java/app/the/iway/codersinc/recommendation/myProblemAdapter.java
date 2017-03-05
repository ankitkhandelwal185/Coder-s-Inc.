package app.the.iway.codersinc.recommendation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;



import app.the.iway.codersinc.Contests.Contact;
import app.the.iway.codersinc.Contests.DatabaseHandler;
import app.the.iway.codersinc.R;


import java.util.List;

/**
 * Created by IWAY on 28-11-2016.
 */
public class myProblemAdapter extends RecyclerView.Adapter<myProblemAdapter.MyViewHolder> implements View.OnClickListener {


    private List<myProblemModel> myproblemsList;
    private Context context;
    DatabaseHandler db;

    @Override
    public void onClick(View v) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title1, tag1;
        ImageButton image1,image2;

        public MyViewHolder(View view) {
            super(view);
            db=new DatabaseHandler(view.getContext());

            title1 = (TextView) view.findViewById(R.id.myproblemtext);
            tag1 = (TextView) view.findViewById(R.id.mytagstext);
            image2 = (ImageButton) view.findViewById(R.id.image2);
            image1 = (ImageButton) view.findViewById(R.id.image1);
        }
    }


    public myProblemAdapter(List<myProblemModel> moviesList,Context context) {
        this.myproblemsList = moviesList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recommended_myproblems_text, parent, false);

       /* MyViewHolder myViewHolder = new MyViewHolder(itemView);
        myViewHolder.itemView.setOnClickListener(myProblemAdapter.this);
        myViewHolder.itemView.setTag(myViewHolder);*/
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final myProblemModel movie = myproblemsList.get(position);
        holder.title1.setText(movie.getCode());
        holder.tag1.setText("Accuracy "+movie.getTitle());
        ImageButton image1 = holder.image1;
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("problemAdapter","onclick");
                Log.d("bind",""+movie.getTitle());
                db.addContact5(new Contact(movie.getCode(),null,null,movie.getTitle(),null));
            }
        });
        ImageView image2=holder.image2;
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.codechef.com/problems/"+movie.getTag());
                Log.e("myProblemUrl",""+uri);
                Intent openBrowser2 = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(openBrowser2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myproblemsList.size();
    }
}