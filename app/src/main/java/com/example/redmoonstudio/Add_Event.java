package com.example.redmoonstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.collect.ComparisonChain.start;

public class Add_Event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__event);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String data = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<String> call = service.Photographer();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        JSONArray jsonArray = null;
                        JSONObject obj = null;

                        try {
                            obj = new JSONObject(response.body().toString());
                            ArrayList array = new ArrayList<String>();

                            for (int i = 0; i < obj.getJSONArray("name").length(); i++) {
                                JSONObject jsonObject1 = obj.getJSONArray("name").getJSONObject(i);
                                String data = jsonObject1.getString("F_Name");
                                array.add(data);
                            }
//                            array.add(obj.get("name"));
                            ArrayAdapter adapter = new ArrayAdapter(Add_Event.this, android.R.layout.simple_spinner_item, array);
                            spinner.setAdapter(adapter);
                            adapter.notifyDataSetChanged();


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

        EditText Name = findViewById(R.id.Name);
        EditText Venue = findViewById(R.id.Venue);
        EditText Date = findViewById(R.id.Date);
        EditText Time = findViewById(R.id.Time);
        EditText Camera = findViewById(R.id.Camera);
        EditText Contact = findViewById(R.id.Contact);
        EditText Link = findViewById(R.id.Link);
        EditText Price = findViewById(R.id.Price);

        Button button_book = findViewById(R.id.button_book);


        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                Date.setText(sdf.format(myCalendar.getTime()));
            }

        };
        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Add_Event.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        button_book.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<String> call = service.book(Name.getText().toString(),
                        Venue.getText().toString(),
                        Date.getText().toString(),
                        Time.getText().toString(),
                        Camera.getText().toString(),
                        Contact.getText().toString(),
                        Link.getText().toString(),
                        Price.getText().toString(),
                        spinner.getSelectedItem().toString()
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
                                    if (obj.getString("result").equals("Add Successfully!")) {
                                        finish();
                                        Intent Add_Event_intent = new Intent(Add_Event.this, Add_Event.class);

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
