package com.crio.coderhack.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crio.coderhack.entity.User;

public interface UserRepository extends MongoRepository<User,String> {
    List<User> findAllByOrderByScoreDesc();
}