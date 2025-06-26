package com.eazybytes.config;

import com.eazybytes.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
  Spring @Configuration annotation is part of the spring core framework.
  Spring Configuration annotation indicates that the class has @Bean definition methods.
  So Spring container can process the class and generate Spring Beans to be used in the application.
*/
@Configuration
public class ProjectConfig {
  @Bean(name = "audiVehicle")
  Vehicle vehicle1(){ // this is a java method
    Vehicle vehicle = new Vehicle();
    vehicle.setName("Audi");
    return vehicle;
  }

  @Bean(value = "hondaVehicle")
  Vehicle vehicle2(){ // this is a java method
    Vehicle vehicle = new Vehicle();
    vehicle.setName("Honda");
    return vehicle;
  }

  @Bean("ferariVehicle")
  Vehicle vehicle3(){ // this is a java method
    Vehicle vehicle = new Vehicle();
    vehicle.setName("Ferari");
    return vehicle;
  }
}
