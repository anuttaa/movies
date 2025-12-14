package com.example.movies.services;

import com.example.movies.dtos.MessageDto;
import com.example.movies.entities.Chat;
import com.example.movies.entities.Message;
import com.example.movies.repositories.ChatRepository;
import com.example.movies.repositories.MessageRepository;
import com.example.movies.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    public MessageDto saveMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setSender(userRepository.findByLogin(messageDto.senderLogin()).orElse(null));
        message.setMessage(messageDto.message());
        message.setDate(LocalDateTime.now());
        message.setReceiverChat(chatRepository.findByChatName(messageDto.receiverChat()).orElse(null));
        messageRepository.save(message);

        messageDto = new MessageDto(messageDto.senderLogin(), messageDto.message(), message.getDate(), String.valueOf(message.getReceiverChat().getChatName()));

        return messageDto;
    }

    public List<MessageDto> getMessageByReceiverChatName(String receiverChatName) {
        try {
            receiverChatName = receiverChatName.replace("\"", "");
            Chat byChatName = chatRepository.findByChatName(receiverChatName).orElse(null);
            List<Message> messagesReceivedByChatName = messageRepository.findAllByReceiverChatId(byChatName.getId());
            return messagesReceivedByChatName.stream().map(x -> new MessageDto(x.getSender().getLogin(), x.getMessage(), x.getDate(), x.getReceiverChat().getChatName())).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
