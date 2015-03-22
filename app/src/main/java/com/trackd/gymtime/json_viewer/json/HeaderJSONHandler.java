package com.trackd.gymtime.json_viewer.json;

import android.content.Context;

import com.trackd.gymtime.json_viewer.models.HeaderPOJO;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kev on 18/03/2015.
 */
public class HeaderJSONHandler extends JSONHandler{
    HeaderPOJO header;


    public HeaderJSONHandler(Context context){
       JSONObject obj = newInstance(context, "header.json");
        header = new HeaderPOJO();
       processData(obj);
    }


    private void processData(JSONObject obj) {

        try {

            //**
            // not the most elegant way of doing this but it's more efficient than parsing
            // the whole file into a map if we know what we're looking for.
            JSONObject pictureData = obj.getJSONObject("picture_data");
            JSONObject formats = pictureData.getJSONObject("formats");
            JSONObject u0 = formats.getJSONObject("U0");
            header.setImageLocation(u0.getString("url"));
            header.setFollowers(obj.getInt("followers_count"));
            header.setFollowing(obj.getInt("following_count"));
            header.setRating(obj.getDouble("rating"));
            header.setSellerRating(obj.getInt("seller_ratings"));
            header.setName(obj.getString("first_name") + " " + obj.getString("last_name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public HeaderPOJO getHeader(){
        return header;
    }
}
