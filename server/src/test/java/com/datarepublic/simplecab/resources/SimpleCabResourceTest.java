package com.datarepublic.simplecab.resources;


import com.datarepublic.simplecab.api.TripCount;
import com.datarepublic.simplecab.core.SimpleCabService;
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
    DateTime aDate  = new DateTime("2013-12-01");

    when(service.getTripCounts(medallions, aDate.toDate()))
        .thenReturn(Lists.newArrayList(new TripCount("medallion12", 5)));

    List<TripCount> tripCounts = resource.getTripCounts(medallions, aDate.toString(), "true");
    assertThat(tripCounts, hasSize(1));
  }
}