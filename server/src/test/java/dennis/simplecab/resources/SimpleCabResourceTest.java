package dennis.simplecab.resources;


import dennis.simplecab.api.TripCount;
import dennis.simplecab.core.SimpleCabService;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitPlatform.class)
class SimpleCabResourceTest {

  private final SimpleCabService service = mock(SimpleCabService.class);
  private final SimpleCabResource resource = new SimpleCabResource(service);

  @BeforeEach
  void init() {

  }

  @DisplayName("test simple cab resource accepts a single ")
  @Test
  void testRequestCount() {
    List<String> medallions = Lists.newArrayList("medallion12");
    DateTime pickupDate
        = new DateTime("2013-12-01");

    when(service.getTripCounts(medallions, pickupDate, Boolean.FALSE))
        .thenReturn(Lists.newArrayList(new TripCount("medallion12", 5L)));

    List<TripCount> tripCounts = resource.getTripCounts(medallions, pickupDate.toString(), "false");
    assertThat(tripCounts, hasSize(1));
  }
}