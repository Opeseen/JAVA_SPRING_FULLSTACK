package com.eazybytes.beans;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Engine {

  private String name;

  public Engine() {
    System.out.println("Engine bean created by spring");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @PostConstruct // spring framework invoke the method once the bean is created to assign the name to the Engine bean
  public void initialize(){
    this.name = "V8";
  }

  @Override
  public String toString() {
    return "Engine{" +
      "name='" + name + '\'' +
      '}';
  }
}
