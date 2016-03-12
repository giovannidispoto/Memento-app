package com.example.ivan.memento;

/**
 * Created by Ivan on 12/02/2016.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
    public static ArrayList<String>  immaginiProfilo;
    public static ArrayList<String>  fotoCaricate;
    public static ArrayList<String> nomiUtente;
    public static ArrayList<String> descrizione;
    public static ArrayList<String> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.tab1_view, container, false);
        immaginiProfilo = new ArrayList();
        fotoCaricate = new ArrayList();
        nomiUtente = new ArrayList();
        descrizione = new ArrayList();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sp.edit();
        String token = sp.getString("memento_token", ""); //recupero token
        String user = sp.getString("memento_user",""); //recupero username

       // Toast.makeText(getContext(),token,Toast.LENGTH_SHORT).show();
       // Toast.makeText(getContext(),user,Toast.LENGTH_SHORT).show();

       final AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.add("user_id",user); //aggiungo username e token per controllare la chiamata dal server
        rp.add("token",token);

        client.post("http://192.168.1.99/memento/?action=get_photo",rp, new AsyncHttpResponseHandler() {
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    risposta = new String(responseBody, "UTF-8");
                    Toast.makeText(getContext(),risposta,Toast.LENGTH_LONG).show();
                        main = new JSONObject(risposta);
                       /* for(int i = 0; i < main.length(); i++){
                            JSONObject obj = main.getJSONObject(String.valueOf(i));
                            immaginiProfilo.add(obj.getString("avatar"));
                            fotoCaricate.add(obj.getString("photo"));
                            nomiUtente.add(obj.getString("user"));
                            descrizione.add(obj.getString("description"));
                            data.add(obj.getString("data"));

                        }*/


                    }catch (JSONException e) {
                         Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
                    }catch(UnsupportedEncodingException e){
                        Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
                    }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(getContext(), "Errore nella richiesta - Home", Toast.LENGTH_LONG).show();
            }
        });

        mylist = (ListView) V.findViewById(R.id.lista);
        mylist.setAdapter(new CustomAdapter(getActivity(), nomiUtente, immaginiProfilo, fotoCaricate, descrizione, data));

        return V;
    }
}
