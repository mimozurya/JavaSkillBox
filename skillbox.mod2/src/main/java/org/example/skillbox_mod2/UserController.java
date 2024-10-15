package org.example.skillbox_mod2;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.skillbox_mod2.domain.User;
import org.example.skillbox_mod2.domain.Storage;
import org.example.skillbox_mod2.eventlistener.event.AddEvent;
import org.example.skillbox_mod2.eventlistener.event.DeleteEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Map;

@ShellComponent
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserController {
    Storage storage;
    ApplicationEventPublisher applicationEventPublisher;

    @ShellMethod
    public String getAll() {
        if(storage.getAll().isEmpty()){
            return "List is empty";
        }
        StringBuilder stringUsers=new StringBuilder();
        for (Map.Entry<Long, User> user: storage.getAll().entrySet()){
            stringUsers.append(user.getKey());
            stringUsers.append(user.getValue().toString()).append("\n");
        }
        return stringUsers.toString();
    }

    @ShellMethod
    public void add(String firstName, String secondName, int age) {
        User user = new User(firstName,secondName,age);
        applicationEventPublisher.publishEvent(new AddEvent(user.toString()));
        storage.add(user);
    }

    @ShellMethod
    public void delete(Long id) {
        applicationEventPublisher.publishEvent(new DeleteEvent(id.toString()));
        storage.delete(id);
    }

    @ShellMethod
    public String deleteAll() {
        storage.deleteAll();
        return "Storage cleared";
    }
}
