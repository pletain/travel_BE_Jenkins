package com.samsam.travel.travelcommerce.domain.user.repository;

import com.samsam.travel.travelcommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
  }