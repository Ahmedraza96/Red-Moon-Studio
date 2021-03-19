package com.example.redmoonstudio;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CustomListAdapterEvents extends BaseAdapter {


    Activity activity;
    JSONArray jsonArray;
    CustomListAdapterEvents(Activity activity, JSONArray jsonArray) {


        this.jsonArray = jsonArray;
        this.activity = activity;
    }


    @Override public int getCount() {
        if(null==jsonArray)
            return 0;
        else
            return jsonArray.length();
    }

    @Override public JSONObject getItem(int position) {
        if(null==jsonArray) return null;
        else
            return jsonArray.optJSONObject(position);
    }

    @Override public long getItemId(int position) {
        JSONObject jsonObject = getItem(position);

        return jsonObject.optLong("id");
    }

    @SuppressLint("SimpleDateFormat")
    @Override public View getView(int position, View convertView, ViewGroup parent) {
        //change in ui in row.xml
        if (convertView == null)
            convertView = activity.getLayoutInflater().inflate(R.layout.event, parent, false);


//work here
        TextView textDate =(TextView)convertView.findViewById(R.id.textDate);
        TextView textName =(TextView)convertView.findViewById(R.id.textName);
        TextView textPhotographer =(TextView)convertView.findViewById(R.id.textPhotographer);
        TextView textCamera =(TextView)convertView.findViewById(R.id.textCamera);
        TextView textTime =(TextView)convertView.findViewById(R.id.textTime);
        TextView textVenue =(TextView)convertView.findViewById(R.id.textVenue);
        TextView textContact =(TextView)convertView.findViewById(R.id.textContact);
        TextView textPrice =(TextView)convertView.findViewById(R.id.textPrice);
        TextView textLink =(TextView)convertView.findViewById(R.id.textLink);

        JSONObject json_data = getItem(position);
        if(null!=json_data ){

            try {
                textDate.setText(json_data.getString("Date"));
                textName.setText(json_data.getString("Name"));
                textPhotographer.setText(json_data.getString("Photographer"));
                textCamera.setText(json_data.getString("Camera"));
                textTime.setText(json_data.getString("Time"));
                textVenue.setText(json_data.getString("Venue"));
                textContact.setText(json_data.getString("Contact"));
                textPrice.setText(json_data.getString("Price"));
                textLink.setText(json_data.getString("Link"));
//                textLink.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//work ends
        return convertView;
    }
}
