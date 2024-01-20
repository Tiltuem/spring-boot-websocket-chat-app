package com.tiltuem.chatapp.repositories;

import com.tiltuem.chatapp.models.Message;
import com.tiltuem.chatapp.models.enums.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    long countBySenderIdAndRecipientIdAndStatus(
            Long senderId, Long recipientId, MessageStatus status);

    List<Message> findByChatId(String chatId);
}
