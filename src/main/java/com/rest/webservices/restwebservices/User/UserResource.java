package com.rest.webservices.restwebservices.User;

import com.rest.webservices.restwebservices.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

/**
 * // TODO Comment
 */
@RestController
public class UserResource {

  @Autowired
  private UserDaoService service;



  @GetMapping(path="/users")
  public List<User> retrieveAllUsers()
  {
    return service.findAll();
  }

  @GetMapping(path="/user/{id}")
  public User getUser(@PathVariable("id") int id)
  {
    User user=service.findOne(id);
    if(user==null)
      throw new UserNotFoundException("Id-" + id+ " Not Found");
    
    return user;
  }

  @PostMapping(path="/users")
  public ResponseEntity<Object> addUser(@Valid @RequestBody User user)
  {
    User saveduser = service.saveUser(user);

    URI location= ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(saveduser.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @DeleteMapping(path="/delete/{id}")
  public void deleteUser(@PathVariable("id") int id) {
    User user = service.deleteById(id);
    if (user == null) {
      throw new UserNotFoundException("Id-" + id + " Not found");
    }
  }
}
