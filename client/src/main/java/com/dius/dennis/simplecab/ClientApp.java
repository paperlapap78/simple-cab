package com.dius.dennis.simplecab;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.common.collect.Lists;
import okhttp3.OkHttpClient;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

public class ClientApp {


  private String host = "loclhost";
  private int port = 8080;


  @Parameter(names = {"-m", "--medallion"}, description = "Cab Medallion")
  private List<String> medallions = Lists.newArrayList();

  @Parameter(names = {"-d", "--pickupDate"}, description = "Trip pickup date format YYYY-MM-DD")
  private String pickupDate;

  @Parameter(names = {"-i", "--ignoreCache"}, description = "to ignore server cache")
  private boolean ignoreCache = false;

  @Parameter(names = {"-c", "--clearCache"}, description = "to clear cache")
  private boolean clearCache = false;

  @Parameter(names = "--help", help = true)
  private boolean help = false;

  private SimpleCabService service;

  ClientApp() {
    final OkHttpClient client = new OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build();
    service = new SimpleCabService(client, host, port);

  }

  public static void main(String... argv) throws IOException {
    ClientApp app = new ClientApp();
    JCommander commander = new JCommander.Builder()
        .addObject(app)
        .build();

    commander.parse(argv);

    if(argv.length == 0) {
      System.out.println("--help for help");
    }

    if(app.help) {
      commander.usage();
      exit(0);
    }

    if (app.clearCache) {
      app.service.deleteCache();
    }
    if (!app.medallions.isEmpty() && app.pickupDate != null) {
      DateTime dtPickupDate = new DateTime(app.pickupDate);
      if (app.ignoreCache) {
        app.service.getMedallionsSummary(app.medallions, dtPickupDate, true);
      } else {
        app.service.getMedallionsSummary(app.medallions, dtPickupDate);
      }
    }
  }
}
