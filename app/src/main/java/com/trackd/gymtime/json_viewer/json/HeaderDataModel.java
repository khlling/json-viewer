package com.trackd.gymtime.json_viewer.json;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kev on 18/03/2015.
 */
public class HeaderDataModel {

    private JSONHandler jsonHandler;
    private JSONObject obj;

    private String imageLocation;
    private String name;
    private int following;
    private int followers;
    private Double rating;
    private int sellerRating;

    public HeaderDataModel(Context context){

       obj = jsonHandler.newInstance(context, "header.json");
        try {

            //**
            // not the most elegant way of doing this but it's more efficient than parsing
            // the whole file into a map if we know what we're looking for.
            JSONObject pictureData = obj.getJSONObject("picture_data");
            JSONObject formats = pictureData.getJSONObject("formats");
            JSONObject u0 = formats.getJSONObject("U0");
            setImageLocation(u0.getString("url"));

            setFollowers(obj.getInt("followers_count"));
            setFollowing(obj.getInt("following_count"));
            setRating(obj.getDouble("rating"));
            setSellerRating(obj.getInt("seller_ratings"));
            setName(obj.getString("first_name") + " " + obj.getString("last_name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(int sellerRating) {
        this.sellerRating = sellerRating;
    }
}
