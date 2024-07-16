package com.example.iismicroservice.repository;

import com.example.iismicroservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
