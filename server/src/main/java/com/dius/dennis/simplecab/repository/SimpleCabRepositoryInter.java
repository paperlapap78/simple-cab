package com.dius.dennis.simplecab.repository;

import java.util.Date;

public interface SimpleCabRepositoryInter {

  Integer getCountByMedallionAndPickupDatetime(String medallionId, Date pickupDate);

}
