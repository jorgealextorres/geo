package com.example.geo.business;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RemoteLocatorResponseConfig {
    @Value("${LocatorREST.Response.latitudeJsonPath}")
    private String latitudeJsonPath;

    @Value("${LocatorREST.Response.longitudeJsonPath}")
    private String longitudeJsonPath;

    public RemoteLocatorResponseConfig() {
    }

    public RemoteLocatorResponseConfig(String latitudeJsonPath, String longitudeJsonPath) {
        this.latitudeJsonPath = latitudeJsonPath;
        this.longitudeJsonPath = longitudeJsonPath;
    }

    public String getLatitudeJsonPath() {
        return latitudeJsonPath;
    }

    public void setLatitudeJsonPath(String latitudeJsonPath) {
        this.latitudeJsonPath = latitudeJsonPath;
    }

    public String getLongitudeJsonPath() {
        return longitudeJsonPath;
    }

    public void setLongitudeJsonPath(String longitudeJsonPath) {
        this.longitudeJsonPath = longitudeJsonPath;
    }
}
