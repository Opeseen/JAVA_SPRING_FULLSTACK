package com.eazybytes.config;

import com.eazybytes.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
  /*
  When you have multiple beans of the same kind inside the Spring context,
  you can make one of them primary by using @Primary annotation. Primary bean is
  the one which Spring will choose if it has multiple options and you don’t specify a name.
  In other words, it is the default bean that Spring Context will consider in case of
  confusion due to multiple beans present of same type.
  * */
  @Primary
  @Bean("ferariVehicle")
  Vehicle vehicle3(){ // this is a java method
    Vehicle vehicle = new Vehicle();
    vehicle.setName("Ferari");
    return vehicle;
  }
}
