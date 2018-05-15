package com.datarepublic.simplecab.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TripCount {

  //medallion, hack_license, vendor_id, rate_code, store_and_fwd_flag,
  //pickup_datetime, dropoff_datetime, passenger_count,
  //trip_time_in_secs, trip_distance
  //todo: add fields needed?

  private  String medallion;
  private Integer count;

  public TripCount() {
    // Jackson deserialization
  }

  public TripCount(String medallion, int count) {
    this.medallion = medallion;
    this.count = count;
  }

  @JsonProperty
  public String getMedallion() {
    return medallion;
  }

  @JsonProperty
  public Integer getCount() {
    return count;
  }
}
