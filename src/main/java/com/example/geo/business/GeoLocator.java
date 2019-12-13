package com.example.geo.business;

import com.example.geo.output.GeoLocation;

public class GeoLocator {

    public static GeoLocation getGeoLocation(String address){
        GeoLocation geo = null;

        // dummy values
        geo = new GeoLocation((float)Math.random(), (float)Math.random());

        return geo;
    }
}
