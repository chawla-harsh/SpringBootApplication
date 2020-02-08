package com.rest.webservices.restwebservices.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * // TODO Comment
 */
@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
}
