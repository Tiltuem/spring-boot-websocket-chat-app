package com.tiltuem.chatapp.repositories;

import com.tiltuem.chatapp.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
