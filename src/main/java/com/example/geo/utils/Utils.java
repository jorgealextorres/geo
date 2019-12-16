package com.example.geo.utils;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {

    private static final Logger logger = LogManager.getLogger(Utils.class);

    public static List<String> convertInputStreamToListString(InputStream is){
        List<String> list = new ArrayList<>();

        Scanner sc = new Scanner(is);

        while(sc.hasNext()){
            list.add(sc.nextLine());
        }

        if(CollectionUtils.isEmpty(list)){
            list = null;
        }

        return list;
    }

    public static String getJsonFromObject(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static InputStream getResponseFromURL(String urlInput, String[] params) throws IOException {

        String urlInputBuild = urlInput;
        String queryChart = "?";

        if(params != null && params.length > 0){
            if(!urlInputBuild.contains(queryChart)){
                urlInputBuild = urlInputBuild.concat(queryChart);
            }
            for(String param: params){
                urlInputBuild = urlInputBuild.concat("&").concat(param);
            }
        }

        logger.debug("URL : " + urlInputBuild);

        URL url = new URL(urlInputBuild);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        return conn.getInputStream();
    }
}
