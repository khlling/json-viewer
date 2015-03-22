package com.trackd.gymtime.json_viewer.models;

import java.util.List;

/**
 * Created by Kev on 22/03/2015.
 */
public class ProductPOJO {

    private int id;
    private List<String> imageLocations;

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
