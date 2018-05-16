package com.dius.dennis.simplecab.core;

import com.dius.dennis.simplecab.api.TripCount;
import com.dius.dennis.simplecab.repository.SimpleCabCache;
import com.dius.dennis.simplecab.repository.SimpleCabRepository;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

@RunWith(JUnitPlatform.class)
class SimpleCabServiceTest {

  private final SimpleCabRepository mockRepo = mock(SimpleCabRepository.class);
  private final SimpleCabCache mockCache = mock(SimpleCabCache.class);
  private SimpleCabService simpleCabService;
  private final DateTime pickupDate = new DateTime("2013-12-01");
  private final String medallion = "123";
  private final List<String> medallions = Lists.newArrayList(medallion);

  @BeforeEach
  void setUp() throws Exception {
    simpleCabService = new SimpleCabService(mockCache, mockRepo);
    when(mockCache.getTripCounts(Lists.newArrayList("123"), pickupDate)).thenReturn(Lists.newArrayList(new TripCount()));
    when(mockRepo.getCountByMedallionAndPickupDatetime("123", pickupDate)).thenReturn(5L);
  }

  @DisplayName("Get trip count from DB when cache is ignored")
  @Test
  void shouldIgnoreCacheAndRequestDB() throws Exception {
    List<TripCount> cabTips = simpleCabService.getTripCounts(medallions, pickupDate, Boolean.TRUE);

    assertThat(cabTips, hasSize(1));
    verify(mockRepo, times(1)).getCountByMedallionAndPickupDatetime(medallion, pickupDate);
    verify(mockCache, never()).getTripCounts(Mockito.anyList(), Mockito.any(DateTime.class));
  }

  @DisplayName("Try to get trip count from cache when cache not ignored")
  @Test
  void shouldTryCacheWhenNotIgnored() throws Exception {
    List<TripCount> cabTrips = simpleCabService.getTripCounts(medallions, pickupDate, Boolean.FALSE);

    assertThat(cabTrips, hasSize(1));
    verify(mockRepo, never()).getCountByMedallionAndPickupDatetime(Mockito.anyString(), Mockito.any(DateTime.class));
    verify(mockCache, times(1)).getTripCounts(medallions, pickupDate);
  }
}