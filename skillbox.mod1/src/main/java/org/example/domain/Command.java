package org.example.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Command {
    ADD("add"),
    DELETE("delete"),
    GET("get"),
    HELP("help");

    private final String command;


}
