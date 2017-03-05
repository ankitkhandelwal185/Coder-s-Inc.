package app.the.iway.codersinc.Drawer.graph;

/**
 * Created by dell pc on 05-12-2016.
 */

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import app.the.iway.codersinc.Contests.Contact;
import app.the.iway.codersinc.Contests.DatabaseHandler;
import app.the.iway.codersinc.R;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;





public class GraphActivity2 extends AppCompatActivity {

    private Document htmlDocument;
    DatabaseHandler db;
    private static String codeforcesUrl = "http://codeforces.com/submissions/";
    HashSet<String> name;
    private float accepted;
    private float wrong;
    private float compilation;
    private float  runtime;
    String a;
    private String[] arr=new String [4];
    private String[] arr1=new String[4];
    Float r[]=new Float[9];

    //String a;
    float sum ;

    // DatabaseHandler db;

    private TextView txtinfo;
    LinearLayout lvOne, lvTwo, lvThree, lvFour, lvFive, lvparent;
    TextView txtOne, txtTwo, txtThree, txtFour, txtFive;
    PieView pieView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar .setBackgroundColor(Color.rgb(55,71,79));
        // arr1=["Accepted","","",""];
        arr1[0]="Accepted";
        arr1[1]="Wrong ans";
        arr1[2]="Compilation";
        arr1[3]="runtime";
        sum=0;
        setTitle("Codeforces");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new DatabaseHandler(this);
        name=new HashSet<>();
        accepted=0;
        wrong=0;
        compilation=0;
        runtime=0;
        db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts2();

        for(Contact cn : contacts) {
            if (cn.getSitename().equalsIgnoreCase("Codeforces")) {
                a = cn.getHandle();
                //Log.e("mayproblem", a);
            }
        }
        //Log.e("graph222 ",""+a);
        if(a.equals("xxxxx")){
            Toast.makeText(this, "Please provide a valid user name of codeforces handle", Toast.LENGTH_SHORT).show();
            this.finish();
        }
        else if(a!=null ) {
            //Log.e("Graph ","ankit "+a);
            codeforcesUrl = codeforcesUrl + a;
            JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
            jsoupAsyncTask.execute();

        }
        else{
            Toast.makeText(this, "Please first Enter your Handle name in ManageHandle", Toast.LENGTH_SHORT).show();
            this.finish();;
        }


    }
    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                //Log.e("calling try ", "in");
                htmlDocument = Jsoup.connect(codeforcesUrl).get();
                Element e = htmlDocument.select("table.status-frame-datatable").first();
                Elements row = e.select("tr");
                Iterator var6 = row.iterator();

                while (var6.hasNext()) {
                    int c = 0;
                    Element artTag1 = (Element) var6.next();
                    Elements tds = artTag1.select("td");
                    Iterator var5 = tds.iterator();
                    while (var5.hasNext()) {
                        c++;
                        Element artTag = (Element) var5.next();
                        String atags = artTag.text();
                        //System.out.println("atags"+atags);
                        if (c == 4) {
                          //  Log.e("name", "" + atags);
                            name.add(atags);

                        }
                        if (c == 6) {
                            //Log.e("accepted/not", "" + atags);
                            if (atags.equalsIgnoreCase("Accepted"))
                            { accepted++;r[0]=accepted;}//accepted
                            else if (atags.charAt(0) == 'R')
                            {  runtime++;r[3]=runtime;}
                            else if (atags.charAt(0) == 'W')
                            {wrong++;r[1]=wrong;}
                            else if (atags.charAt(0) == 'C')
                            {compilation++;r[2]=compilation;}//runtime answers
                        }


                    }
                }
                //  Log.e("recommnd", "async3 " + problems.size());
                sum=accepted+compilation+wrong+runtime;

            }//catch (IOException var21) {
            //var21.printStackTrace();

            catch (IOException var21) {
                var21.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //parsedHtmlNode.setText(htmlContentInStringFormat);
            // parsedHtmlNode.setText(htmlDocument);


            txtinfo = (TextView) findViewById(R.id.txtinfo);
            pieView = (PieView) findViewById(R.id.pie_view);

            lvOne = (LinearLayout) findViewById(R.id.lvOne);
            lvTwo = (LinearLayout) findViewById(R.id.lvTwo);
            lvThree = (LinearLayout) findViewById(R.id.lvThree);
            lvFour = (LinearLayout) findViewById(R.id.lvFour);

            lvparent = (LinearLayout) findViewById(R.id.lvparent);

            txtOne = (TextView) findViewById(R.id.txtOne);
            txtTwo = (TextView) findViewById(R.id.txtTwo);
            txtThree = (TextView) findViewById(R.id.txtThree);
            txtFour = (TextView) findViewById(R.id.txtFour);

            set(pieView);


        }
    }

    private void set(PieView pieView) {
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        // toolbar.setTitle("Success Graph");
        int color0 = Color.rgb(0, 128, 255);
        int color1 = Color.rgb(252, 3, 71);
        int color2 = Color.rgb(117, 91, 4);
        int color3 = Color.rgb(3, 7, 173);
        int color4 = Color.rgb(20, 156, 82);

        pieHelperArrayList.add(new PieHelper(((accepted/sum)*100) , color4));
        pieHelperArrayList.add(new PieHelper(((wrong/sum)*100), color1));

        pieHelperArrayList.add(new PieHelper(((compilation/sum)*100), color2));

        pieHelperArrayList.add(new PieHelper(((runtime/sum)*100), color3));

        //  pieHelperArrayList.add(new PieHelper(((r[8]/sum)*100), color0));

        lvOne.setBackgroundColor(color4);
        txtOne.setText("accepted");
        lvTwo.setBackgroundColor(color1);
        txtTwo.setText("wrong");
        lvThree.setBackgroundColor(color2);
        txtThree.setText("compilation");
        lvFour.setBackgroundColor(color3);
        txtFour.setText("runtime");
        //lvOne.setBackgroundColor(color4);
        //txtFive.setText(arr1[8].toString());

        pieView.setDate(pieHelperArrayList);
        pieView.setOnPieClickListener(new PieView.OnPieClickListener() {
            @Override
            public void onPieClick(int index) {
                if (index != PieView.NO_SELECTED_INDEX) {
                    txtinfo.setText(r[index] + " "
                            + arr1[index] + ".");
                } else {
                    txtinfo.setText("No selected pie");
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return  true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

