package com.dius.dennis.simplecab.core;

import com.dius.dennis.simplecab.api.TripCount;
import com.dius.dennis.simplecab.repository.SimpleCabCache;
import com.dius.dennis.simplecab.repository.SimpleCabRepository;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class SimpleCabService {

  private static final Logger LOG = LoggerFactory.getLogger(SimpleCabService.class);

  private final SimpleCabCache cache;
  private final SimpleCabRepository store;

  public SimpleCabService(SimpleCabCache cache, SimpleCabRepository store) {
    this.cache = cache;
    this.store = store;
  }
  public List<TripCount> getTripCounts(List<String> medallions, DateTime pickupDate, Boolean ignoreCache) {
    if(ignoreCache) {
      return getTripCountsFromStore(medallions, pickupDate);
    } else {
      try {
        return cache.getTripCounts(medallions, pickupDate);
      } catch (ExecutionException e) {
        LOG.error("could not read from cache", e);
        return getTripCountsFromStore(medallions, pickupDate);
      }
    }
  }

  private List<TripCount> getTripCountsFromStore(List<String> medallions, DateTime pickupDate) {
    return medallions.stream().map(medallion -> {
      Long count = store.getCountByMedallionAndPickupDatetime(medallion, pickupDate);
      return new TripCount(medallion, count);
    }).collect(Collectors.toList());
  }

  public void deleteCache() {
    cache.purgeCache();
  }
}
