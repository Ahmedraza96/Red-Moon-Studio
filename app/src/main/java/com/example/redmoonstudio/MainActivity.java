package com.example.redmoonstudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.installations.FirebaseInstallations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = findViewById(R.id.login);
        EditText Email = findViewById(R.id.Email);
        EditText Password = findViewById(R.id.Password);

        SharedPreferences sharedPre2 = getSharedPreferences("login_info", MODE_PRIVATE);
        if (sharedPre2.getBoolean("logged_in", false)) {

            Intent activityChangeIntent = new Intent(MainActivity.this, Dashboard.class);
            startActivity(activityChangeIntent);
            finish();

        }
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<String> call = service.login(Email.getText().toString(), Password.getText().toString());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {

                                JSONArray jsonArray = null;
                                JSONObject obj = null;

                                try {
                                    obj = new JSONObject(response.body().toString());

                                    Toast.makeText(MainActivity.this, obj.getString("result"), Toast.LENGTH_LONG).show();
                                    if(obj.getString("code").equals("1")){
                                        SharedPreferences sharedPre = getSharedPreferences("login_info", MODE_PRIVATE);
                                        sharedPre.edit().putBoolean("logged_in", true).commit();
                                        Intent activityChangeIntent = new Intent(MainActivity.this, Dashboard.class);
                                        startActivity(activityChangeIntent);
                                        finish();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            } else {
                                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
            }


        });


    }
}