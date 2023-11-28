package com.develop.internetshop.entities.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 */

@Entity
@Table(name = "user_table")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Schema(description = "Сущность пользователя")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Уникальный идентификатор")
    private Long id;

    @Column(name = "first_name", length = 50)
    @Schema(description = "Имя")
    private String firstName;

    @Column(name = "middle_name", length = 50)
    @Schema(description = "Отчество")
    private String middleName;

    @Column(name = "last_name", length = 50)
    @Schema(description = "Фамилия")
    private String lastName;

    @Column(name = "mobile", length = 15)
    @Schema(description = "Мобильный телефон")
    private String mobile;

    @Column(name = "email", unique = true, length = 50, nullable = false)
    @Schema(description = "Email")
    private String email;

    @Column(name = "password_hash", length = 32, nullable = false)
    @Schema(description = "Хеш пароля")
    private String passwordHash;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Тип пользователя (админ, обычный пользователь, работник)")
    private UserType type;

    @Column(name = "registered_at")
    @Schema(description = "Дата регистрации")
    private Date registeredAt;

    @Column(name = "last_login")
    @Schema(description = "Когда последний раз был в сети")
    private Date lastLogin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(type.name()));
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}