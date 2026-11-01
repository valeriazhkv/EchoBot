package org.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Сущность пользователя Telegram.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "telegram_id", unique = true, nullable = false)
    private Long telegramId;

    @Column(name = "username")
    private String username;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected User() {}

    public User(Long telegramId, String username) {
        this.telegramId = telegramId;
        this.username = username;
        this.createdAt = LocalDateTime.now();
    }
    public Integer getId() { return id; }
    public Long getTelegramId() { return telegramId; }
    public String getUsername() { return username; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
