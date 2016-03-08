package com.example.ivan.memento;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;

public class MainActivity extends FragmentActivity implements OnClickListener {

    private ViewPager mViewPager;
    private static final int CAMERA_REQUEST = 123;
    private static File foto = null;
    private static String risposta;
    private static JSONObject main;
    private FragmentTabHost mTabHost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("Home"),
                Tab1Fragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("Scopri"),
                Tab2Fragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("Profilo"),
                Tab3Fragment.class, null);
        /*
        final AsyncHttpClient client = new AsyncHttpClient();
        RequestHandle requestHandle = client.get("www.google.it", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    risposta = new String(responseBody, "UTF-8");
                    try {
                        main = new JSONObject(risposta);
                        JSONArray mainArray = main.getJSONArray("results");
                        Toast.makeText(getApplicationContext(), main.toString(), Toast.LENGTH_SHORT).show();
                        for (int i=0; i < mainArray.length(); i++){
                            JSONObject jsonObject = mainArray.getJSONObject(i);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("text"), Toast.LENGTH_SHORT).show();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                Toast.makeText(getApplicationContext(), "bestia eva", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRetry(int retryNo) {

            }
        });
        */

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*try {
                   foto = new File(Environment.getExternalStorageDirectory(), "temp.jpg");

                    Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));
                    intentCamera.putExtra("outputX", 500);
                    intentCamera.putExtra("outputY", 500);
                    intentCamera.putExtra("aspectX", 1);
                    intentCamera.putExtra("aspectY", 1);
                    intentCamera.putExtra("scale", true);
                    startActivityForResult(intentCamera, CAMERA_REQUEST);

                }catch (Exception e){
                    Toast b = Toast.makeText(getApplicationContext(), "Si è verificato un errore durante l'acquisizione dell'immagine: \n" + e.toString(), Toast.LENGTH_LONG);
                    b.show();
                }*/

                final String permission = Manifest.permission.CAMERA;
                if (ContextCompat.checkSelfPermission(MainActivity.this, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {
                        // Show permission rationale
                    } else {
                        // Handle the result in Activity#onRequestPermissionResult(int, String[], int[])
                        ActivityCompat.requestPermissions(this, new String[]{permission}, REQUEST_CAMERA_PERMISSION);
                    }
                } else {
                    // Start CameraActivity
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            //Provo a recuperare i dati, in caso si verifichi qualche errore visualizzo un Toast con la descrizione.
            try {
                Uri photoUri = Uri.fromFile(foto);
                Bitmap fotoDati = android.provider.MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.fromFile(foto));
                Intent a = new Intent(getApplicationContext(), upload_photo.class);
                a.putExtra("photo", photoUri.toString());
                startActivity(a);
            } catch (Exception e) {
                Toast t = Toast.makeText(this, "Si è verificato un errore durante l'acquisizione dell'immagine:\n" + e.toString(), Toast.LENGTH_LONG);
                t.show();
            }

        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onClick(View v) {

    }
}
