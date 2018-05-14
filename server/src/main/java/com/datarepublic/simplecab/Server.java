package com.datarepublic.simplecab;

import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Server extends Application<SimpleCabConfiguration> {

  public static void main(String[] args) throws Exception {
    new Server().run(args);
  }

  @Override
  public String getName() {
    return "simple-cab-server";
  }

  @Override
  public void initialize(Bootstrap<SimpleCabConfiguration> bootstrap) {

  }

  @Override
  public void run(SimpleCabConfiguration configuration, Environment environment) {
    final SimpleCabResource resource = new SimpleCabResource(configuration.getTemplate());
    SimpleCabHealthCheck healthCheck = new SimpleCabHealthCheck(configuration.getTemplate());
    environment.healthChecks().register("client", healthCheck);
    environment.jersey().register(resource);
  }
}
