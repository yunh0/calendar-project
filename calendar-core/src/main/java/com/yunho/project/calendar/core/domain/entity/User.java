package com.yunho.project.calendar.core.domain.entity;

import com.yunho.project.calendar.core.util.Encryptor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String name;

    private String email;

    private String password;

    private LocalDate birthday;

    @Builder
    public User(String name, String email, String password, LocalDate birthday) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public boolean isMatched(Encryptor encryptor, String pw) {
        return encryptor.isMatch(pw, this.password);
    }

}
