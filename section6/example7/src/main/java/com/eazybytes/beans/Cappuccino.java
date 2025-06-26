package com.eazybytes.beans;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("cappuccino") // to be considered as a bean name by providing a value inside the @Component annotation

public class Cappuccino implements Coffee {
  @Override
  public String makeCoffee() {
    return "Cappuccino Coffee";
  }
}
