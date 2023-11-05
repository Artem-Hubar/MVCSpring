package com.example.demo;

import com.example.demo.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepo")
public interface MessageRepo extends CrudRepository<Message, Integer> {
}
