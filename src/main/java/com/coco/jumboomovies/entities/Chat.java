package com.coco.jumboomovies.entities;

import com.coco.jumboomovies.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Table(name = "chats")
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "chat_id"))
public class Chat extends BaseEntity {

    @Column(name = "chat_name")
    private String chatName;

    @ManyToMany
    @JoinTable(
            name = "chats_users",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    public Chat(String chatName) {
        this.chatName = chatName;
        this.users = new ArrayList<>();
    }
}
