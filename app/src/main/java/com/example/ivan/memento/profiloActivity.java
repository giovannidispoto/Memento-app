package com.example.ivan.memento;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class profiloActivity extends AppCompatActivity {

    private static String risposta;
    private static JSONObject main;
    static ImageView profilo;
    GridView grid;
    String[] descrizione = {"Imagine1","Imagine2","Imagine3","Imagine4","Imagine5","Immagine6"};
    String[] immagine = {"http://www.superedo.it/sfondi/sfondi/Animali/Gatti/gatti_69.jpg","http://www.superedo.it/sfondi/sfondi/Animali/Gatti/gatti_69.jpg","http://www.superedo.it/sfondi/sfondi/Animali/Gatti/gatti_69.jpg","http://www.superedo.it/sfondi/sfondi/Animali/Gatti/gatti_69.jpg","http://www.superedo.it/sfondi/sfondi/Animali/Gatti/gatti_69.jpg","http://www.superedo.it/sfondi/sfondi/Animali/Gatti/gatti_69.jpg"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        profilo = (ImageView) findViewById(R.id.fotoprofilo);
        Picasso.with(this).load("http://1.bp.blogspot.com/-Puv12pm3Nxk/TbumeypUYCI/AAAAAAAAB0Q/DdJ_X5eSJJU/s1600/cane+05.jpg").into(profilo);

        CustomGrid adapter = new CustomGrid(this, descrizione, immagine);
        grid=(GridView) findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(profiloActivity.this, "You Clicked at " + descrizione[+position], Toast.LENGTH_SHORT).show();
            }
        });

        profilo = (ImageView) findViewById(R.id.fotoprofilo);
        /*richiesta json con nome profilo e url foto profilo
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


                        Picasso.with(getActivity()).load("Uri").into(profilo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                Toast.makeText(getActivity().getApplicationContext(), "Richiesta fallita - profilo", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRetry(int retryNo) {

            }
        });
        */
    }
}
