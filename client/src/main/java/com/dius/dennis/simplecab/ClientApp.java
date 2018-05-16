package com.dius.dennis.simplecab;


import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class ClientApp {

  public static void main(String[] args){
    //TODO you would need to implement a basic CLI here
  }

  public void run() {
    final OkHttpClient client = new OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build();

    SimpleCabService service = new SimpleCabService(client, "localhost", 8080);
  }
}
