package com.tiltuem.chatapp.repositories;

import com.tiltuem.chatapp.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
