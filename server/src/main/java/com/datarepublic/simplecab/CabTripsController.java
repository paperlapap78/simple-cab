package com.datarepublic.simplecab;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CabTripsController {

  @RequestMapping(value = "/trips", method = RequestMethod.GET)
  @ResponseBody
  public String simpleCabInfo(@PathVariable String medallion) {
    return "hello";
  }
}