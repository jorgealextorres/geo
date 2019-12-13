package com.example.geo.rest;

import com.example.geo.mapper.InputStreamToListString;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

@Path("/")
public class GeoLoc {

    @Path("getGeo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGeo(String address) {
        String result;
        try{
            System.out.println("Input : " + address);
            throw new Exception("error1");
//            result = address;
//            return Response.ok(JsonMapper.getJsonFromObject(result)).build();
        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }

    }

    @Path("getGeoFromFile")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGeoFromFile(@FormDataParam("file") FormDataBodyPart body) {
        String result;
        List<String> listAddresses;

        try{

            listAddresses = InputStreamToListString.getListString(body.getEntityAs(InputStream.class));

/*            InputStream is = body.getEntityAs(InputStream.class);
            Scanner sc = new Scanner(is);

            while(sc.hasNext()){
                System.out.println("linea : " + sc.nextLine());
            }*/

            for(String s: listAddresses) {
                System.out.println("linea : " + s);
            }

            throw new Exception("error2");
//            result = address;
//            return Response.ok(JsonMapper.getJsonFromObject(result)).build();
        } catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }

    }
}
