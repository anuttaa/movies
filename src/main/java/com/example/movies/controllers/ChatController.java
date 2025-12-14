package com.example.movies.controllers;


import com.example.movies.dtos.ChatCreatorDto;
import com.example.movies.dtos.ChatDto;
import com.example.movies.dtos.MessageDto;
import com.example.movies.services.ChatService;
import com.example.movies.services.MessageService;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;
    private final ChatService chatService;

    @MessageMapping("/message")
    @SendTo("/chat/public")
    public MessageDto receivePrivateMessage(@Payload MessageDto messageDto) {
        return messageService.saveMessage(messageDto);
    }

    @MessageMapping("/user-chats")
    @SendTo("/topic/chats")
    public void getChats(Principal principal) {
        List<ChatDto> chats = chatService.getChatsByUserLogin(principal.getName());
        simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/queue/chats", chats);
    }

    @MessageMapping("chat-messages")
    @SendTo("/chat/messages")
    public void getMessagesCurrentChat(String receiverChat, Principal principal) {
        List<MessageDto> messagesChat = messageService.getMessageByReceiverChatName(receiverChat);
        simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/queue/messages", messagesChat);
    }

    @MessageMapping("/create-chat")
    public void createChatWithUsers(@Payload ChatCreatorDto chatCreatorDto) {
        chatService.createChatWithUsers(chatCreatorDto);
    }

    @MessageMapping("/add-to-chat")
    public void addUserToChat(@Payload ChatCreatorDto chatCreatorDto) {
        chatService.addUserToChat(chatCreatorDto);
    }
}
