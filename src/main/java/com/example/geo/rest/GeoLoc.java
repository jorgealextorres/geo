package com.example.geo.rest;

import com.example.geo.business.GeoLocator;
import com.example.geo.output.ErrorOutput;
import com.example.geo.output.ListSimpleSearchOutput;
import com.example.geo.output.SimpleSearchOutput;
import com.example.geo.utils.CustomError;
import com.example.geo.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

            SimpleSearchOutput simpleSearchOutput = new SimpleSearchOutput(address, geoLocatorSingleton.getGeoLocation(address));

            return Response.ok(Utils.getJsonFromObject(simpleSearchOutput)).build();
        } catch(Exception e){
            logger.error(e.getMessage(), e);

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorOutput(address, customError.getMessage())).build();
        }

    }

    @Path("getGeoFromFile")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGeoFromFile(@FormDataParam("file") FormDataBodyPart body) {
        List<SimpleSearchOutput> locations = new ArrayList<>();
        List<ErrorOutput> errors = new ArrayList<>();
        List<String> listAddresses;
        SimpleSearchOutput simpleSearchOutput;
        ErrorOutput errorOutput;

        try{
            listAddresses = Utils.convertInputStreamToListString(body.getEntityAs(InputStream.class));

            for(String address: listAddresses) {
                logger.debug("File line : " + address);

                try {
                    simpleSearchOutput = new SimpleSearchOutput(address, geoLocatorSingleton.getGeoLocation(address));

                    locations.add(simpleSearchOutput);
                } catch(Exception e){
                    logger.error(e.getMessage(), e);

                    errorOutput = new ErrorOutput(address, customError.getMessage());

                    errors.add(errorOutput);
                }
            }

            return Response.ok(Utils.getJsonFromObject(new ListSimpleSearchOutput(locations, errors))).build();
        } catch(Exception e){
            logger.error(e.getMessage(), e);

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Utils.getJsonFromObject(customError.getMessage())).build();
        }

    }
}
