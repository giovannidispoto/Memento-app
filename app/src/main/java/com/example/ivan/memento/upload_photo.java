package com.example.ivan.memento;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class upload_photo extends Activity {

    private static final int CAMERA_REQUEST = 123;
    private static File foto = null;
    private static ImageView imageView;
    private static String uri;
    private static EditText desc;
    private static Uri photoUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getWindow().setTitle("Inserisci una foto");
        ImageView img = (ImageView) findViewById(R.id.visualizza);
        desc = (EditText) findViewById(R.id.descrizione);
        uri = getIntent().getStringExtra("photo");
        Toast.makeText(getApplicationContext(),uri,Toast.LENGTH_LONG).show();
        try {
            Bitmap photo = android.provider.MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(uri));
            //Bitmap res = scaleDown(photo,img.getMaxWidth(),true);
            img.setImageBitmap(photo);
        }catch(IOException e){
            e.printStackTrace();;
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AsyncHttpClient client = new AsyncHttpClient();
                File foto = new File(uri);
                RequestParams ps = new RequestParams();
                try {
                    ps.put("file_upload", new FileInputStream(foto));
                    //ps.put("descrizione", desc.getText());
                }catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                client.post("http://192.168.137.97/jmg/?action=up", ps, new AsyncHttpResponseHandler() {
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        Toast.makeText(getApplicationContext(), "Trasferimento avvenuto con successo", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Toast.makeText(getApplicationContext(), "Errore nel trasferimento", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    /*public static Bitmap scaleDown(Bitmap realImage, float maxImageSize, boolean filter){
        float ratio = Math.min((float) maxImageSize / realImage.getWidth(), (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());
        Bitmap newBtp = Bitmap.createScaledBitmap(realImage,width,height,filter);
        return newBtp;
    }*/
}
