package com.example.redmoonstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Photographer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer);

        EditText F_Name = findViewById(R.id.Name);
        EditText L_Name = findViewById(R.id.Venue);
        EditText gender = findViewById(R.id.Date);
        EditText age = findViewById(R.id.Time);
        EditText address = findViewById(R.id.Camera);
        EditText cell = findViewById(R.id.Contact);
        EditText experience = findViewById(R.id.Link);
        Button button_add = findViewById(R.id.button_book);


        button_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                GetDataServiceAdd service = RetrofitClientInstance.getRetrofitInstance().create(GetDataServiceAdd.class);
                Call<String> call = service.add(F_Name.getText().toString(),
                        L_Name.getText().toString(),
                        gender.getText().toString(),
                        age.getText().toString(),
                        address.getText().toString(),
                        cell.getText().toString(),
                        experience.getText().toString()
                );
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {

                                JSONArray jsonArray = null;
                                JSONObject obj = null;

                                try {
                                    obj = new JSONObject(response.body().toString());

                                    Toast.makeText(getApplicationContext(), obj.getString("result"), Toast.LENGTH_LONG).show();
                                    if(obj.getString("result").equals("Add Successfully!")) {
                                        finish();
                                        Intent Add_Event_intent = new Intent(Photographer.this, Photographer.class);

                                        startActivity(Add_Event_intent);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "t.getMessage()", Toast.LENGTH_LONG).show();

                    }
                });

            }
        });
}
}