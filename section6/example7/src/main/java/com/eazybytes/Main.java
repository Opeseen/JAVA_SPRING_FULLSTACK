package com.eazybytes;

import com.eazybytes.beans.Coffee;
import com.eazybytes.beans.CoffeeShop;
import com.eazybytes.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
  public static void main(String[] args) {

    var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
    /*
    We don’t need to do any explicit casting while fetching a bean from context.
    Spring is smart enough to look for a bean of the type you requested in its context.
    If such a bean doesn’t exist,Spring will throw an exception.
    * */
    CoffeeShop coffeeShop = context.getBean(CoffeeShop.class);
    Coffee coffee = context.getBean(Coffee.class);

    System.out.println("Coffee name from spring context is: " + coffeeShop.getCoffee().makeCoffee());
  }
}