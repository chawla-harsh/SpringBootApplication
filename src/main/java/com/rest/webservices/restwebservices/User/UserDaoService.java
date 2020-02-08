package com.rest.webservices.restwebservices.User;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.validation.constraints.Null;

/**
 * // TODO Comment
 */
@Component
public class UserDaoService {

  private static List<User> Users=new ArrayList<>();

  static int usersCount=2;

  static
  {
    Users.add(new User(1,"adam",new Date()));
    Users.add(new User(2,"Adam",new Date())) ;
  }


  public List<User> findAll() {
    return Users;
  }

  public User saveUser(User user){
    if(user.getId()== null)
      user.setId(++usersCount);
    
    Users.add(user);
    return user ;
  }

  public User findOne(int Id) {
    
    for (User user : Users) {
      if (user.getId() == Id) {
          return user;
      }
    }
    return null;
  }

  public User deleteById(int Id) {
    Iterator<User> it=Users.iterator();
    while(it.hasNext()){
      User user=it.next();
      if (user.getId() == Id) {
        it.remove();
        return user;
      }
    }
    return null;
  }
    
}
