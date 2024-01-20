package com.tiltuem.chatapp.services;

import com.tiltuem.chatapp.models.Message;

import java.util.List;

public interface MessageService {
    Message save(Message message);

    long countNewMessages(Long senderId, Long recipientId);

    List<Message> findChatMessages(Long senderId, Long recipientId);

    Message findById(Long id);
}
