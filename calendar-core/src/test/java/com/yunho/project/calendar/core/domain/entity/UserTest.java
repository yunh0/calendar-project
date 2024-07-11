package com.yunho.project.calendar.core.domain.entity;

import com.yunho.project.calendar.core.util.Encryptor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private final Encryptor alwaysTrueEncryptor = new Encryptor() {

        @Override
        public String encrypt(String origin) {
            return origin;
        }

        @Override
        public boolean isMatch(String origin, String hashed) {
            return true;
        }
    };

    @Test
    void isMatchedTest() {
        final User user = User.builder().name("name").password("pw").email("email").build();
        assertTrue(user.isMatched(alwaysTrueEncryptor, "aaa"));
    }
}