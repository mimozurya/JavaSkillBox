package org.example.skillbox_mod2.domain;

public record User (String firstName, String lastName, int age) {
    @Override
    public String toString() {
        return "User{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
