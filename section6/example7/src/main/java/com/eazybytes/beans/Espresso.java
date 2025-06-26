package com.eazybytes.beans;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("espresso") // to be considered as a bean name by providing a value inside the @Component annotation
@Primary
public class Espresso implements Coffee {
  @Override
  public String makeCoffee() {
    return "Espresso Coffee";
  }
}
