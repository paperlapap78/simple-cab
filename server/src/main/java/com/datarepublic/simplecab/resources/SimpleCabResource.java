package com.datarepublic.simplecab.resources;

import com.codahale.metrics.annotation.Timed;
import com.datarepublic.simplecab.api.TripCount;
import com.datarepublic.simplecab.core.SimpleCabService;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;

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
  public List<TripCount> getTripCounts(@QueryParam("medallion") List<String> medallions, @QueryParam("pickupDate") String pickupDate, @QueryParam("ignoreCache") String ignoreCache) {
    DateTime dtPickupDate = new DateTime(pickupDate);
    return service.getTripCounts(medallions, dtPickupDate.toDate());
  }

  @Path("/cache")
  @DELETE
  public void deleteCache() {
    System.out.println("#### Dennis");
  }
}
