package com.tiltuem.chatapp.services.impl;

import com.tiltuem.chatapp.models.Chat;
import com.tiltuem.chatapp.repositories.ChatRepository;
import com.tiltuem.chatapp.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;

    @Override
    public Optional<String> getChatId(
            Long senderId, Long recipientId, boolean createIfNotExist) {

        return chatRepository
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map(Chat::getChatId)
                .or(() -> {
                    if (!createIfNotExist) {
                        return Optional.empty();
                    }

                    String chatId =
                            String.format("%d_%d", senderId, recipientId);

                    Chat senderRecipient = Chat
                            .builder()
                            .chatId(chatId)
                            .senderId(senderId)
                            .recipientId(recipientId)
                            .build();

                    Chat recipientSender = Chat
                            .builder()
                            .chatId(chatId)
                            .senderId(recipientId)
                            .recipientId(senderId)
                            .build();

                    chatRepository.save(senderRecipient);
                    chatRepository.save(recipientSender);

                    return Optional.of(chatId);
                });
    }
}
