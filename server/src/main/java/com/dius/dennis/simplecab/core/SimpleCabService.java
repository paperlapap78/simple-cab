package com.dius.dennis.simplecab.core;

import com.dius.dennis.simplecab.api.TripCount;
import com.dius.dennis.simplecab.repository.SimpleCabRepository;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;

import java.util.List;

public class SimpleCabService {

  private final SimpleCabRepository store;

  public SimpleCabService(SimpleCabRepository store) {
    //todo add cache
    this.store = store;
  }
  public List<TripCount> getTripCounts(List<String> medallions, DateTime pickupDate) {
    List<TripCount> tripCounts = Lists.newArrayList();

    medallions.forEach(medallion -> {
      //todo check cache first
      Long count = store.getCountByMedallionAndPickupDatetime(medallion, pickupDate);
      tripCounts.add(new TripCount(medallion, count));
    });

    return tripCounts;
  }
}
