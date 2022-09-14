package com.example.bridesmaids.repository;
import com.example.bridesmaids.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositry extends JpaRepository<User, Integer> {

}
