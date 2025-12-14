package com.example.movies.services;

import com.example.movies.dtos.ChatCreatorDto;
import com.example.movies.dtos.ChatDto;
import com.example.movies.entities.Chat;
import com.example.movies.entities.User;
import com.example.movies.mappers.ChatMapper;
import com.example.movies.repositories.ChatRepository;
import com.example.movies.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final UserRepository userRepository;
    private final ChatMapper chatMapper;
    private final ChatRepository chatRepository;

    public List<ChatDto> getChatsByUserLogin(String login) {
        if (login != null) {
            return userRepository.getChatsByUserLogin(login).stream().map(chatMapper::toDto).toList();
        }
        return null;
    }

    @Transactional
    public void createChatWithUsers(ChatCreatorDto chatCreatorDto) {
        try {
            String chatName = chatCreatorDto.chatName();
            List<String> userLogins = chatCreatorDto.users();

            Chat chat = new Chat(chatName);

            List<User> userList = userLogins.stream()
                    .map(login -> userRepository.findByLogin(login)
                            .orElseThrow(() -> new RuntimeException("User not found: " + login)))
                    .toList();

            userList.forEach(user -> user.getChats().add(chat));

            chatRepository.save(chat);
        } catch (RuntimeException e) {
            System.err.println("Error creating chat: " + e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void addUserToChat(ChatCreatorDto chatCreatorDto) {
        try {
            String chatName = chatCreatorDto.chatName();
            List<String> userLogins = chatCreatorDto.users();

            Chat chat = chatRepository.findByChatName(chatName).orElseThrow(() -> new RuntimeException("Chat not found: " + chatName));

            List<User> userList = userLogins.stream()
                    .map(login -> userRepository.findByLogin(login)
                            .orElseThrow(() -> new IllegalCallerException("User not found: " + login)))
                    .toList();

            userList.forEach(user -> {
                if (!chat.getUsers().contains(user)) {
                    chat.getUsers().add(user);
                    userRepository.save(user);
                }
                if (!user.getChats().contains(chat)) user.getChats().add(chat);
            });

            chatRepository.save(chat);
        } catch (IllegalCallerException e) {
            System.err.println("Error creating chat: " + e.getMessage());
        }
    }
}
