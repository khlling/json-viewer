package com.trackd.gymtime.json_viewer.json;

import android.content.Context;

import com.trackd.gymtime.json_viewer.models.ProductPOJO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kev on 19/03/2015.
 */
public class ProductsJSONHandler extends JSONHandler{

    private JSONHandler jsonHandler;
    private JSONObject obj;
    private ProductPOJO product;

    public ProductsJSONHandler(Context context, int position){
        JSONObject obj = newInstance(context, "products.json");
        product = new ProductPOJO();
        processData(obj,position);
    }

    private void processData (JSONObject obj, int position){

        List<String> locationsList = new ArrayList<String>();
        try {
            JSONArray objects =  obj.getJSONArray("objects");
            for(int i=0; i< objects.length();i++){
                JSONObject object = objects.getJSONObject(i);
                product.setId(object.getInt("id"));
                JSONArray pictureDataArray = object.getJSONArray("pictures_data");
                JSONObject pictureDataObject = pictureDataArray.getJSONObject(0);
                JSONObject formats = pictureDataObject.getJSONObject("formats");
                JSONObject u0 = formats.getJSONObject("P" + position);
                locationsList.add(u0.getString("url"));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        product.setImageLocations(locationsList);
    }

    public ProductPOJO getProduct(){
        return product;
    }
}
