package com.example.geo.rest;

import com.example.geo.business.GeoLocator;
import com.example.geo.output.ListGeoLocationOutput;
import com.example.geo.utils.CustomError;
import com.example.geo.utils.Utils;
import com.example.geo.output.GeoLocationOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/")
public class GeoLoc {

    @Autowired
    GeoLocator geoLocatorSingleton;

    @Autowired
    CustomError customError;

    private static final Logger logger = LogManager.getLogger(GeoLoc.class);

    @Path("getGeo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGeo(String address) {
        try{
            logger.debug("Input : " + address);

            return Response.ok(Utils.getJsonFromObject(geoLocatorSingleton.getGeoLocation(address))).build();
        } catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }

    }

    @Path("getGeoFromFile")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGeoFromFile(@FormDataParam("file") FormDataBodyPart body) {
        Map<String, GeoLocationOutput> locations = new HashMap<>();
        Map<String, String> errors = new HashMap<>();
        List<String> listAddresses;

        try{
            listAddresses = Utils.convertInputStreamToListString(body.getEntityAs(InputStream.class));

            for(String address: listAddresses) {
                logger.debug("File line : " + address);
                try {
                    locations.put(address, geoLocatorSingleton.getGeoLocation(address));
                } catch(Exception e){
                    logger.error(e.getMessage(), e);
                    errors .put(address, customError.getMessage() );
                }
            }

            return Response.ok(Utils.getJsonFromObject(new ListGeoLocationOutput(locations, errors))).build();
        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }

    }
}
