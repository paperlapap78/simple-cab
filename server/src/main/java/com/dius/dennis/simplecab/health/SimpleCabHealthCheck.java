package com.dius.dennis.simplecab.health;

import com.codahale.metrics.health.HealthCheck;

public class SimpleCabHealthCheck extends HealthCheck {
  private final String template;

  public SimpleCabHealthCheck(String template) {
    this.template = template;
  }

  @Override
  protected Result check() throws Exception {
    //todo: more usefull healthcheck

    return Result.healthy();
  }
}
