package app.the.iway.codersinc.Drawer.graph;

import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;




import app.the.iway.codersinc.Contests.Contact;
import app.the.iway.codersinc.Contests.DatabaseHandler;
import app.the.iway.codersinc.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class GraphActivity extends AppCompatActivity {

    private Document htmlDocument;
    // private String htmlPageUrl = "https://inducesmile.com/";

    private String htmlPageUrl = "https://www.codechef.com/users/";
    private TextView parsedHtmlNode;
    private String htmlContentInStringFormat;
    private String tableId="problem_stats";
    private String[] arr=new String [9];
    private String[] arr1=new String [9];
    Float r[]=new Float[9];
    String a;
    float sum =(float) 0;

    DatabaseHandler db;

    private TextView txtinfo;
    LinearLayout lvOne, lvTwo, lvThree, lvFour, lvFive, lvparent;
    TextView txtOne, txtTwo, txtThree, txtFour, txtFive;
    Button btnundo, btnsave;
    PieView pieView;
    Uri outputFileUri;
    Toolbar toolbar;
    OutputStream outStream = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.rgb(55,71,79));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Success Graph");
        db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts2();
        for(Contact cn : contacts) {
            if (cn.getSitename().equals("Codechef")) {
                a = cn.getHandle();
                //Log.e("ghussssss", a);
            }
        }
        if(a.equals("xxxxx")){
            Toast.makeText(GraphActivity.this, "Please provide a valid user name of codechef handle", Toast.LENGTH_SHORT).show();
            finish();
        }
        else if(a!=null ) {
            //Log.e("Graph ","ankit "+a);
            htmlPageUrl = htmlPageUrl + a;
            JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
            jsoupAsyncTask.execute();
        }
        else{
            Toast.makeText(GraphActivity.this, "Please first Enter your Handle name in ManageHandle", Toast.LENGTH_SHORT).show();
            finish();
        }
        /*showInputDialog();*/

    }
    /*public void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(GraphActivity.this);
        View promptView = layoutInflater.inflate(R.layout.activity_live, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GraphActivity.this);
        alertDialogBuilder.setView(promptView);
         final EditText a = (EditText) promptView.findViewById(R.id.editText1);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  addListenerOnChkIos();
                        h=a.getText().toString();
                        htmlPageUrl=htmlPageUrl+h;
                        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
                        jsoupAsyncTask.execute();

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Intent intent = new Intent(GraphActivity.this, MainActivity.class);
                                GraphActivity.this.startActivity(intent);


                            }

                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
        //addListenerOnChkIos();
    }*/
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return  true;
    }
  private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

      @Override
      protected void onPreExecute() {
          super.onPreExecute();
      }

      @Override
      protected Void doInBackground(Void... params) {
          try {
              htmlDocument = Jsoup.connect(htmlPageUrl).get();
              // htmlContentInStringFormat = htmlDocument.title()
              org.jsoup.nodes.Element table = htmlDocument.getElementById(tableId);
              Elements tds = table.getElementsByTag("td");
              int i=0;
              int t=0;
              int s=0;
              for (org.jsoup.nodes.Element td : tds) {
                  i++;
                  if(i<=9)
                  {

                      arr1[s]=td.text();
                      if(arr1[s].equals("CTE")){
                          arr1[s]="Compile Time Error";
                      }
                      if(arr1[s].equals("TLE")){
                          arr1[s]="Time Limit Exceeded";
                      }
                      System.out.println("\n 1  "+arr1[s]);
                      s++;

                  }
                  if(i>9)
                  {arr[t]=td.text();
                      System.out.println("\n"+arr[t]);
                      t++;
                  }
              }

              for(int j=4;j<=8;j++)
              {
                  r[j]=Float.parseFloat(arr[j]);
                  sum = (float)(sum + r[j]);
               }

          } catch (IOException e) {
              e.printStackTrace();
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
          lvFive = (LinearLayout) findViewById(R.id.lvFive);
          lvparent = (LinearLayout) findViewById(R.id.lvparent);

          txtOne = (TextView) findViewById(R.id.txtOne);
          txtTwo = (TextView) findViewById(R.id.txtTwo);
          txtThree = (TextView) findViewById(R.id.txtThree);
          txtFour = (TextView) findViewById(R.id.txtFour);
          txtFive = (TextView) findViewById(R.id.txtFive);
          set(pieView);


      }
  }

    private void set(PieView pieView) {
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();

        int color0 = Color.rgb(0, 128, 255);
        int color1 = Color.rgb(252, 3, 71);
        int color2 = Color.rgb(117, 91, 4);
        int color3 = Color.rgb(3, 7, 173);
        int color4 = Color.rgb(20, 156, 82);

        pieHelperArrayList.add(new PieHelper(((r[4]/sum)*100) , color4));
        pieHelperArrayList.add(new PieHelper(((r[5]/sum)*100), color1));

        pieHelperArrayList.add(new PieHelper(((r[6]/sum)*100), color2));

        pieHelperArrayList.add(new PieHelper(((r[7]/sum)*100), color3));

        pieHelperArrayList.add(new PieHelper(((r[8]/sum)*100), color0));

        lvFive.setBackgroundColor(color0);
        txtOne.setText(arr1[4].toString());
        lvTwo.setBackgroundColor(color1);
        txtTwo.setText(arr1[5].toString());
        lvThree.setBackgroundColor(color2);
        txtThree.setText(arr1[6].toString());
        lvFour.setBackgroundColor(color3);
        txtFour.setText(arr1[7].toString());
        lvOne.setBackgroundColor(color4);
        txtFive.setText(arr1[8].toString());

        pieView.setDate(pieHelperArrayList);
        pieView.setOnPieClickListener(new PieView.OnPieClickListener() {
            @Override
            public void onPieClick(int index) {
                if (index != PieView.NO_SELECTED_INDEX) {
                    txtinfo.setText(r[index+4] + " "
                            + arr1[index+4] + ".");
                } else {
                    txtinfo.setText("No selected pie");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}