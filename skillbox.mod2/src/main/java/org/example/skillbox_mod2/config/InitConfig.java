package org.example.skillbox_mod2.config;

import org.example.skillbox_mod2.domain.User;
import org.example.skillbox_mod2.domain.Storage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("auto-fill")
public class InitConfig {

    @Bean
    public Storage userStorage(){
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("skillbox.mod2/src/main/resources/init.txt"))){
            while (reader.ready()){
                String[] data = reader.readLine().split(";");
                users.add(new User(data[0],data[1],Integer.parseInt(data[2])));
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Storage(users);
    }

}
