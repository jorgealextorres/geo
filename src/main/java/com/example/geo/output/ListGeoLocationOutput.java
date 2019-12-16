package com.example.geo.output;

import java.util.HashMap;
import java.util.Map;

public class ListGeoLocationOutput {
    Map<String, GeoLocationOutput> locations = new HashMap<>();
    Map<String, String> errors = new HashMap<>();

    public ListGeoLocationOutput(Map<String, GeoLocationOutput> locations, Map<String, String> errors) {
        this.locations = locations;
        this.errors = errors;
    }
}
