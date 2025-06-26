package com.eazybytes.config;

import com.eazybytes.beans.Person;
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

  @Bean
  Vehicle vehicle(){ // this is a java method
    Vehicle vehicle = new Vehicle();
    vehicle.setName("Toyota");
    return vehicle;
  }

//  @Bean
//  Person person(){ // this is a java method
//    Person person = new Person();
//    person.setName("Lucy");
//    person.setVehicle(vehicle()); // Using method invocation to perform manual wiring of bean
//    return person;
//  }

    @Bean
    Person person(Vehicle vehicle){ // this is a java method
      Person person = new Person();
      person.setName("Lucy");
      person.setVehicle(vehicle); // Using method parameters to perform manual wiring
      return person;
    }

}
