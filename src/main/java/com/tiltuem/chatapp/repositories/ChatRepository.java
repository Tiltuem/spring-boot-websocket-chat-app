package com.tiltuem.chatapp.repositories;

import com.tiltuem.chatapp.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Optional<Chat> findBySenderIdAndRecipientId(Long senderId, Long recipientId);

}
