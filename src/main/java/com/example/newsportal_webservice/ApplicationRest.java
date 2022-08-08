package com.example.newsportal_webservice;



import com.example.newsportal_webservice.resource.ApplicationResource;
import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class ApplicationRest extends Application {
//public class ApplicationRest {


    public ApplicationRest() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:17170");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("com.example.newsportal_webservice.resource");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> appResource = new HashSet<>();
        appResource.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        appResource.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        appResource.add(ApplicationResource.class);
        return appResource;
    }
}
