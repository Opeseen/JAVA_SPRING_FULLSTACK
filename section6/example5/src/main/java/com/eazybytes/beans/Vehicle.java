package com.eazybytes.beans;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

public class Vehicle {
  private String name;

  public Vehicle() {
    System.out.println("Vehicle bean created by spring");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void sayHello(){
    System.out.println("Printing Hello from Component Vehicle Bean");
  }

  @Override
  public String toString() {
    return "Vehicle{" +
        "name='" + name + '\'' +
        '}';
  }
}
