package com.example.geo.rest;

import com.example.geo.business.GeoLocator;
import com.example.geo.mapper.InputStreamToListString;
import com.example.geo.mapper.JsonMapper;
import com.example.geo.output.GeoLocation;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/")
public class GeoLoc {

    @Path("getGeo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGeo(String address) {
        try{
            System.out.println("Input : " + address);
            //throw new Exception("error1");
            return Response.ok(JsonMapper.getJsonFromObject(GeoLocator.getGeoLocation(address))).build();
        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }

    }

    @Path("getGeoFromFile")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGeoFromFile(@FormDataParam("file") FormDataBodyPart body) {
        Map<String, GeoLocation> locations = new HashMap<>();
        List<String> listAddresses;

        try{
            listAddresses = InputStreamToListString.getListString(body.getEntityAs(InputStream.class));

            for(String address: listAddresses) {
                System.out.println("linea : " + address);
                locations.put(address, GeoLocator.getGeoLocation(address));
            }

            //throw new Exception("error2");

            return Response.ok(JsonMapper.getJsonFromObject(locations)).build();
        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }

    }
}
