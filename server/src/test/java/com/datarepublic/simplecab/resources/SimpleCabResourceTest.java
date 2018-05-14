package com.datarepublic.simplecab.resources;


import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Optional;

@RunWith(JUnitPlatform.class)
public class SimpleCabResourceTest {

  private final SimpleCabResource resource = new SimpleCabResource("template");

  @BeforeEach
  void init() {

  }

  @DisplayName("Test request count")
  @Test
  void testRequestCount() {
    List<String> medallions = Lists.newArrayList("medallion12");
    DateTime aDate  = new DateTime("2013-12-01");
    String response = resource.getTripCounts(Optional.of(medallions), Optional.of(aDate.toDate()), Optional.of(false));
    System.out.println("response " + response);
  }
}