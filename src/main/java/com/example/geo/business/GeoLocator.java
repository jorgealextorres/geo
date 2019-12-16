package com.example.geo.business;

import com.example.geo.output.GeoLocation;
import com.example.geo.utils.Utils;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

@Component
public class GeoLocator {

    @Autowired
    private RemoteLocatorConfig remoteLocatorConfig;

    @Autowired
    private RemoteLocatorResponseConfig remoteLocatorResponseConfig;

    private static final Logger logger = LogManager.getLogger(Utils.class);

    public GeoLocator() {
    }

    public GeoLocation getGeoLocation(String address) throws Exception {
        GeoLocation geo = new GeoLocation();

        String[] params = {remoteLocatorConfig.getSearchField() + "=" + URLEncoder.encode(address, "UTF-8")};
        InputStream isLocation = Utils.getResponseFromURL(remoteLocatorConfig.getUrl(), params);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(isLocation, "UTF-8"));

       try {
            Double latitude = JsonPath.read(jsonObject, "$." + remoteLocatorResponseConfig.getLatitudeJsonPath());
            Double longitude = JsonPath.read(jsonObject, "$." + remoteLocatorResponseConfig.getLongitudeJsonPath());

            geo.setLatitude(latitude);
            geo.setLongitude(longitude);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
        }

        return geo;
    }
}
