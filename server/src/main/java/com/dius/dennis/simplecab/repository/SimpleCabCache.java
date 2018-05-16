package com.dius.dennis.simplecab.repository;

import com.dius.dennis.simplecab.api.TripCount;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SimpleCabCache {
  private LoadingCache<CacheKey, TripCount> tripCountCache;

  public SimpleCabCache(SimpleCabRepository store) {
    CacheLoader<CacheKey, TripCount> loader = new CacheLoader<CacheKey, TripCount>() {
      @Override
      public TripCount load(CacheKey key) {
        Long count = store.getCountByMedallionAndPickupDatetime(key.getMedallion(), key.getPickupDate());
        return new TripCount(key.getMedallion(), count);
      }
    };
    tripCountCache = CacheBuilder.newBuilder()
        .maximumSize(500)
        .expireAfterAccess(2, TimeUnit.DAYS)
        .build(loader);
  }

  public List<TripCount> getTripCounts(List<String> medallions, DateTime pickupDate) throws ExecutionException {
    Set<CacheKey> cacheKeys = medallions.stream().map(medallion -> new CacheKey(medallion, pickupDate)).collect(Collectors.toSet());
    return Lists.newArrayList(tripCountCache.getAll(cacheKeys).values());
  }

  public void purgeCache() {
    this.tripCountCache.invalidateAll();
  }

  private class CacheKey {
    private final String medallion;
    private final DateTime pickupDate;

    CacheKey(String medallion, DateTime pickupDate) {
      this.medallion = medallion;
      this.pickupDate = pickupDate;
    }

    String getMedallion() {
      return medallion;
    }

    DateTime getPickupDate() {
      return pickupDate;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CacheKey cacheKey = (CacheKey) o;
      return Objects.equals(medallion, cacheKey.medallion) &&
          Objects.equals(pickupDate, cacheKey.pickupDate);
    }

    @Override
    public int hashCode() {
      return Objects.hash(medallion, pickupDate);
    }
  }
}
