package com.datarepublic.simplecab.resources;

import com.codahale.metrics.annotation.Timed;
import com.datarepublic.simplecab.core.SimpleCabService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/trips")
@Produces(MediaType.APPLICATION_JSON)
public class SimpleCabResource {

  private final SimpleCabService service;

  public SimpleCabResource(SimpleCabService service) {
    this.service = service;
  }

  @GET
  @Timed
  public String getTripCounts(@QueryParam("medallion") List<String> medallions, @QueryParam("pickupDate") String pickupDate, @QueryParam("ignoreCache") String ignoreCache) {

    service.getTripCounts(medallions, pickupDate);

    return "whatever" + medallions + pickupDate + ignoreCache;
  }

  @Path("/cache")
  @DELETE
  public void deleteCache() {
    System.out.println("#### Dennis");
  }
}
