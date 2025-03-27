package com.example.repository;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
    Optional<List<Message>> findMessagesByPostedBy(Integer id);
}
