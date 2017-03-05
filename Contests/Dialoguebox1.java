package com.android.iway.codersinc.Contests;

/**
 * Created by Shashank on 18-Oct-16.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.iway.codersinc.MainActivity;
import com.android.iway.codersinc.R;

import java.util.List;

public class Dialoguebox1 extends Activity  {

    public Button button;
    public TextView resultText;
    public CheckBox buttonhac;
    public CheckBox buttonc;
    public CheckBox buttonear;
    public  String sitename;
    public String res;
    public int c=0;
    private static final String TABLE_EVENTS1="events1";
    private static  final String KEY_SITENAME="Sitename";
    public SQLiteDatabase s;
    public TextView textViewResult;
    DatabaseHandler db=new DatabaseHandler(this);

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

        // components from main.xml

        showInputDialog();



    }

    public void itemClicked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){
            Log.e("kk","gggggg");

        }
    }
    public void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(Dialoguebox1.this);


        View promptView = layoutInflater.inflate(R.layout.layout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Dialoguebox1.this);
        alertDialogBuilder.setView(promptView);

        // final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        // setup a dialog window
          buttonhac=(CheckBox)promptView.findViewById(R.id.hackerrank);
        buttonc=(CheckBox)promptView.findViewById(R.id.codechef);
         buttonear=(CheckBox)promptView.findViewById(R.id.hackerearth);




        List<Contact> contactssite = db.getAllContacts1();
        for (Contact cn : contactssite) {
            Log.e("lol","bho");
            String log = "Sitename: " + cn.getSitename() + "\nResponse:\t" + cn.getRes();
            // Writing Contacts to log
            Log.d("Name: ", log);
            //   textViewResult.setText("SiteName:\t" + cn.getSitename());
            if(cn.getRes().equals("true")){
                Log.e("ll","gg");
//                buttonc=(CheckBox)findViewById(R.id.codechef);

                if(cn.getSitename().equals("Codechef")) {
                    buttonc.setChecked(true);
                    Log.e("jj", "hhh");
                }
                else if(cn.getSitename().equals("Hackerrank")){
                    buttonhac.setChecked(true);
                }
                else if(cn.getSitename().equals("Hackerearth")){
                    buttonear.setChecked(true);
                }
            }
        }

        //db.onUpgrade1(eventsManager);
        //db.onCreate1(s);
        Log.e("kkk","ddd");
        //addListenerOnChkIos();
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  addListenerOnChkIos();
                        if(buttonc.isChecked()) {
                            Log.e("lll", "hhh");
                            //buttonc=(CheckBox)findViewById(R.id.codechef);
                            //buttonc.setChecked(true);
                            sitename = "Codechef";
                            res = "true";
                            Log.e("nnn", "pehla");
                          //  System.out.println(c);
                            db.updateContact(new Contact(sitename, res));
                            //   c++;

                            List<Contact> contactssite = db.getAllContacts1();

                           /* for (Contact cn1 : contactssite) {
                                String log = "Sitename: " + cn1.getSitename() + "\nResponse:\t" + cn1.getRes();
                                // Writing Contacts to log
                                Log.d("Name: ", log);
                                //   textViewResult.setText("SiteName:\t" + cn.getSitename());


                                if (cn1.getRes().equals("false")) {
                                    //         c++;
                                    Log.e("dial", "update");
                                    System.out.println(c);
                                    db.updateContact(new Contact(sitename, res));
                                }
                            }*/

                            Log.d("Reading: ", "Reading all contacts..");
                            // List<Contact> contactssite = db.getAllContacts1();
                            Log.e("ddddd","hello555555");

                         /*   for (Contact cn1 : contactssite) {
                                String log = "Sitename: " + cn1.getSitename() + "\nResponse:\t" + cn1.getRes();
                                // Writing Contacts to log
                                Log.d("Name: ", log);
                             //   textViewResult.setText("SiteName:\t" + cn.getSitename());
                            }*/
                            Intent intent=new Intent(Dialoguebox1.this,MainActivity.class);
                            Dialoguebox1.this.startActivity(intent);

                        }
                        if(!buttonc.isChecked()){
                            Log.e("xxx","www");
                            sitename="Codechef";
                            res="false";
                            c=1;
                            // db.addContact1(new Contact(sitename, res));


                            db.updateContact(new Contact(sitename,res));
                        }
                        if(buttonhac.isChecked()){
                            Log.e("kkk","fff");
                            Log.e("lll", "hhh");
                            //buttonc=(CheckBox)findViewById(R.id.codechef);
                            //buttonc.setChecked(true);
                            sitename = "Hackerrank";
                            res = "true";
                            Log.e("nnn", "pehla");
                            System.out.println(c);
                            db.updateContact(new Contact(sitename, res));
                            //   c++;

                            List<Contact> contactssite = db.getAllContacts1();

                           /* for (Contact cn1 : contactssite) {
                                String log = "Sitename: " + cn1.getSitename() + "\nResponse:\t" + cn1.getRes();
                                // Writing Contacts to log
                                Log.d("Name: ", log);
                                //   textViewResult.setText("SiteName:\t" + cn.getSitename());


                                if (cn1.getRes().equals("false")) {
                                    //         c++;
                                    Log.e("dial", "update");
                                    System.out.println(c);
                                    db.updateContact(new Contact(sitename, res));
                                }
                            }*/

                            Log.d("Reading: ", "Reading all contacts..");
                            // List<Contact> contactssite = db.getAllContacts1();
                            Log.e("ddddd","hello555555");

                         /*   for (Contact cn1 : contactssite) {
                                String log = "Sitename: " + cn1.getSitename() + "\nResponse:\t" + cn1.getRes();
                                // Writing Contacts to log
                                Log.d("Name: ", log);
                             //   textViewResult.setText("SiteName:\t" + cn.getSitename());
                            }*/
                            Intent intent=new Intent(Dialoguebox1.this,MainActivity.class);
                            Dialoguebox1.this.startActivity(intent);

                        }
                        if(!buttonhac.isChecked()){
                            Log.e("xxx","www");
                            sitename="Hackerrank";
                            res="false";
                            c=2;
                            // db.addContact1(new Contact(sitename, res));


                            db.updateContact(new Contact(sitename,res));
                        }
                        if(buttonear.isChecked()){

                            Log.e("lll", "hhh");
                            //buttonc=(CheckBox)findViewById(R.id.codechef);
                            //buttonc.setChecked(true);
                            sitename = "Hackerearth";
                            res = "true";
                            Log.e("nnn", "pehla");
                            System.out.println(c);
                            db.updateContact(new Contact(sitename, res));
                            //   c++;

                            List<Contact> contactssite = db.getAllContacts1();
/*
                            for (Contact cn1 : contactssite) {
                                String log = "Sitename: " + cn1.getSitename() + "\nResponse:\t" + cn1.getRes();
                                // Writing Contacts to log
                                Log.d("Name: ", log);
                                //   textViewResult.setText("SiteName:\t" + cn.getSitename());


                                if (cn1.getRes().equals("false")) {
                                    //         c++;
                                    Log.e("dial", "update");
                                    System.out.println(c);
                                    db.updateContact(new Contact(sitename, res));
                                }
                            }*/

                            Log.d("Reading: ", "Reading all contacts..");
                            // List<Contact> contactssite = db.getAllContacts1();
                            Log.e("ddddd","hello555555");

                         /*   for (Contact cn1 : contactssite) {
                                String log = "Sitename: " + cn1.getSitename() + "\nResponse:\t" + cn1.getRes();
                                // Writing Contacts to log
                                Log.d("Name: ", log);
                             //   textViewResult.setText("SiteName:\t" + cn.getSitename());
                            }*/
                            Intent intent=new Intent(Dialoguebox1.this,MainActivity.class);
                            Dialoguebox1.this.startActivity(intent);


                        }
                        if(!buttonear.isChecked()){
                            Log.e("xxx","www");
                            sitename="Hackerearth";
                            res="false";
                            c=3;
                            // db.addContact1(new Contact(sitename, res));


                            db.updateContact(new Contact(sitename,res));
                            Intent intent=new Intent(Dialoguebox1.this,MainActivity.class);
                            Dialoguebox1.this.startActivity(intent);

                        }

//                        resultText.setText("Hello, " );
                        //                      Log.e("kk","ll");
                        //install();
                        //  StringBuffer result = new StringBuffer();
                        //result.append("IPhone check : ").append(buttonhac.isChecked());




                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Intent intent=new Intent(Dialoguebox1.this,MainActivity.class);
                                Dialoguebox1.this.startActivity(intent);


                            }

                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();

        alert.show();

        //addListenerOnChkIos();
    }


}

