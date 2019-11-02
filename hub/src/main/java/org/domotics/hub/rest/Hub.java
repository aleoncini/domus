package org.domotics.hub.rest;

import org.domotics.hub.model.ControllerService;
import org.domotics.hub.model.HubResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("hub")
public class Hub {
    private static final Logger logger = Logger.getLogger("org.domotics");

    @GET
    @Produces("text/plain")
    public Response doGet() {
        return Response.ok("[HUB] REST service to invoke remote micro controller services.").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/{pin}")
    public Response status(@PathParam("id") String id, @PathParam("pin") int pin) {
        logger.info("[HUB] richiesto stato per controller " + id + ", Pin: " + pin);
        String message = "To Be Developed";
        return Response.status(200).entity(new HubResult().setId(id).setMessage(message).setResult(HubResult.SUCCESS).toString()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("accendi/{id}/{pin}")
    public Response switchOn(@PathParam("id") String id) {
        logger.info("[Hub] richiesta accensione: " + id);
        String message = "To Be Developed";
        return Response.status(200).entity(new HubResult().setId(id).setMessage(message).setResult(HubResult.SUCCESS).toString()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("spegni/{id}/{pin}")
    public Response switchOff(@PathParam("id") String id) {
        logger.info("[Hub] richiesto spegnimento: " + id);
        String message = "To Be Developed";
        return Response.status(200).entity(new HubResult().setId(id).setMessage(message).setResult(HubResult.SUCCESS).toString()).build();
    }

}
