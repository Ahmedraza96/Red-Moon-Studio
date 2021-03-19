package com.example.redmoonstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class viewPhotographer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photographer);

        ListView listPhotographer = findViewById(R.id.listPhotographer);

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

//                                Toast.makeText(ThirdActivity.this,obj.getString("msg"), Toast.LENGTH_LONG).show();


                            CustomListAdapterPhotographer custom_adapter = new CustomListAdapterPhotographer(viewPhotographer.this, obj.getJSONArray("values"));
                            custom_adapter.notifyDataSetChanged();
                            listPhotographer.setAdapter(custom_adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    }
                } else {

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(viewPhotographer.this, "t.getMessage()", Toast.LENGTH_LONG).show();

            }
        });
    }
}
