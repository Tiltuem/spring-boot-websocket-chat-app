package com.tiltuem.chatapp.services;

import java.util.Optional;

public interface ChatService {
    Optional<String> getChatId(
            Long senderId, Long recipientId, boolean createIfNotExist);
}
