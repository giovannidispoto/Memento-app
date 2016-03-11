package com.example.ivan.memento;

/**
 * Created by Ivan on 12/02/2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class Tab3Fragment extends Fragment {
    private static String risposta;
    private static JSONObject main;
    static ImageView profilo;
    GridView grid;
    String[] descrizione = {"Imagine1","Imagine2","Imagine3","Imagine4","Imagine5","Immagine6"};
    String[] immagine = {"http://www.superedo.it/sfondi/sfondi/Animali/Gatti/gatti_69.jpg","http://www.superedo.it/sfondi/sfondi/Animali/Gatti/gatti_69.jpg","http://www.superedo.it/sfondi/sfondi/Animali/Gatti/gatti_69.jpg","http://www.superedo.it/sfondi/sfondi/Animali/Gatti/gatti_69.jpg","http://www.superedo.it/sfondi/sfondi/Animali/Gatti/gatti_69.jpg","http://www.superedo.it/sfondi/sfondi/Animali/Gatti/gatti_69.jpg"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.tab3_view, container, false);

        profilo = (ImageView) V.findViewById(R.id.fotoprofilo);
        Picasso.with(getActivity()).load("http://1.bp.blogspot.com/-Puv12pm3Nxk/TbumeypUYCI/AAAAAAAAB0Q/DdJ_X5eSJJU/s1600/cane+05.jpg").into(profilo);

        CustomGrid adapter = new CustomGrid(getContext(), descrizione, immagine);
        grid=(GridView)V.findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getContext(), "You Clicked at " + descrizione[+position], Toast.LENGTH_SHORT).show();

            }
        });

        profilo = (ImageView) V.findViewById(R.id.fotoprofilo);
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
        return V;
    }
}

