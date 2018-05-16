package com.dius.dennis.simplecab.repository;

import org.jdbi.v3.core.Jdbi;
import org.joda.time.DateTime;

import java.util.Map;

public class SimpleCabRepository {

  private final Jdbi jdbi;

  public SimpleCabRepository(Jdbi jdbi) {
    this.jdbi = jdbi;
  }

  public Long getCountByMedallionAndPickupDatetime(String medallionId, DateTime pickupDate) {
    Map<String, Object> result = jdbi.withHandle(handle ->
        handle.createQuery("SELECT COUNT(*)" +
            "FROM cab_trip_data " +
            "WHERE medallion = :medallion " +
            "AND pickup_datetime BETWEEN :startOfDay AND :endOfDay")
            .bind("medallion", medallionId)
            .bind("startOfDay", pickupDate.withTime(0, 0, 0, 0))
            .bind("endOfDay", pickupDate.withTime(23, 59, 59, 999))
            .mapToMap()
            .findOnly()
    );

    return (Long) result.get("count(*)");
  }
}
