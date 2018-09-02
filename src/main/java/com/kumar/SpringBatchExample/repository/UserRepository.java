package com.kumar.SpringBatchExample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.SpringBatchExample.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
