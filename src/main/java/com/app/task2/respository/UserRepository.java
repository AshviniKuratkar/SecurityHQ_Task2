package com.app.task2.respository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.task2.model.Role;
import com.app.task2.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> 
{
 // Optional<User> findByUsername(String username);
  //Optional is a container object used to contain not-null objects. Optional object is used to represent null with absent value. 
  User findByUsername(String userName);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
  @Query(value="SELECT * FROM users INNER JOIN user_roles ur ON users.id = ur.user_id WHERE ur.role_id IN :roles", nativeQuery = true)
  List<User>findAllADMIN(@Param("roles") Set<Role> roleSet);
  
  
 
}