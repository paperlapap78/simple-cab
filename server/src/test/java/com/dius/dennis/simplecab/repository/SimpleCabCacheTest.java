package com.dius.dennis.simplecab.repository;

import com.dius.dennis.simplecab.api.TripCount;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(JUnitPlatform.class)
class SimpleCabCacheTest {

  private final SimpleCabRepository mockStore = mock(SimpleCabRepository.class);
  private final SimpleCabCache cache = new SimpleCabCache(mockStore);
  final String medallion = "123";
  final List<String> medallions = Lists.newArrayList(medallion);
  final DateTime pickupDate = new DateTime("2013-12-01");

  @AfterEach
  void clearCache() {
    cache.purgeCache();
  }

  @DisplayName("Calls DB on first call")
  @Test
  void shouldCallDB() throws Exception {
    List<TripCount> cabTrips = cache.getTripCounts(medallions, pickupDate);

    assertThat(cabTrips, hasSize(1));
    verify(mockStore, times(1)).getCountByMedallionAndPickupDatetime(medallion, pickupDate);
  }

  @DisplayName("Does not call DB on consecutive call")
  @Test
  void shouldCallCache() throws Exception {
    cache.getTripCounts(medallions, pickupDate);
    List<TripCount> cabTrips = cache.getTripCounts(medallions, pickupDate);

    assertThat(cabTrips, hasSize(1));
    verify(mockStore, times(1)).getCountByMedallionAndPickupDatetime(medallion, pickupDate);
  }
}