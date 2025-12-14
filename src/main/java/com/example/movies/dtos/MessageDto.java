package com.example.movies.dtos;

import java.time.LocalDateTime;

public record MessageDto (
        String senderLogin,
        String message,
        LocalDateTime dateTime,
        String receiverChat
){
}
