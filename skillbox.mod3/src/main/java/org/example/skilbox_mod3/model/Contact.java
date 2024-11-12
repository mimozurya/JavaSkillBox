package org.example.skilbox_mod3.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    long id;
    String firstName;
    String secondName;
    String email;
    String phoneNumber;
}
