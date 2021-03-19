package com.example.redmoonstudio;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomListAdapterPhotographer extends BaseAdapter {


    Activity activity;
    JSONArray jsonArray;
    CustomListAdapterPhotographer(Activity activity, JSONArray jsonArray) {


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
            convertView = activity.getLayoutInflater().inflate(R.layout.photographer, parent, false);


//work here
        TextView textF_Name =(TextView)convertView.findViewById(R.id.text1Date);
        TextView textL_Name =(TextView)convertView.findViewById(R.id.text1Name);
        TextView textGender =(TextView)convertView.findViewById(R.id.textGender);
        TextView textAge =(TextView)convertView.findViewById(R.id.textAge);
        TextView textAddress =(TextView)convertView.findViewById(R.id.textAddress);
        TextView textCell =(TextView)convertView.findViewById(R.id.textCell);
        TextView textExperience =(TextView)convertView.findViewById(R.id.text1Price);

        JSONObject json_data = getItem(position);
        if(null!=json_data ){

            try {
                textF_Name.setText(json_data.getString("F_Name"));
                textL_Name.setText(json_data.getString("L_Name"));
                textGender.setText(json_data.getString("Gender"));
                textAge.setText(json_data.getString("Age"));
                textAddress.setText(json_data.getString("Address"));
                textCell.setText(json_data.getString("Cell"));
                textExperience.setText(json_data.getString("Experience"));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//work ends
        return convertView;
    }
}
