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

public class CustomListAdapterReports extends BaseAdapter {


    Activity activity;
    JSONArray jsonArray;
    CustomListAdapterReports(Activity activity, JSONArray jsonArray) {


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

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        //change in ui in row.xml
        if (convertView == null)
            convertView = activity.getLayoutInflater().inflate(R.layout.report, parent, false);


//work here
        TextView textDate =(TextView)convertView.findViewById(R.id.text1Date);
        TextView textName =(TextView)convertView.findViewById(R.id.text1Name);
        TextView textPrice =(TextView)convertView.findViewById(R.id.text1Price);

        JSONObject json_data = getItem(position);
        if(null!=json_data ){

            try {
                textDate.setText(json_data.getString("Date"));
                textName.setText(json_data.getString("Name"));
                textPrice.setText(json_data.getString("Price"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//work ends
        return convertView;
    }
}
