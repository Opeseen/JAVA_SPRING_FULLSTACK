package com.eazybytes.eazystore.scopes;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

@Component
@ApplicationScope
@Getter
@Slf4j
public class ApplicationScopeBean {

  private int visitorCount;

  public void incrementVisitorCount(){
    visitorCount++;
  }

  public ApplicationScopeBean(){
    log.info("ApplicationScopeBean Initialized");
  }
}
