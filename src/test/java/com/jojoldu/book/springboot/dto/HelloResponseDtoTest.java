package com.jojoldu.book.springboot.dto;

import com.jojoldu.book.springboot.web.dto.HelloResponseDto;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {

        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        //assertj라는 테스트 검증 라이브러리의 검증 메소드
        //검증하고 싶은 대상을 메소드 인자로 받음. 메소드 체이닝 지원
        //isEqualTo - assertj의 동등비교 메서드, assertThat()에 있는 값과 동일할 때만 성공함
        //assertThat는 Junit의 기본 라이브러리가 아닌 assertj의 assertThat을 사용
        //Junit에 비해 장점은 다음과 같음
        //CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않음.
        //자동완성이 좀 더 확실하게 지원됨.
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

}
