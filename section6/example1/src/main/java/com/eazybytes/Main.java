package com.eazybytes;

import com.eazybytes.beans.Vehicle;
import com.eazybytes.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
  public static void main(String[] args) {
    Vehicle veh = new Vehicle();
    veh.setName("Honda City");
    System.out.println("Vehicle name from non spring context is: " + veh.getName());

    var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
    /*
    We don’t need to do any explicit casting while fetching a bean from context.
    Spring is smart enough to look for a bean of the type you requested in its context.
    If such a bean doesn’t exist,Spring will throw an exception.
    * */
    Vehicle vehicle = context.getBean(Vehicle.class);
    System.out.println("Vehicle name from spring context is: " + vehicle.getName());

    String hello = context.getBean(String.class);
    System.out.println("String value from spring context is: " + hello);
  }
}