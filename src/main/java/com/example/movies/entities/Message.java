package com.example.movies.entities;

import com.example.movies.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@AttributeOverride(name = "id", column = @Column(name = "message_id"))
public class Message extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @OneToOne
    @JoinColumn(name = "receiver_chat_id")
    private Chat receiverChat;

    @NotNull
    private String message;

    @NotNull
    private LocalDateTime date;
}
