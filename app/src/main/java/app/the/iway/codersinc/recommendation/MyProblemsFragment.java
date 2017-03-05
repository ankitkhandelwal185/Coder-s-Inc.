package app.the.iway.codersinc.recommendation;

import android.os.AsyncTask;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import app.the.iway.codersinc.Contests.Contact;
import app.the.iway.codersinc.Contests.DatabaseHandler;
import app.the.iway.codersinc.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by IWAY on 28-11-2016.
 */
public class MyProblemsFragment extends Fragment {

    private Document htmlDocument;
    private Document htmlDocument1;
    private String htmlPageUrl = "https://www.codechef.com/users/";
    private String Url="https://discuss.codechef.com/problems/";
    private static String easy = "http://codersinc.esy.es/easy.php";
    private TextView parsedHtmlNode;
    private String htmlContentInStringFormat;
    private String tableId = "dataTable";
    String a;
    DatabaseHandler db;
    public ArrayList<String> problems=null;
    private String tagname=null;
    private ArrayList<String> tags=null;
    private List<myProblemModel> myproblemsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private myProblemAdapter mAdapter;
    private ArrayList<String> myprofileprob;
    private NavigableMap<Double,String> field ;
    private NavigableMap<Double,String> addfield;
    private NavigableMap<String,String> newfield;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.myproblems_layout, container, false);

        tags = new ArrayList();
        problems = new ArrayList();
        myprofileprob=new ArrayList<>();
        field = new TreeMap<>();
        addfield=new TreeMap<>();
        newfield=new TreeMap<>();
        recyclerView = (RecyclerView) v.findViewById(R.id.myproblemrecycler_view);
        recyclerView.setHasFixedSize(true);
        mAdapter = new myProblemAdapter(myproblemsList,getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        getData();

        db = new DatabaseHandler(getActivity());
        List<Contact> contacts = db.getAllContacts2();
        for(Contact cn : contacts) {
            if (cn.getSitename().equals("Codechef")) {
                a = cn.getHandle();
                //Log.e("mayproblem", a);
            }
        }
        if(a.equals("xxxxx")){
            Toast.makeText(getActivity(), "Please provide a valid user name of codechef handle", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
        else if(a!=null ) {
            //Log.e("Graph ","ankit "+a);
            htmlPageUrl = htmlPageUrl + a;
            JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
            jsoupAsyncTask.execute();
        }
        else{
            Toast.makeText(getActivity(), "Please first Enter your Handle name in ManageHandle", Toast.LENGTH_SHORT).show();
            getActivity().finish();;
        }

         printmy();

        return v;
    }


    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {
        private JsoupAsyncTask() {
        }

        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected Void doInBackground(Void... params) {
            try {
               // Log.e("calling ", "getjsondata");
               // Log.e("back", "try");
                htmlDocument = Jsoup.connect(htmlPageUrl).get();
                // String beginner_url = beginner.trim();
                // String easy_url = easy.trim();
                Element e = (Element)htmlDocument.select("table").get(2);
                Elements row = e.select("tr:gt(7)");
                Elements tds = row.select("td");
                Iterator var5 = tds.iterator();

                while(var5.hasNext()) {
                    Element artTag = (Element)var5.next();
                    Elements atags = artTag.select("a");
                    //atags=atags.text();
                   // System.out.println("atags"+atags);
                    Iterator var8 = atags.iterator();

                    while(var8.hasNext()) {
                        Element atag = (Element)var8.next();
                        //atag=atag.text();
                        Url = "https://discuss.codechef.com/problems/";
                        boolean c = false;
                        int d = 0;
                        String r = "";
                        String s = "";
                        String linkt = atag.text();
                        //Log.e("linkt"+linkt,"m");
                        Url =Url + linkt;
                        //System.out.println(Url);
                        Set keys = field.keySet();
                        Iterator itr = keys.iterator();
                        while (itr.hasNext()) {
                            Log.e("itr ", "while");
                            Double key = (Double) itr.next();
                            String value = (String) field.get(key);
                           // Log.e("zero " + key, "zero " + value);
                            // String h=linkt+":";
                              System.out.println("check"+key+linkt+"meghna");
                            if(linkt.equals(value)) {
                                //Log.e("addfield","123");
                                addfield.put(key , value);
                                break;

                            }
                        }
                    }
                    //Log.e("recommnd", "async3 " + problems.size());

                } //catch (IOException var21) {
                //var21.printStackTrace();
            }
            catch (IOException var21) {
                var21.printStackTrace();
            }



            return null;
        }

        protected void onPostExecute(Void result) {
            prepareData();
        }
    }


    private void prepareData(){
        //Log.e("myproblem","prepare");
        /*for(int i=0 ; i< myprofileprob.size() ; i++) {
            myproblem problem = new myproblem(myprofileprob.get(i),"tag5432");
            myproblemsList.add(problem);
            Log.e("myproblem","data "+myprofileprob.get(i));
            mAdapter.notifyDataSetChanged();
        }*/
        Iterator iterator = addfield.keySet().iterator();
        while(iterator.hasNext()) {
            Double key=(Double)iterator.next();
            String value=(String)addfield.get(key);
            String tvalue="";

            //Iterator iterator1 = field.keySet().iterator();
            for(Map.Entry<Double, String> e : field.entrySet())
            {
                Double key1=e.getKey();
                String value1=e.getValue();
                if(value1.equals(value))
                {
                    Map.Entry<Double, String> prev = field.lowerEntry(e.getKey());
                    Double key2=prev.getKey();
                    String value2=prev.getValue();
                    for(Map.Entry<String,String> g:newfield.entrySet())
                    {
                        String tkey=g.getKey();
                        if(tkey.equals(value2))
                            tvalue=g.getValue();

                    }
                    //Log.e("accuracygyi",""+tvalue);
                    //Log.e("accuracygyi",""+key2.toString());
                    //Log.e("accuracygyi",""+value2);
                    db.addContact8(new Contact(tvalue,key2.toString(),value2));
                    //myProblemModel problem = new myProblemModel(tvalue,key2.toString(),value2);

                    //myproblemsList.add(problem);
                    //mAdapter.notifyDataSetChanged();


                }
            }



            //Log.e("first " + key, "second " + value);

            // Toast.makeText(ctx, "Key: "+key+" Value: "+value, Toast.LENGTH_LONG).show();
           /* Set keys = addfield.keySet();
            Iterator itr = keys.iterator();
            while (itr.hasNext()) {
                Double key1 = (Double)itr.next();
                // String key =name.toString();
                //String key = addfield.toString();
                System.out.println(key1 + "meghn3");
            }*/
        }
    }
    void  printmy(){
        List<Contact> contacts = db.getAllContacts8();
        for (Contact cn:contacts){
            myProblemModel problem = new myProblemModel(cn.getname(),cn.getAccuracy(),cn.getMyp());
           // Log.e("accuracyaayi",""+cn.getMyp());
            //Log.e("accuracyaayi",""+cn.getAccuracy());
            //Log.e("accuracyaayi",""+cn.getname());
            myproblemsList.add(problem);
            mAdapter.notifyDataSetChanged();

        }
    }




    public void getData() {
        // loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);
        String url = easy.trim();
        //Log.e("myprobFrag","getData");
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  loading.dismiss();
                //Log.e("myprobFrag","onresponce");
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  Toxast.makeText(this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    public void showJSON(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("easy");
            //Log.e("myprobFrag","showJson");
             for (int i = 0; i < result.length() ; i++) {
                JSONObject collegeData = result.getJSONObject(i);
                String name = collegeData.getString("code");
                String name1 = collegeData.getString("nameprob");
                Double accuracy = collegeData.getDouble("accuracy");
                String acc=accuracy.toString();
                System.out.println("listcheck"+name+accuracy);
                field.put(accuracy,name);
                newfield.put(name,name1);



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
