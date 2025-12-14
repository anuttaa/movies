package com.example.movies.entities;

import javax.validation.constraints.Pattern;
import com.example.movies.model.Person;
import com.example.movies.model.Role;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.ToString;

import java.util.List;


@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@Data
@ToString(exclude = "favourites")
public class User extends Person {

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid login format")
    private String login;
    private String password;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_email_verified", nullable = false)
    private boolean isEmailVerified = false;

    @ManyToMany
    @JoinTable(
            name = "favourites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> favourites;

    @ManyToMany
    @JoinTable(
            name = "chats_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id")
    )
    private List<Chat> chats;
}
