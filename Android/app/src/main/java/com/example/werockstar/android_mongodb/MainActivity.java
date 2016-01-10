package com.example.werockstar.android_mongodb;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername;
    EditText editTextPassword;

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new saveToMongo().execute("http://10.0.3.2:5000/");
            }
        });
    }

    private class saveToMongo extends AsyncTask<String, Void, Void> {

        private OkHttpClient client;
        private RequestBody body;
        private ProgressDialog dialog;

        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            client = new OkHttpClient();

            body = new FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .build();

            Request request = new Request.Builder()
                    .url(params[0])
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful())
                    Log.d(MainActivity.class.getSimpleName(), "success");
                else
                    Log.d(MainActivity.class.getSimpleName() , response.message());
            } catch (IOException e) {
                Log.d(MainActivity.class.getSimpleName(), "Error : " + e.getMessage().toString());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            dialog.dismiss();
        }
    }
}
