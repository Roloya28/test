package com.example.jdbc4.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Memo {

    private Long id;
    private String content;

    public Memo(String content) {
        this.content = content;
    }
}
