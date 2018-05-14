package com.datarepublic.simplecab.resources;

import com.codahale.metrics.annotation.Timed;
import com.datarepublic.simplecab.api.CabTrip;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Path("/trips")
@Produces(MediaType.APPLICATION_JSON)
public class SimpleCabResource {

  private final String template;

  public SimpleCabResource(String template) {
    this.template = template;
  }

  @GET
  @Timed
  public String getTripCounts(@QueryParam("medallions")Optional<List<String>> medallions, @QueryParam("date") Optional<Date> pickupDate, @QueryParam("ignoreCache") Optional<Boolean> ignoreCache) {

    return "whatever";
  }
}
