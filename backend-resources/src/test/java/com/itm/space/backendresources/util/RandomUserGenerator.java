package com.itm.space.backendresources.util;

import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class RandomUserGenerator {

    private static final String[] FIRST_NAMES = {"Alice", "Bob", "Charlie", "Diana", "Emily", "Frank", "Grace", "Henry", "Ivy", "Jack"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"};
    private static final String[] EMAIL_DOMAINS = {"gmail.com", "yahoo.com", "hotmail.com", "example.com", "test.com"};
    private static final int PASSWORD_LENGTH = 8;
    private static final int USERNAME_LENGTH = 8;

    public String generateUsername() {
        Random random = new Random();
        StringBuilder username = new StringBuilder();
        for (int i = 0; i < USERNAME_LENGTH; i++) {
            char c = (char) ('a' + random.nextInt(26));
            username.append(c);
        }
        return username.toString();
    }

    public String generateEmail(String username) {
        Random random = new Random();
        String domain = EMAIL_DOMAINS[random.nextInt(EMAIL_DOMAINS.length)];
        return username + "@" + domain;
    }

    public String generatePassword() {
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            char c;
            if (random.nextBoolean()) {
                c = (char) ('a' + random.nextInt(26));
            } else {
                c = (char) ('0' + random.nextInt(10));
            }
            password.append(c);
        }
        return password.toString();
    }

    public String generateFirstName() {
        Random random = new Random();
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
    }

    public String generateLastName() {
        Random random = new Random();
        return LAST_NAMES[random.nextInt(LAST_NAMES.length)];
    }
}
