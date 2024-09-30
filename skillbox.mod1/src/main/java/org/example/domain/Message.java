package org.example.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Message {
    HELLO("""
            Список команд - help"""),
    HELP("""
            Команды:
            1. Добавить user - add
            2. Удалить user - delete
            3. Просмотреть all user - get
            """),
    ACCESS_ADD("Пользователь добавлен"),
    ACCESS_DELETE("Пользователь удален"),
    NULL_CONTACTS("Список пуст"),
    NOT_FOUND_USER("Пользователя нет");

    private final String text;


}
