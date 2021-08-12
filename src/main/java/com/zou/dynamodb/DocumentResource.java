package com.zou.dynamodb;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/documents")
public class DocumentResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @Inject
    DocumentSyncService service;

    @GET
    public List<Document> getAll() {
        return service.findAll();
    }

    @GET
    @Path("{id}")
    public Document getSingle(@PathParam("id") String id) {
        return service.get(id);
    }

    @POST
    public List<Document> add(Document document) {
        service.add(document);
        return getAll();
    }
}