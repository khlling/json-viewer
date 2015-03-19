package com.trackd.gymtime.json_viewer.json;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kev on 19/03/2015.
 */
public class ProductsDataModel {

    private JSONHandler jsonHandler;
    private JSONObject obj;

    private int id;
    private List<String> imageLocations;

    public ProductsDataModel(Context context, int position){
        obj = jsonHandler.newInstance(context, "products.json");
        List<String> locationsList = new ArrayList<String>();
        try {
           JSONArray objects =  obj.getJSONArray("objects");
            for(int i=0; i< objects.length();i++){
                JSONObject object = objects.getJSONObject(i);
                setId(object.getInt("id"));
                JSONArray pictureDataArray = object.getJSONArray("pictures_data");
                JSONObject pictureDataObject = pictureDataArray.getJSONObject(0);
                JSONObject formats = pictureDataObject.getJSONObject("formats");
                JSONObject u0 = formats.getJSONObject("P" + position);
                locationsList.add(u0.getString("url"));
            }
        }catch (JSONException e){
        e.printStackTrace();
        }
        setImageLocations(locationsList);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getImageLocations() {
        return imageLocations;
    }

    public void setImageLocations(List<String> imageLocation) {
        this.imageLocations = imageLocation;
    }
}
