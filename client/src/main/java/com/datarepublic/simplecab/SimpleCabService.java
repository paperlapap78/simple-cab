package com.datarepublic.simplecab;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class SimpleCabService {

  public static final String HTTP_SCHEME = "http";
  public static final String TRIPS_PATH = "trips";
  private final OkHttpClient client;
  private final String host;
  private final int port;

  SimpleCabService(OkHttpClient client, String host, int port) {
    this.client = client;
    this.host = host;
    this.port = port;
  }

  public void deleteCache() throws IOException {
    HttpUrl url = new HttpUrl.Builder()
        .scheme(HTTP_SCHEME)
        .host(host)
        .port(port)
        .addPathSegment(TRIPS_PATH)
        .addPathSegment("cache")
        .build();
    Request request = new Request.Builder()
        .url(url)
        .delete()
        .build();
    client.newCall(request).execute();
  }

  public String getMedallionsSummary(List<String> medallions, Date pickupDate) throws IOException {
    return this.getMedallionsSummary(medallions, pickupDate, false);
  }

  public String getMedallionsSummary(List<String> medallions, Date pickupDate, boolean ignoreCache) throws IOException {
    HttpUrl url = getHttpUrl(medallions, pickupDate, ignoreCache);
    Request request = new Request.Builder().url(url).build();
    Response response = client.newCall(request).execute();

    return response.body().string();
  }

  private HttpUrl getHttpUrl(List<String> medallions, Date pickupDate, boolean ignoreCache) {
    HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
        .scheme("http")
        .host(host)
        .port(port)
        .addPathSegment("trips");

    medallions.forEach(medallion -> urlBuilder.addQueryParameter("medallion", medallion));
    urlBuilder.addQueryParameter("pickupDate", formatDate(pickupDate))
    .addQueryParameter("ignoreCache", String.valueOf(ignoreCache));

    return urlBuilder.build();
  }

  private String formatDate(Date date) {
    DateTime aDate = new DateTime(date);
    DateTimeFormatter fmt = ISODateTimeFormat.date();
    return fmt.print(aDate);
  }
}
