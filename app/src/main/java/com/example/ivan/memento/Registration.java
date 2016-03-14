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
        Intent i = new Intent(getBaseContext(), MainActivity.class);
        //assegnazione risorse
        usernameView = (EditText) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);
        emailView = (EditText) findViewById(R.id.email);
        nameView = (EditText) findViewById(R.id.name);
        surnameView = (EditText) findViewById(R.id.surname);
        date_of_birthView = (EditText) findViewById(R.id.date_of_birth);
        Button signupButton = (Button) findViewById(R.id.signup);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registration();
                startActivity(new Intent(Registration.this,MainActivity.class));
            }
        });
    }

    private void registration() {

        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();
        String email = emailView.getText().toString();
        String name = nameView.getText().toString();
        String surname = surnameView.getText().toString();
        String date_of_birth = date_of_birthView.getText().toString();

        final AsyncHttpClient client = new AsyncHttpClient();
        RequestParams ps = new RequestParams();
        boolean cancel = false;
        View focusView = null;
        //controllo validità campi
        try {
            if (TextUtils.isEmpty(username)) {//il campo non deve essere vuoto
                usernameView.setError(getString(R.string.error_field_required));
                focusView = usernameView;
                cancel = true;
            } else if (!isUsernameValid(username)) {//username.length>4
                usernameView.setError(getString(R.string.error_invalid_username));
                focusView = usernameView;
                cancel = true;
            }
            if (TextUtils.isEmpty(password)) {//il campo non deve essere vuoto
                passwordView.setError(getString(R.string.error_field_required));
                focusView = passwordView;
                cancel = true;
            } else if (!isPasswordValid(password)) {//password.length>8
                passwordView.setError(getString(R.string.error_invalid_password));
                focusView = usernameView;
                cancel=true;
            }
            if (TextUtils.isEmpty(email)) {//il campo non deve essere vuoto
                emailView.setError(getString(R.string.error_field_required));
                focusView = emailView;
                cancel = true;
            }
            if (TextUtils.isEmpty(name)) {//il campo non deve essere vuoto
                nameView.setError(getString(R.string.error_field_required));
                focusView = nameView;
                cancel = true;
            }
            if (TextUtils.isEmpty(surname)) {//il campo non deve essere vuoto
                surnameView.setError(getString(R.string.error_field_required));
                focusView = surnameView;
                cancel = true;
            }
            if (TextUtils.isEmpty(date_of_birth)) {//il campo non deve essere vuoto
                date_of_birthView.setError(getString(R.string.error_field_required));
                focusView = date_of_birthView;
                cancel = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            ps.put("username", username);
            ps.put("password", password);
            ps.put("email", email);
            ps.put("name", name);
            ps.put("surname", surname);
            ps.put("date_of_birth", date_of_birth);
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

                        Toast.makeText(getApplicationContext(), main.toString(), Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        e.printStackTrace();

                    } catch (UnsupportedEncodingException e) {
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

    private boolean isUsernameValid(String username) {
        return username.length() > 4;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 8;
    }
}