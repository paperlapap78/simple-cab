package com.dius.dennis.simplecab;

import java.util.Date;

public interface SimpleCabServiceinterf {

//  void deleteCache();

  String getMedallionsSummary(Date pickupDate, String... medallions);

//  ??? getMedallionsSummary(String... medallions, Date pickupDate, boolean ignoreCache);

}
