package dennis.simplecab.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TripCount {

  private  String medallion;
  private Long count;

  public TripCount() {
    // Jackson deserialization
  }

  public TripCount(String medallion, Long count) {
    this.medallion = medallion;
    this.count = count;
  }

  @JsonProperty
  public String getMedallion() {
    return medallion;
  }

  @JsonProperty
  public Long getCount() {
    return count;
  }
}
