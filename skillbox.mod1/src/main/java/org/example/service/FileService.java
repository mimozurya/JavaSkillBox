package org.example.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor

public class FileService {
    final UserParser userParser;


    public HashMap<String, User> initContacts() {
        HashMap<String, User> contacts = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("skillbox.mod1/src/main/resources/default-contacts.csv"))) {
            while (reader.ready()) {
                User user = userParser.stringToUser(reader.readLine());
                if (user == null) continue;
                contacts.put(user.getEmail(), user);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

    public void addUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("skillbox.mod1/src/main/resources/default-contacts.csv",true))) {
            writer.newLine();
            writer.write(user.getFullName() + ";" + user.getPhoneNumber() + ";" + user.getEmail());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
