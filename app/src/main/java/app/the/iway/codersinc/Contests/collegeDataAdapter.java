package app.the.iway.codersinc.Contests;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import app.the.iway.codersinc.DataModel;
import app.the.iway.codersinc.R;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by dell pc on 02-12-2016.
 */
public class collegeDataAdapter extends RecyclerView.Adapter<collegeDataAdapter.MyViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<DataModel> dataSet;

    // Hold the position of the expanded item
    private int expandedPosition = -1;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.college_card_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.itemView.setOnClickListener(collegeDataAdapter.this);
        myViewHolder.itemView.setTag(myViewHolder);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int listPosition) {
        TextView textViewName = holder.textViewName;
        TextView textstarttime = holder.textstarttime;
        TextView textendtime = holder.textendtime;
        TextView textsitename=holder.textsitename;
        TextView textenddate=holder.textenddate;
        TextView textstartdate=holder.textstartdate;
        TextView textshare = holder.textshare;
        TextView textsync = holder.textsync;
        TextView textnotifyme=holder.textnotify;
        TextView textlink=holder.textlink;
        CardView cardview=holder.card1;
        FrameLayout frame= holder.frame;

        textViewName.setText(dataSet.get(listPosition).getName());
        textstarttime.setText(dataSet.get(listPosition).getStarttime());
        textendtime.setText(dataSet.get(listPosition).getEndtime());
        textsitename.setText(dataSet.get(listPosition).getSitename());
        textstartdate.setText(dataSet.get(listPosition).getStartdate());
        textenddate.setText(dataSet.get(listPosition).getEnddate());
        textlink.setText(dataSet.get(listPosition).getLink());
        textViewName.setText(dataSet.get(listPosition).getName());
        textstarttime.setText(dataSet.get(listPosition).getStarttime());
        textendtime.setText(dataSet.get(listPosition).getEndtime());
        textsitename.setText(dataSet.get(listPosition).getSitename());
        textstartdate.setText(dataSet.get(listPosition).getStartdate());
        textenddate.setText(dataSet.get(listPosition).getEnddate());
        textlink.setText(dataSet.get(listPosition).getLink());
        Log.e("link", dataSet.get(listPosition).getLink());
        if(dataSet.get(listPosition).getLink().equals("www.codechef.com")){
            Log.e("adapter","codechef");

            frame.setBackgroundColor(Color.rgb(139,69,19));
            //cardview.setBackgroundColor(Color.rgb(176,190,197));
            // frameLayout1.setBackgroundColor(Color.rgb(165,42,42));

        }
        if(dataSet.get(listPosition).getLink().equals("hackerrank")){
            Log.e("adapter", "hackerrank");
            //cardview.setCardBackgroundColor(Color.GREEN);
            frame.setBackgroundColor(Color.rgb(24,92,19));

        }
        if(dataSet.get(listPosition).getSitename().equals("Hackerearth")){
            Log.e("adapter", "hackerearth");
            //cardview.setCardBackgroundColor(Color.YELLOW);
            frame.setBackgroundColor(Color.rgb(70,130,180));

        }
        if(dataSet.get(listPosition).getSitename().equals("Codeforces")){
            //Log.e("adapter","hackerearth");
            frame.setBackgroundColor(Color.rgb(222,185,135));
        }
        //TextView LinkButton = (TextView) findViewById(R.id.ankit);
        textlink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Uri uri = Uri.parse(dataSet.get(listPosition).getLink());
                Intent openBrowser = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(openBrowser);
            }
        });

        if (listPosition == expandedPosition) {
            holder.l.setVisibility(View.VISIBLE);
        } else {
            holder.l.setVisibility(View.GONE);
        }


        textshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, dataSet.get(listPosition).getLink());
                sendIntent.setType("text/plain");
                Intent.createChooser(sendIntent, "Share via");
                context.startActivity(sendIntent);
            }
        });
        textsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calintent = new Intent(Intent.ACTION_INSERT);
                calintent.setData(CalendarContract.Events.CONTENT_URI);
                calintent.setType("vnd.android.cursor.item/event");
                calintent.putExtra(CalendarContract.Events.TITLE, dataSet.get(listPosition).getName());
                calintent.putExtra(CalendarContract.Events.EVENT_LOCATION, dataSet.get(listPosition).getSitename());
                if(dataSet.get(listPosition).getType().equals("college")) {
                    String c = dataSet.get(listPosition).getStarttime();
                    String[] partst = c.split(":");
                    String partt1 = partst[0]; // 004
                    String partt2 = partst[1]; // 034556
                    String partt3 = partst[2];
                    String d = dataSet.get(listPosition).getStartdate();
                    String[] partsd = d.split("-");
                    String partd1 = partsd[0]; // 004
                    String partd2 = partsd[1]; // 034556
                    String partd3 = partsd[2];
                    String mon[] = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                    partd2 = mon[Integer.parseInt(partd2)];
                    String str = partd2 + " " + partd3 + " " + partd1 + " " + partt1 + ":" + partt2 + ":" + partt3 + "." + "000 " + "UTC";
                    SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
                    Date date = null;
                    try {
                        date = df.parse(str);

                    } catch (ParseException e) {
                        e.printStackTrace();

                    }
                    long epoch = (date.getTime() - 19800000);

                    calintent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, epoch);

                    String c1 = dataSet.get(listPosition).getEndtime();
                    String[] partst1 = c1.split(":");
                    String partt11 = partst1[0]; // 004
                    String partt21 = partst1[1]; // 034556
                    String partt31 = partst1[2];
                    String d1 = dataSet.get(listPosition).getEnddate();
                    String[] partsd1 = d1.split("-");
                    String partd11 = partsd1[0]; // 004
                    String partd21 = partsd1[1]; // 034556
                    String partd31 = partsd1[2];
                    //String mon[] = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                    partd21 = mon[Integer.parseInt(partd21)];
                    String str1 = partd21 + " " + partd31 + " " + partd11 + " " + partt11 + ":" + partt21 + ":" + partt31 + "." + "000 " + "UTC";
                    SimpleDateFormat df1 = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
                    Date date1 = null;
                    try {
                        date1 = df1.parse(str1);

                    } catch (ParseException e) {
                        e.printStackTrace();

                    }
                    long epoch1 = (date1.getTime() - 19800000);

                    calintent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, epoch1);








                }
                context.startActivity(calintent);
            }
        });


        textnotifyme.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String type= dataSet.get(listPosition).getType();
                if(type.equals("present")){
                    Toast.makeText(context, "This contest has already started", Toast.LENGTH_SHORT).show();
                }
                else {
                    String c=dataSet.get(listPosition).getStarttime();
                    String[] partst = c.split(":");
                    String partt1 = partst[0]; // 004
                    String partt2 = partst[1]; // 034556
                    String partt3 = partst[2];
                    String d=dataSet.get(listPosition).getStartdate();
                    String[] partsd = d.split("-");
                    String partd1 = partsd[0]; // 004
                    String partd2 = partsd[1]; // 034556
                    String partd3 = partsd[2];
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.MONTH,Integer.parseInt(partd2));
                    calendar.set(Calendar.YEAR,Integer.parseInt(partd1));
                    calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(partd3));

                    calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(partt1));
                    calendar.set(Calendar.MINUTE,Integer.parseInt(partt2));
                    calendar.set(Calendar.SECOND,Integer.parseInt(partt3));
                    calendar.set(Calendar.AM_PM, Calendar.PM);
                    Toast.makeText(context, "Your Responce has been Received : you'll be notified", Toast.LENGTH_SHORT).show();
                }

              /*  Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
                if(PendingIntent.getBroadcast(getClass(), 0,
                        myIntent,
                        PendingIntent.FLAG_NO_CREATE) == null) {
                    pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
*/
            }
        });

        //imageView.setImageResource(dataSet.get(listPosition).getImage());


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setFilter(List<DataModel> countryModels) {
        dataSet = new ArrayList<>();
        dataSet.addAll(countryModels);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) view.getTag();
        //String theString = dataSet.get(holder.getPosition());

        // Check for an expanded view, collapse if you find one
        if (expandedPosition >= 0) {
            int prev = expandedPosition;
            notifyItemChanged(prev);

        }

        // Set the current position to "expanded"
        expandedPosition = holder.getPosition();
        notifyItemChanged(expandedPosition);


    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textstarttime;
        TextView textendtime;
        TextView textstartdate;
        TextView textenddate;
        TextView textsitename;
        ImageView imageViewIcon;
        TextView textshare;
        TextView textsync;
        TextView textnotify;
        LinearLayout l;
        TextView textlink;
        CardView card1;
        FrameLayout frame;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textstarttime = (TextView) itemView.findViewById(R.id.textstarttime);
            this.textendtime  = (TextView) itemView.findViewById(R.id.textendtime);
            this.textsitename=(TextView)itemView.findViewById(R.id.textsitename);
            this.textstartdate=(TextView)itemView.findViewById(R.id.textstartdate);
            this.textenddate=(TextView)itemView.findViewById(R.id.textenddate);
            this.textshare=(TextView)itemView.findViewById(R.id.shareit);
            this.textsync=(TextView)itemView.findViewById(R.id.textsync);
            this.textnotify=(TextView)itemView.findViewById(R.id.textnotify);
            this.l=(LinearLayout)itemView.findViewById(R.id.llExpandArea);
            this.textlink=(TextView)itemView.findViewById(R.id.link);
            this.card1 = (CardView)itemView.findViewById(R.id.card_view);
            this.frame = (FrameLayout)itemView.findViewById(R.id.clg_frame);
            // this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public collegeDataAdapter(ArrayList<DataModel> data, Context context) {
        this.dataSet = data;
        this.context=context;
    }
}
