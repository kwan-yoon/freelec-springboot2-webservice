package com.jojoldu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    /*
    *   Java8이 나오기 전까지 사용되었던 Date와 Calendar 클래스는 다음과 같은 문제점이 있음
    *   1. 불변(변경이 불가능한) 객체가 아님
    *   - 멀티스레드 환경에서 언제든 문제가 발생할 수 있음
    *   2. Calendar는 월(month) 값 설계가 잘못되어있음
    *   - 10월을 나타내는 Calendar.October의 값은 9임
    *   - 당연히 10으로 생각했던 개발자들에겐 혼란을 줌
    *   그래서 8부터는 이런 문제점들을 개선한 LocalDate와 LocalDateTime을 사용함
    * */

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    /*
    *   BaseTimeEntity 클래스는 모든 Entity의 상위 클래스가 되어 Entity들의 createdDate, modifiedDate를 자동으로 관리하는 역할을 합니다
    *
    *   @MappedSuperclass
    *   - JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createdDate, modifiedDate)도 컬럼으로 인식하도록 합니다.
    *
    *   @EntityListeners(AuditingEntityListener.class)
    *   - BaseTimeEntity 클래스에 Auditing 기능을 포함시킵니다.
    *
    *   @CreatedDate
    *   - Entity가 생성되어 저장될 때 시간이 자동저장됩니다.
    *
    *   @LastModifiedDate
    *   - 조회한 Entity의 값을 변경할 때 시간이 자동 저장됩니다.
    *
    * */
}
