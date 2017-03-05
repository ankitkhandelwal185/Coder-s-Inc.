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


import com.android.volley.NetworkResponse;
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

import java.util.ArrayList;
import java.util.List;

;


/**
 * Created by IWAY on 28-11-2016.
 */
public class AllProblemsFragment extends Fragment {

    private static String codeforcesUrl = "http://codeforces.com/api/problemset.problems";
    public ArrayList<String> problems=null;
    private List<allProblemModel> problemsList = new ArrayList<>();
    public DatabaseHandler db;
    private RecyclerView recyclerView;
    private allProblemAdapter mAdapter;
    public ArrayList<String> problemsname=null;
    public ArrayList<Integer> contestid=null;
    public ArrayList<String> index=null;
    public ArrayList<String> type1=null;
    public ArrayList<Double> points=null;
    ArrayList<ArrayList<String>> biDemArrList =null;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.allproblems_layout, container, false);
     db=new DatabaseHandler(getActivity());
        super.onCreate(savedInstanceState);
        problems = new ArrayList<>();
        problemsname = new ArrayList<>();
        contestid= new ArrayList<>();
        index= new ArrayList<>();
        type1= new ArrayList<>();
        points= new ArrayList<>();
        biDemArrList = new ArrayList<ArrayList<String>>();
        getJsonCData();
        Log.e("allproblems","oncreate");
        recyclerView = (RecyclerView) v.findViewById(R.id.allprob_recycler_view);
        recyclerView.setHasFixedSize(true);
        mAdapter = new allProblemAdapter(problemsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();
        return v;
    }

    public void getJsonCData() {
       // Log.e("codeforces", "ingetjson");
        // loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);
        String url = codeforcesUrl.trim();
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // loading.dismiss();
                //Log.e("codeforces volley","getjson/onresponce");
                showJsonCData(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Log.e("allProblem1",error.toString());
                        NetworkResponse networkResponse=error.networkResponse;
                        if(networkResponse!=null){
                            //Log.e("status code",String.valueOf(networkResponse.statusCode));
                        }
                        // Toast.makeText(this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    private void showJsonCData(String response) {

        try {
           // Log.e("codeforces","try");
            JSONObject jsonObject = new JSONObject(response);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray result1 = result.getJSONArray("problems");
            //Log.e("codeforces", " result size " + result.length());
            for (int i = 0; i < result1.length(); i++) {
                JSONObject tagdata = result1.getJSONObject(i);
                String projectnumber11 = tagdata.getString("name");
                problems.add(projectnumber11);
                if (tagdata.has("name")) {
                    String projectnumber1 = tagdata.getString("name");
                    problemsname.add(projectnumber1);
                }
                if (tagdata.has("contestId")) {
                    Integer projectnumber1 = (Integer)tagdata.get("contestId");
                    //System.out.println("contestid1 "+ projectnumber1);
                    contestid.add(projectnumber1);
                }
                if (tagdata.has("index")) {
                    String projectnumber1 = tagdata.getString("index");
                    //System.out.println("index "+ projectnumber1);
                    index.add(projectnumber1);
                }
                if (tagdata.has("type")) {
                    String projectnumber1 = tagdata.getString("type");
                    //System.out.println("type "+ projectnumber1);
                    type1.add(projectnumber1);
                }
               /* if (tagdata.has("points")) {

                    Double projectnumber1 = tagdata.get("points");
                    BigDecimal.valueOf(tagdata.getDouble("")).floatValue();
                    points.add(projectnumber1);
                }*/
                if (tagdata.has("tags")) {
                    JSONArray projectnumber1 = tagdata.getJSONArray("tags");
                    int count = projectnumber1.length();
                    ArrayList<String> temp = new ArrayList<String>();
                    for (int j = 0; j < count; j++) {
                        String h= (String)projectnumber1.get(j);
                        temp.add(h);
                    }

                    biDemArrList.add(temp);
                }


            }
            //Log.e("codeforces", "size " + problemsname.size());
            for (int y = 0; y < problemsname.size(); y++) {
                System.out.println("database for loop   "+contestid.get(y));
                String p=problemsname.get(y).toString();
                String c=contestid.get(y).toString();
                String in=index.get(y).toString();
                String b=biDemArrList.get(y).toString();
                String text = b.toString().replace("[", "").replace("]", "");
                //Log.e("value b ",""+text);
                String url = "http://codeforces.com/problemset/allProblemModel/"+c+"/"+in;
                db.addContact4(new Contact(p, c, in, text,url));
            }

            for(int i = 0; i < biDemArrList.size(); i++){
                for(int j = 0; j < biDemArrList.get(i).size(); j++){
                    //System.out.print("bidem "+ biDemArrList.get(i).get(j)+",");
                    //Log.e("problemname ","size  "+problemsname.size());
                }
                System.out.println();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void prepareMovieData() {
          //Log.d("allproblems","size "+problems.size());
       /* for(int i=0;i<problems.size();i++) {
            String url = "http://codeforces.com/problemset/problem/"+contestid.get(i)+"/"+index.get(i);
            allProblemModel allProblemModel = new allProblemModel(problemsname.get(i),biDemArrList.get(i).toString());
            problemsList.add(allProblemModel);
            mAdapter.notifyDataSetChanged();
        }*/
        List<Contact> contacts = db.getAllContacts4();
        for(Contact cn : contacts){
            allProblemModel p = new allProblemModel(cn.getname(),cn.getTags());
            problemsList.add(p);

        }
        mAdapter.notifyDataSetChanged();
       // Log.e("prepareAnk",""+contacts.size());

    }


}

