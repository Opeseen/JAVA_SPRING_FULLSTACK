package com.eazybytes.beans;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Vehicle {
  private String name;

//  @Autowired // field injection
  private final Engine engine;

  @Autowired
  public Vehicle(Engine engine) {
    this.engine = engine;
    System.out.println("Vehicle bean created by spring");
  }

//  public Vehicle() {
//  }

  public String getName() {
    return name;
  }

  public Engine getEngine() {
    return engine;
  }
/*
  @Autowired // setter injection
  public void setEngine(Engine engine) {
    this.engine = engine;
  }
*/
  public void setName(String name) {
    this.name = name;
  }

  public void sayHello(){
    System.out.println("Printing Hello from Component Vehicle Bean");
  }

  @PostConstruct
  public void initialize(){
    this.name = "KIA";
  }

  @Override
  public String toString() {
    return "Vehicle{" +
        "name='" + name + '\'' +
        '}';
  }
}
