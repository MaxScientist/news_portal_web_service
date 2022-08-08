package com.example.newsportal_webservice.resource;


import com.example.newsportal_webservice.entity.News;
import com.example.newsportal_webservice.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/news")
@Api(value = "/news-swagger")
public class ApplicationResource {

    @Inject
//    @EJB
    private NewsService newsService;


    @Context
    private UriInfo uriInfo;

    @GET
    @Path("hello")
//    @ApiOperation(pr)
//    @Produces("application/json")
    public Response helloWorld() {
        News news = new News();
        news.setId(1L);
        news.setTitle("Title");
        news.setShortDescription("short description");
        news.setContentOfNews("content of news");
        return Response.ok().entity(news).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @ApiOperation(consumes = MediaType.APPLICATION_JSON, value = "Update news article")
    public Response updateNews(@ApiParam(value = "news id", required = true) @PathParam("id") long id, News news) {
        newsService.update(id, news);
        return Response.ok().build();
    }

    @POST
    @Path("/create")
    @Consumes("application/json")
//    @ApiOperation(consumes = MediaType.APPLICATION_JSON, )
    public Response saveNews(News news) {
        newsService.save(news);
        return Response.created(uriInfo.getBaseUriBuilder().path(ApplicationResource.class).buildFromEncoded()
        ).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
//    @ApiOperation(consumes = MediaType.APPLICATION_JSON, value = "Update news article")
    public Response deleteNewsById(@PathParam("id") long id) {
        newsService.delete(id);
        return Response.created(uriInfo.getBaseUriBuilder().path(ApplicationResource.class).buildFromEncoded()
        ).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findById(@PathParam("id") long id) {
        return Response.ok().entity(newsService.findById(id)).build();
    }

    @GET
    @Path("/allNews")
    @Produces("application/json")
    public Response findAll() {
        return Response.ok().entity(newsService.findAll()).build();
    }

}
