package com.example.geo.utils.mapper;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class XMLConsumer {

    public static InputStream getXMLFromURL(String urlInput, String[] params) throws IOException {

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

        System.out.println(urlInputBuild);

        URL url = new URL(urlInputBuild);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        //conn.setRequestProperty("Accept", "application/xml");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        return conn.getInputStream();
    }

    public static String getValueFRomXPath(Document doc, String xpathInput) throws XPathExpressionException {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile(xpathInput);
        NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        System.out.println(nl.toString());

        return nl.item(0).getNodeValue();
    }
}
