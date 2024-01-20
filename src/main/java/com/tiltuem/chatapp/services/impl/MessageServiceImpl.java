package com.tiltuem.chatapp.services.impl;

import com.tiltuem.chatapp.models.Message;
import com.tiltuem.chatapp.models.enums.MessageStatus;
import com.tiltuem.chatapp.repositories.MessageRepository;
import com.tiltuem.chatapp.services.ChatService;
import com.tiltuem.chatapp.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository repository;
    private final ChatService chatService;

    @Override
    public Message save(Message message) {
        message.setStatus(MessageStatus.RECEIVED);
        repository.save(message);

        return message;
    }

    @Override
    public long countNewMessages(Long senderId, Long recipientId) {
        return repository.countBySenderIdAndRecipientIdAndStatus(senderId, recipientId, MessageStatus.RECEIVED);
    }

    @Override
    public List<Message> findChatMessages(Long senderId, Long recipientId) {
        Optional<String> chatId = chatService.getChatId(senderId, recipientId, false);


        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
    }

    @Override
    public Message findById(Long id) {
        return repository.findById(id).map(chatMessage -> {
            chatMessage.setStatus(MessageStatus.DELIVERED);
            return repository.save(chatMessage);
        }).orElseThrow(RuntimeException::new);
    }
}
