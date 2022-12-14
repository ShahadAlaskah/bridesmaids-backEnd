package com.example.bridesmaids.repository;
import com.example.bridesmaids.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);
    User findUserByUsername(String username);
    @Query("select u from User u where u.isApproved=false")
    List<User> findAllByIsApproved();
    User findUserByEmail(String email);

}
