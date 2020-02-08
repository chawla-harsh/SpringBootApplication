package com.rest.webservices.restwebservices.User;

import com.rest.webservices.restwebservices.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;
import javax.validation.Valid;

/**
 * // TODO Comment
 */
@RestController
public class UserJPAResource {

  @Autowired
  private UserDaoService service;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PostRepository postRepository;

  @GetMapping(path="/jpa/users")
  public List<User> retrieveAllUsers()
  {
    return userRepository.findAll();
  }

  @GetMapping(path="/jpa/user/{id}")
  public User getUser(@PathVariable("id") int id)
  {
    Optional<User> user=userRepository.findById(id);
    if(!user.isPresent())
      throw new UserNotFoundException("Id-" + id+ " Not Found");
    
    return user.get();
  }

  @PostMapping(path="/jpa/users")
  public ResponseEntity<Object> addUser(@Valid @RequestBody User user)
  {
    User saveduser = userRepository.save(user);

    URI location= ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(saveduser.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @DeleteMapping(path="/jpa/delete/{id}")
  public void deleteUser(@PathVariable("id") int id) {
    userRepository.deleteById(id);
  }


  @GetMapping(path="/jpa/user/{id}/posts")
  public List<Post> getUserPosts(@PathVariable("id") int id)
  {
    Optional<User> user=userRepository.findById(id);
    if(!user.isPresent())
      throw new UserNotFoundException("Id-" + id+ " Not Found");

    return user.get().getPosts();
  }

  @PostMapping(path="/jpa/user/{id}/posts")
  public ResponseEntity<Object> createPost(@PathVariable("id") int id,@RequestBody Post post)
  {
    Optional<User> userOptional=userRepository.findById(id);
    if(!userOptional.isPresent())
      throw new UserNotFoundException("Id-" + id+ " Not Found");

     User user=userOptional.get();
     post.setUser(user);

     postRepository.save(post);
     
    URI location= ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(post.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

}
