package com.webdemo.reppository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webdemo.model.User;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {
}

