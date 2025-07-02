package com.eazybytes;

import com.eazybytes.beans.MyService;
import com.eazybytes.beans.UserSession;
import com.eazybytes.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
  public static void main(String[] args) {

    var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

    MyService service1 = context.getBean(MyService.class);
    MyService service2 = context.getBean(MyService.class);

    UserSession userSession1 = context.getBean(UserSession.class);
    UserSession userSession2 = context.getBean(UserSession.class);

    System.out.println(userSession1==userSession2);
    System.out.println(userSession1.getSessionID());
    System.out.println(userSession2.getSessionID());
  }
}