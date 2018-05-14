package com.datarepublic.simplecab;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/trips")
@Produces(MediaType.APPLICATION_JSON)
public class SimpleCabResource {

  private final String template;

  public SimpleCabResource(String template) {
    this.template = template;
  }

  @GET
  @Timed //todo: do i need this?
  public CabTrip getCabTrip(@QueryParam("medallion") Optional<String> medallion) {
    final String something = String.format(template, "world");
    return new CabTrip(something);
  }
}
