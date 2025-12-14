package com.coco.jumboomovies.entities;

import com.coco.jumboomovies.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "confirmation_token")
public class ConfirmationToken extends BaseEntity {

    private String token;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;
}
