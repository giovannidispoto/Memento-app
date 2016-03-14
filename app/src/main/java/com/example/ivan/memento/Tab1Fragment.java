package com.example.ivan.memento;

/**
 * Created by Ivan on 12/02/2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Tab1Fragment extends Fragment {

    private static String risposta;
    private static JSONObject main;
    static ListView mylist;

    //Array che conterranno le foto di profilo - nome utente - foto caricata
    public static ArrayList<String> immaginiProfilo;
    public static ArrayList<String> fotoCaricate;
    public static ArrayList<String> nomiUtente;
    public static ArrayList<String> descrizione;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.tab1_view, container, false);
        immaginiProfilo = new ArrayList<>();
        fotoCaricate = new ArrayList<>();
        nomiUtente = new ArrayList<>();
        descrizione = new ArrayList<>();

        for(int i = 0; i < 2; i++){
            immaginiProfilo.add("http://www.grandain.com/wp-content/uploads/2014/04/cane_1.jpg");
            fotoCaricate.add("http://www.grandain.com/wp-content/uploads/2014/04/cane_1.jpg");
            nomiUtente.add("Mario Testa");
            descrizione.add("ciao");
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
                            descrizione.add(obj.getString("description"));
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
                Toast.makeText(getContext(), "Errore nella richiesta - Home", Toast.LENGTH_LONG).show();
            }
        });


        mylist = (ListView) V.findViewById(R.id.lista);
        mylist.setAdapter(new CustomAdapter(getActivity() , nomiUtente, immaginiProfilo, fotoCaricate, descrizione));

        return V;
    }
}
