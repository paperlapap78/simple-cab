package com.dius.dennis.simplecab;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class SimpleCabConfiguration extends Configuration {

  @NotEmpty
  private String check;

  @Valid
  @NotNull
  private DataSourceFactory database = new DataSourceFactory();

  @JsonProperty
  public String getCheck() {
    return check;
  }

  @JsonProperty
  public void setCheck(String check) {
    this.check = check;
  }


  @JsonProperty("database")
  public void setDatabase(DataSourceFactory factory) {
    this.database = factory;
  }

  @JsonProperty("database")
  public DataSourceFactory getDataSourceFactory() {
    return database;
  }
}
