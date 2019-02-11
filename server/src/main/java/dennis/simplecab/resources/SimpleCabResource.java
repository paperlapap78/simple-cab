package dennis.simplecab.resources;

import com.codahale.metrics.annotation.Timed;
import dennis.simplecab.api.TripCount;
import dennis.simplecab.core.SimpleCabService;
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
    return service.getTripCounts(medallions, new DateTime(pickupDate), Boolean.valueOf(ignoreCache));
  }

  @Path("/cache")
  @DELETE
  public void deleteCache() {
    service.deleteCache();
  }
}
