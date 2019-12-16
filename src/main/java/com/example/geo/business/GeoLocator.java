package com.example.geo.business;

import com.example.geo.output.GeoLocation;
import com.example.geo.utils.mapper.InputStreamMapper;
import com.example.geo.utils.mapper.XMLConsumer;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@Component
public class GeoLocator {
    private InputStream isXSD;

    public GeoLocator() {
    }

    public InputStream getIsXSD() {
        return isXSD;
    }

    public void setIsXSD(InputStream isXSD) {
        this.isXSD = isXSD;
    }

    public GeoLocation getGeoLocation(String address) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        GeoLocation geo = null;

        // code : https://www.mkyong.com/webservices/jax-rs/restfull-java-client-with-java-net-url/
        // call : https://geocoder.ls.hereapi.com/search/6.2/geocode.json?languages=es&maxresults=1&searchtext=Barcelona&apiKey=zRKCNulsmituY-IuLoyca4d50QYtpjW4C4H5Do6d9MQ
        // params : String param = URLEncoder.encode(param1Before, "UTF-8");
        // path : x.Response.View[0].Result[0].Location.NavigationPosition[0].Latitude
        // path : x.Response.View[0].Result[0].Location.NavigationPosition[0].Longitude
        // https://stackoverflow.com/questions/8233542/parse-a-nested-json-using-gson

        // Si lo validamos xml con xsd
        // call : https://geocoder.ls.hereapi.com/search/6.2/geocode.xml?languages=es&maxresults=1&searchtext=Barcelona&apiKey=zRKCNulsmituY-IuLoyca4d50QYtpjW4C4H5Do6d9MQ
        // get xsd : https://geocoder.ls.hereapi.com/6.2/xsd/LBSP-Search-Search.xsd?apiKey=zRKCNulsmituY-IuLoyca4d50QYtpjW4C4H5Do6d9MQ
        // xml validation against xsd : https://stackoverflow.com/questions/6815579/validating-xml-against-xsd
        // Property : version -> 6.2
        // Property : pathLatitude -> /ns2:Search[1]/Response[1]/View[1]/Result[1]/Location[1]/NavigationPosition[1]/Latitude
        // Property : pathLongitude -> /ns2:Search[1]/Response[1]/View[1]/Result[1]/Location[1]/NavigationPosition[1]/Longitude
        /*
        InputStream input;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder parser = factory.newDocumentBuilder();
        Document dc= parser.parse(input);

        - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile("//Type[@type_id=\"4218\"]");
        NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
         */
/*
        String urlLocation = "https://geocoder.ls.hereapi.com/search/6.2/geocode.xml?languages=es&maxresults=1&apiKey=zRKCNulsmituY-IuLoyca4d50QYtpjW4C4H5Do6d9MQ";
        String[] params = {"searchtext=" + URLEncoder.encode(address, "UTF-8")};

        InputStream isLocation = XMLConsumer.getXMLFromURL(urlLocation, params);

        Document doc = InputStreamMapper.convertToXMLDocument(isLocation);

        Float latitude = Float.valueOf(XMLConsumer.getValueFRomXPath(doc, "/ns2:Search[1]/Response[1]/View[1]/Result[1]/Location[1]/NavigationPosition[1]/Latitude"));
        Float longitude = Float.valueOf(XMLConsumer.getValueFRomXPath(doc, "/ns2:Search[1]/Response[1]/View[1]/Result[1]/Location[1]/NavigationPosition[1]/Longitude"));
*/
        // dummy values
        geo = new GeoLocation((float)Math.random(), (float)Math.random());
        //geo = new GeoLocation(latitude, longitude);

        return geo;
    }
}
