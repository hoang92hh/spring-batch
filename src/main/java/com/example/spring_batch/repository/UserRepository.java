package com.example.spring_batch.repository;

import com.example.spring_batch.model.UserBatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserBatch, Integer> {
}