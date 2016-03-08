package com.example.ivan.memento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class Registration extends AppCompatActivity {

    private EditText usernameView;
    private EditText passwordView;
    private EditText emailView;
    private EditText nameView;
    private EditText surnameView;
    private EditText date_of_birthView;
    private String risposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Intent i = new Intent(getBaseContext(),MainActivity.class);
       //assegnazione risorse
        usernameView = (EditText) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);
        emailView = (EditText) findViewById(R.id.email);
        nameView = (EditText) findViewById(R.id.name);
        surnameView = (EditText) findViewById(R.id.surname);
        date_of_birthView = (EditText) findViewById(R.id.date_of_birth);
        Button signupButton = (Button) findViewById(R.id.sign_in_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registration();
            }
        });
    }

    private void registration(){

        String username= usernameView.getText().toString();
        String password=passwordView.getText().toString();
        String email=emailView.getText().toString();
        String name=nameView.getText().toString();
        String surname=surnameView.getText().toString();
        String date_of_birth=date_of_birthView.getText().toString();

        final AsyncHttpClient client = new AsyncHttpClient();
        RequestParams ps = new RequestParams();
        try {
            ps.put("username",username);
            ps.put("password",password);
            ps.put("email",email);
            ps.put("name",name);
            ps.put("surname",surname);
            ps.put("date_of_birth",date_of_birth);
        }catch (Exception e) {
            e.printStackTrace();
        }
        //Invio JSON
        client.post("http://192.168.1.99/memento/?action=auth", ps, new AsyncHttpResponseHandler() {
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(getApplicationContext(), "Trasferimento avvenuto con successo", Toast.LENGTH_LONG).show();
                try {
                    risposta = new String(responseBody, "UTF-8");
                    JSONObject main = new JSONObject(risposta);

                    Toast.makeText(getApplicationContext(),main.toString(),Toast.LENGTH_LONG).show();

                }catch (JSONException e) {
                    e.printStackTrace();

                }catch(UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Errore nel trasferimento", Toast.LENGTH_LONG).show();
            }
        });

    }
}
