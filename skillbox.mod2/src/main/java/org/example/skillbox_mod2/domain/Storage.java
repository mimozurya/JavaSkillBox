package org.example.skillbox_mod2.domain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class Storage {
    private final HashMap<Long, User> users;
    private Long lastId;

    public Storage() {
        this.lastId = 0L;
        this.users = new HashMap<>();
    }

    public Storage(List<User> users) {
        this.lastId = 0L;
        this.users = new HashMap<>();
        for (User user : users) {
            add(user);
        }
    }

    public void add(User user) {
        users.put(lastId, user);
        lastId++;
    }

    public void delete(Long id) {
        users.remove(id);
    }

    public void deleteAll() {
        users.clear();
    }

    public HashMap<Long, User> getAll() {
        return users;
    }
}
