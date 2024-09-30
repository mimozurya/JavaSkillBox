package org.example.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.domain.Contact;
import org.example.service.FileService;
import org.example.service.UserParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("init")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class InitConfig {

    @Bean
    public UserParser userMapper() {
        return new UserParser();
    }

    @Bean
    public FileService fileService() {
        return new FileService(userMapper());
    }

    @Bean
    public Contact contacts() {
        return new Contact(fileService().initContacts());
    }
}
