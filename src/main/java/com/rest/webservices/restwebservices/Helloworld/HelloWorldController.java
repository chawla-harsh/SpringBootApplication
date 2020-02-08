package com.rest.webservices.restwebservices.Helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * // TODO Comment
 */
@RestController
public class HelloWorldController {

  @Autowired
  private MessageSource messageSource ;
  
  @RequestMapping(method = RequestMethod.GET,path="/helloworld")
  public String helloWorld()
  {
    return "HelloWorld";
  }

  //hello-world-bean
  @RequestMapping(method = RequestMethod.GET,path="/helloworldbean")
  public HelloWorldBean helloWorldBean() {
    return new HelloWorldBean("HelloWorld")  ;
  }

  @GetMapping("/helloworld-Internationalized")
  public String helloWorldInternationalized(@RequestHeader(value="Accept-Language",required = false) Locale locale) {
    return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
  }
}
