package org.example.skillbox_mod7.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("user")
public class User implements UserDetails {
    @Id
    String id;
    String username;
    String email;
    @Field
    Set<UserRole> roleSet;
    String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleSet;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
