package com.rest.webservices.restwebservices.Filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * // TODO Comment
 */
@RestController
public class FilteringController {

  @GetMapping("/filtering")
  public MappingJacksonValue retrieve()
  {
    SomeBean bean=new SomeBean("field1","field2","field3");

    SimpleBeanPropertyFilter filter  = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
    FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
    MappingJacksonValue mapping=new MappingJacksonValue(bean);
    
    mapping.setFilters(filters);

    return mapping;

  }
}
