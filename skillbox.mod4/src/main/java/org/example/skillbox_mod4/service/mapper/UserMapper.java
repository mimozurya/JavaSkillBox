package org.example.skillbox_mod4.service.mapper;

import org.example.skillbox_mod4.adapter.web.dto.UserDto;
import org.example.skillbox_mod4.domain.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto userToUserDto(UserEntity userEntity){
        return new UserDto(userEntity.getId(), userEntity.getName());
    }
}
