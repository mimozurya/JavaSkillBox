package org.example.service;

import org.example.domain.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserParser {
    public User stringToUser(String inputData) {
        try {
            String[] userData = inputData.split(";");
            if (userData.length != 3) {
                throw new IllegalArgumentException("Invalid input data: " + Arrays.toString(userData));
            }
            return new User(userData[0].trim(), userData[1].trim(), userData[2].trim());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
