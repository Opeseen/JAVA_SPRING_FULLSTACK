package com.eazybytes.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Engine {

  public final Vehicle vehicle;

  @Autowired
  public Engine(Vehicle vehicle) { // circular dependency
    this.vehicle = vehicle;
  }
}
