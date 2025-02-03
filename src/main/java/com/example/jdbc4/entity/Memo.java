package com.example.jdbc4.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Memo {

    @Setter
    private Long id;
    private String content;

    // id 필드를 제외한 나머지 필드를 넣기
    public Memo(String content) {
        this.content = content;
    }
}
