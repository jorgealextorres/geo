package com.example.geo.output;

public class GeoLocation {
    float lattitude;
    float longitude;

    public GeoLocation() {
    }

    public GeoLocation(float lattitude, float longitude) {
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    public float getLattitude() {
        return lattitude;
    }

    public void setLattitude(float lattitude) {
        this.lattitude = lattitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
