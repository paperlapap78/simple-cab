package com.datarepublic.simplecab.resources;


import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(JUnitPlatform.class)
public class SimpleCabResourceTest {

  private final SimpleCabResource resource = new SimpleCabResource("template");

  @BeforeEach
  void init() {

  }

  @DisplayName("test simple cab resource accepts a single ")
  @Test
  void testRequestCount() {
    List<String> medallions = Lists.newArrayList("medallion12");
    DateTime aDate  = new DateTime("2013-12-01");
    String response = resource.getTripCounts(medallions, aDate.toString(), "true");
    System.out.println("response " + response);
  }
}