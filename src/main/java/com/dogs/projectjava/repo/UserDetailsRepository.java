package com.dogs.projectjava.repo;

import com.dogs.projectjava.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer> {
    @Query("SELECT ud FROM UserDetails ud WHERE ud.username = :username")
    UserDetails getAllUserDetailsWithUser(@Param("username")String username);
}
