package com.dius.dennis.simplecab.core;

import com.dius.dennis.simplecab.api.TripCount;
import com.dius.dennis.simplecab.repository.SimpleCabRepository;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
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
class SimpleCabServiceTest {

  @DisplayName("test to ")
  @Test
  void testGet() throws Exception {
    SimpleCabRepository mockRepo = mock(SimpleCabRepository.class);
    SimpleCabService simpleCabService = new SimpleCabService(mockRepo);
    DateTime pickupDate = new DateTime("2013-12-01");
    when(mockRepo.getCountByMedallionAndPickupDatetime("123", pickupDate)).thenReturn(5L);

    List<String> medallions = Lists.newArrayList("123");
    List<TripCount> cabTips = simpleCabService.getTripCounts(medallions, pickupDate);
    assertThat(cabTips, hasSize(1));
  }




}