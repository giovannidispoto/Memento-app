package com.example.ivan.memento;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class postActivity extends AppCompatActivity {

    private static String risposta;
    private static JSONObject main;
    static ListView mylist;

    public static ArrayList<String> immaginiProfilo;
    public static ArrayList<String> fotoCaricate;
    public static ArrayList<String> nomiUtente;
    public static ArrayList<String> commenti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        immaginiProfilo = new ArrayList<>();
        fotoCaricate = new ArrayList<>();
        nomiUtente = new ArrayList<>();
        commenti = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            immaginiProfilo.add("http://www.grandain.com/wp-content/uploads/2014/04/cane_1.jpg");
            nomiUtente.add("Mario Testa");
            commenti.add("ciao");
        }

        final AsyncHttpClient client = new AsyncHttpClient();

        client.post("http://www.google.it", new AsyncHttpResponseHandler() {
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    risposta = new String(responseBody, "UTF-8");
                    try {
                        main = new JSONObject(risposta);
                        for(int i = 0; i < main.length(); i++){
                            JSONObject obj = main.getJSONObject(String.valueOf(i));
                            immaginiProfilo.add(obj.getString("profile_pic"));
                            fotoCaricate.add(obj.getString("photo"));
                            nomiUtente.add(obj.getString("user"));
                            commenti.add(obj.getString("description"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(postActivity.this, "Errore nella richiesta - Home", Toast.LENGTH_LONG).show();
            }
        });


        mylist = (ListView) findViewById(R.id.listView);
        mylist.setAdapter(new AdapterListaCommenti(postActivity.this, nomiUtente, immaginiProfilo, commenti));



    }
}
