package app.the.iway.codersinc.Drawer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import app.the.iway.codersinc.Contests.Contact;
import app.the.iway.codersinc.Contests.DatabaseHandler;
import app.the.iway.codersinc.Drawer.graph.GraphActivity;
import app.the.iway.codersinc.Drawer.graph.GraphActivity2;
import app.the.iway.codersinc.LoginSignup.SignupActivity;
import app.the.iway.codersinc.R;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    public final int SELECT_PHOTO = 1;
    public ImageView imageView;
    public static DatabaseHandler db;
    public static SignupActivity obj;
    public static Button button;
    public static  int counter;
    ImageButton image9,image10,editimage;
    TextView clickhere ;
    LinearLayout changePasswordLayout,clickhereLayout;
    Button reset,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db=new DatabaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.rgb(55,71,79));
        setTitle("Profile");
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout toolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        toolbarLayout.setBackgroundColor(Color.rgb(144,164,174));

        // Button button1=(Button)findViewById(R.id.resetprofile);
       /* button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
             Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");                             //edit button
                    startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                    edit();
                    int flag=1;

            }
        });*/
        reset = (Button)findViewById(R.id.reset_btn);
        cancel = (Button)findViewById(R.id.cancel_btn);
        clickhere = (TextView)findViewById(R.id.clickhere);
        changePasswordLayout = (LinearLayout)findViewById(R.id.changepassword_layout);
        clickhereLayout = (LinearLayout)findViewById(R.id.clickhere_layout);
        editimage = (ImageButton) findViewById(R.id.editprofile);
        reset.setOnClickListener(this);
        cancel.setOnClickListener(this);
        clickhere.setOnClickListener(this);
        editimage.setOnClickListener(this);
        image9 = (ImageButton)findViewById(R.id.tvNumber9);
        image10 = (ImageButton)findViewById(R.id.tvNumber10);
        image9.setOnClickListener(this);
        image10.setOnClickListener(this);
        ImageButton button = (ImageButton) findViewById(R.id.user_profile_photo);
        imageView = (ImageView) findViewById(R.id.imageView);
        List<Contact> contacts1 = db.getAllContacts3();
        if(contacts1.size()==0) {


            editimage.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                   // counter=0;
                   // edit();
                }
            });
       }
       /* else if(contacts1.size()>0) {


            ImageButton imageButton = (ImageButton) findViewById(R.id.editprofile);

            imageButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image*//*");
                    startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                    counter = 1;
                    //edit();


                }
            });

        }*/
        else
        {
            display1();
            //edit();
        }


        TextView codechef= (TextView)findViewById(R.id.tvNumber1);
        TextView hackerrank= (TextView)findViewById(R.id.tvNumber2);
        TextView hackerearth= (TextView)findViewById(R.id.tvNumber3);
        List<Contact> contacts = db.getAllContacts2();
        for (Contact cn : contacts) {
            String s=cn.getSitename();
            if(s.equals("Codechef")){
                codechef.setText(cn.getHandle());

            }
            else if(s.equals("Hackerrank")){
                hackerrank.setText(cn.getHandle());

            }
            else if (s.equals("Hackerearth")){
                hackerearth.setText(cn.getHandle());
            }

        }

    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return  true;
    }

    @Override
    public void onClick(View v) {
        if(v==image9){
            Intent c=new Intent(this, GraphActivity.class);
            startActivity(c);
        }
        if(v==image10){
            Intent cf=new Intent(this, GraphActivity2.class);
            startActivity(cf);
        }
        if(v==clickhere){
            changePasswordLayout.setVisibility(View.VISIBLE);
            clickhereLayout.setVisibility(View.GONE);
        }
        if(v==reset){
            //check old password and update password
        }
        if(v==cancel){
            changePasswordLayout.setVisibility(View.GONE);
            clickhereLayout.setVisibility(View.VISIBLE);
        }
    }

    public class DbBitmapUtility {

        // convert from bitmap to byte array
        public  byte[] getBytes(Bitmap bitmap) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            return stream.toByteArray();
        }

        // convert from byte array to bitmap
        public  Bitmap getImage(byte[] image) {
            return BitmapFactory.decodeByteArray(image, 0, image.length);
        }
    }
    public void edit(){
        Log.e("aaya","edit");
        ImageButton button=(ImageButton)findViewById(R.id.user_profile_photo);
        button.setVisibility(View.GONE);
    }
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        //getBytes(selectedImage);
                        final String n="shashank";
                        ImageButton imageButton=(ImageButton)findViewById(R.id.editprofile);
                       // imageView = (ImageView) findViewById(R.id.imageView);

                       /* if(counter==0){
                            db.addEntry(new Contact(n,getBytes(selectedImage),null,null));
                            display1();
                            Log.e("1","imageadd");
                           // edit();

                        }*/
                        /*else if(counter==1){
                            db.updateContact3(new Contact(n,getBytes(selectedImage),null,null));
                            display1();
                            Log.e("2","imageup");
                            //edit();

                        }*/
                        db.addEntry(new Contact(n,getBytes(selectedImage),null,null));
                        display1();
                            // //edit button





                       /* List<Contact> contacts = db.getAllContacts3();
                        for (Contact cn : contacts) {
                            Log.e("aaya yahan",getImage(cn.getImage()).toString());
                            Bitmap image1=getImage(cn.getImage());
                            imageView.setImageBitmap(image1
                            );
                        }*/
                        // imageView.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }
    public void display1(){
        List<Contact> contacts = db.getAllContacts3();
        for (Contact cn : contacts) {
            //Log.e("pro","aaya yahan");
            //Log.e("image",getImage(cn.getImage()).toString());
            Bitmap image1=getImage(cn.getImage());
            imageView.setImageBitmap(image1
            );
        }
        edit();

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}