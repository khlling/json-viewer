package com.trackd.gymtime.json_viewer.json;

import android.content.Context;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Kev on 18/03/2015.
 */
public class JSONHandler {

    public static JSONObject newInstance (Context context, String asset){
        JSONObject obj = null;

        try {

            obj = new JSONObject(loadJSONFromAsset(context, asset));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static String loadJSONFromAsset(Context context, String asset) {
        String json = null;
        try {

            InputStream is = context.getAssets().open(asset);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
