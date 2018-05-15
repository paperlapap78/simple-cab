package com.datarepublic.simplecab.core;

import com.datarepublic.simplecab.api.TripCount;
import com.datarepublic.simplecab.repository.SimpleCabRepository;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

public class SimpleCabService {

  private final SimpleCabRepository store;

  public SimpleCabService(SimpleCabRepository store) {
    //todo add cache
    this.store = store;
  }
  public List<TripCount> getTripCounts(List<String> medallions, Date pickupDate) {
    List<TripCount> tripCounts = Lists.newArrayList();

    medallions.forEach(medallion -> {
      //todo check cache first
      Integer count = store.getCountByMedallionAndPickupDatetime(medallion, pickupDate);
      tripCounts.add(new TripCount(medallion, count));
    });

    return tripCounts;
  }
}
