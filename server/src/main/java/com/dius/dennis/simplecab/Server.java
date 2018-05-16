package com.dius.dennis.simplecab;

import com.dius.dennis.simplecab.core.SimpleCabService;
import com.dius.dennis.simplecab.health.SimpleCabHealthCheck;
import com.dius.dennis.simplecab.repository.SimpleCabCache;
import com.dius.dennis.simplecab.repository.SimpleCabRepository;
import com.dius.dennis.simplecab.resources.SimpleCabResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.jodatime2.JodaTimePlugin;

public class Server extends Application<SimpleCabConfiguration> {

  public static void main(String[] args) throws Exception {
    new Server().run(args);
  }

  @Override
  public String getName() {
    return "simple-cab-server";
  }

  @Override
  public void run(SimpleCabConfiguration configuration, Environment environment) {
    final JdbiFactory factory = new JdbiFactory();
    final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
    jdbi.installPlugin(new JodaTimePlugin());
    final SimpleCabRepository store = new SimpleCabRepository(jdbi);
    final SimpleCabResource resource = new SimpleCabResource(new SimpleCabService(new SimpleCabCache(store), store));
    SimpleCabHealthCheck healthCheck = new SimpleCabHealthCheck(configuration.getTemplate());
    environment.healthChecks().register("client", healthCheck);
    environment.jersey().register(resource);
  }
}
