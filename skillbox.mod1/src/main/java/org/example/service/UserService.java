package org.example.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.domain.Contact;
import org.example.domain.Message;
import org.example.domain.User;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService {
    final UserParser userParser;
    final Contact contact;
    final FileService fileService;

    public String add(String inputData) {
        var user = userParser.stringToUser(inputData);
        if (user == null) return "Failed to add a user";
        contact.add(user);
        fileService.addUser(user);
        return Message.ACCESS_ADD.getText();
    }

    public String delete(String email) {
        if (!contact.getContacts().containsKey(email)) {
            return Message.NOT_FOUND_USER.getText();
        }
        contact.delete(email);
        return Message.ACCESS_DELETE.getText();
    }

    public String getAll() {
        if (contact.getContacts().isEmpty()) {
            return Message.NULL_CONTACTS.getText();
        }
        return contact.getContacts().values().stream()
                .map(User::toString)
                .collect(Collectors.joining("\n"));
    }

    public String help() {
        return Message.HELP.getText();
    }
}
