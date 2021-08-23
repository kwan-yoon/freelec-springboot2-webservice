package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    /*
    *   @Entity
    *   테이블과 링크될 클래스임을 나타냄
    *   기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭함
    *   EX) SalesManager.java -> sales_manager table
    *
    *   @Id
    *   해당 테이블의 PK 필드를 나타냄
    *
    *   @GeneratedValue
    *   PK의 생성 규칙을 나타냄
    *   스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 됨
    *
    *   @Column
    *   테이블의 컬럼을 나타내며 굳이 선언하지 않아도 해당 클래스의 필드는 모두 컬럼이됨
    *   사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용함
    *   문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나
    *   타입을 TEXT로 변경하고 싶거나 등의 경우에 사용함
    * 
    *   @NoArgsConstructor
    *   기본생성자 자동 추가
    *
    *   @Getter
    *   클래스 내 모든 필드의 Getter 메소드를 자동 생성
    * 
    *   @Builder
    *   해당 클래스의 빌더 패턴 생성
    *   생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    *
    *   Posts클래스에는 Setter 메소드가 없음.
    *   Setter를 무작정 생성하면 해당 클래스의 인스턴스 값들이 언제 어디서 변하는지 알 수 없기 때문임
    *   대신, 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야함
    *
    *   Setter메소드가 없는데, 값 변경이 필요할 때는 그럼 어떻게 해줘야할까?
    *   기본적인 구조는 생성자를 통해 최종값을 채운 후 DB에 삽입하며,
    *   값 변경이 필요한 경우에 해당 이벤트에 맞는 public 메소드를 호출하여 변경하는 것을 전제로함
    *
    *   이 책에서는 생성자 대신에 @Builder를 통해 제공되는 빌더 클래스를 사용함
    *   생성자나 빌더나 생성 시점에 값을 채워주는 역할은 같음
    *   다만 생성자의 경우 지금 채워야 할 필드가 무엇인지 명확히 지정할 수 없음
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
