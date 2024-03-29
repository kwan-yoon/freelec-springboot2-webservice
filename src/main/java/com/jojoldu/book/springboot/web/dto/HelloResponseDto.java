package com.jojoldu.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//선언된 모든 필드의 get 메소드를 생성해줍니다.
@Getter
//final이 없는 필드는 생성자에 포함되지 않습니다.
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
