package app.the.iway.codersinc.Drawer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.the.iway.codersinc.R;


/**
 * Created by dell pc on 02-12-2016.
 */
public class AboutUsActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.rgb(55,71,79));
        setTitle("About Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button button = (Button) findViewById(R.id.feedbackbutton);
        button.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return  true;
    }

    public void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(AboutUsActivity.this);


        View promptView = layoutInflater.inflate(R.layout.layout1, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AboutUsActivity.this);
        alertDialogBuilder.setView(promptView);
        final EditText editText = (EditText) promptView.findViewById(R.id.editText);
        // setup a dialog window
        alertDialogBuilder.setTitle("Your Message");
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  addListenerOnChkIos();
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/html");
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", "codersinc2016@gmail.com", null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, editText.getText().toString() );

                        startActivity(Intent.createChooser(emailIntent, "Send email..."));
                        Toast.makeText(AboutUsActivity.this, "Thanks for your responce", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                /*Intent intent=new Intent(AboutUsActivity.this,MainActivity.class);
                                AboutUsActivity.this.startActivity(intent);*/


                            }

                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();

        alert.show();

        //addListenerOnChkIos();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
