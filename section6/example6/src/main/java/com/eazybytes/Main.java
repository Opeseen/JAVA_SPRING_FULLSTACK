package com.eazybytes;

import com.eazybytes.beans.Engine;
import com.eazybytes.beans.Vehicle;
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
    Engine engine = context.getBean(Engine.class);
    Vehicle vehicle = context.getBean(Vehicle.class);

    System.out.println("Engine name from spring context is: " + engine.getName());
    System.out.println("Vehicle name from spring context is: " + vehicle.getName());
    System.out.println("Engine that vehicle own is: " + vehicle.getEngine());
  }
}