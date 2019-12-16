package com.example.geo.business;

import com.example.geo.output.GeoLocation;
import com.example.geo.utils.mapper.XMLConsumer;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
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

    public GeoLocator() {
    }

    public GeoLocation getGeoLocation(String address) {
        GeoLocation geo = new GeoLocation();

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

        try {
            //String urlLocation = "https://geocoder.ls.hereapi.com/search/6.2/geocode.json?languages=es&maxresults=1&apiKey=zRKCNulsmituY-IuLoyca4d50QYtpjW4C4H5Do6d9MQ";
            //String[] params = {"searchtext=" + URLEncoder.encode(address, "UTF-8")};
            String[] params = {remoteLocatorConfig.getSearchField() + "=" + URLEncoder.encode(address, "UTF-8")};


            InputStream isLocation = XMLConsumer.getResponseFromURL(remoteLocatorConfig.getUrl(), params);

            //String jsonResponse = (InputStreamMapper.convertToListString(isLocation)).toString();
            // System.out.println("location from rest : " + jsonResponse);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(isLocation, "UTF-8"));


            /*

            System.out.println("xml received");

            String aaa = "<Response><MetaInfo><Timestamp>2019-12-16T04:59:42.218Z</Timestamp></MetaInfo><View xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns2:SearchResultsViewType\"><ViewId>0</ViewId><Result><Relevance>1.0</Relevance><MatchLevel>city</MatchLevel><MatchQuality><County>1.0</County><City>1.0</City></MatchQuality><Location><LocationId>NT_rEmofR12M9KayEWS0Xvk6C</LocationId><LocationType>point</LocationType><DisplayPosition><Latitude>41.38804</Latitude><Longitude>2.17001</Longitude></DisplayPosition><NavigationPosition><Latitude>41.38804</Latitude><Longitude>2.17001</Longitude></NavigationPosition><MapView><TopLeft><Latitude>41.4686</Latitude><Longitude>2.05238</Longitude></TopLeft><BottomRight><Latitude>41.3204</Latitude><Longitude>2.22729</Longitude></BottomRight></MapView><Address><Label>Barcelona, Cataluña, España</Label><Country>ESP</Country><State>Cataluña</State><County>Barcelona</County><City>Barcelona</City><PostalCode>08007</PostalCode><AdditionalData key=\"CountryName\">España</AdditionalData><AdditionalData key=\"StateName\">Cataluña</AdditionalData><AdditionalData key=\"CountyName\">Barcelona</AdditionalData></Address></Location></Result></View></Response>";
            isLocation = new ByteArrayInputStream(aaa.getBytes());

            Document doc = InputStreamMapper.convertToXMLDocument(isLocation);

            System.out.println("Xml doc : " + doc.toString());

            Float latitude = Float.valueOf(XMLConsumer.getValueFRomXPath(doc, "/ns2:Search[1]/Response[1]/View[1]/Result[1]/Location[1]/NavigationPosition[1]/Latitude"));
            Float longitude = Float.valueOf(XMLConsumer.getValueFRomXPath(doc, "/ns2:Search[1]/Response[1]/View[1]/Result[1]/Location[1]/NavigationPosition[1]/Longitude"));

            // dummy values
            //geo = new GeoLocation((float)Math.random(), (float)Math.random());
            geo = new GeoLocation(latitude, longitude);


             */

            //Double latitude = JsonPath.read(jsonObject, "$.Response.View[0].Result[0].Location.DisplayPosition.Latitude");
            Double latitude = JsonPath.read(jsonObject, "$." + remoteLocatorResponseConfig.getLatitudeJsonPath());
            //Double longitude = JsonPath.read(jsonObject, "$.Response.View[0].Result[0].Location.DisplayPosition.Longitude");
            Double longitude = JsonPath.read(jsonObject, "$." + remoteLocatorResponseConfig.getLongitudeJsonPath());

            geo.setLatitude(latitude);
            geo.setLongitude(longitude);
        } catch (Exception e){
            e.printStackTrace();
        }

        return geo;
    }
}
