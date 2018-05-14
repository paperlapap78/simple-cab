package com.datarepublic.simplecab.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CabTrip {

  //medallion, hack_license, vendor_id, rate_code, store_and_fwd_flag,
  //pickup_datetime, dropoff_datetime, passenger_count,
  //trip_time_in_secs, trip_distance
  //todo: add fields needed

  private String medallion;

  public CabTrip() {

  }

  public CabTrip(String medallion) {
    this.medallion = medallion;
  }

  @JsonProperty
  public String getMedallion() {
    return medallion;
  }
}
