package com.example.geo.config;

import com.example.geo.rest.GeoLoc;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(GeoLoc.class);
        register(MultiPartFeature.class);
    }
}
