package me.jungeun.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class) // TODO
// 엔티티가 삽입, 삭제, 수정, 조회 등의 작업을 할 때 전, 후에 어떠한 작업을 하기 위해
// 이벤트 처리를 위해 사용

@NoArgsConstructor(access = AccessLevel.PROTECTED) // TODO
// 파라미터가 없는 기본 생성자를 생성

@Getter // TODO
// Lombok에서 가장 많이 사용되는 어노테이션, 자동으로 getPerson() 메소드 생성

@Entity // TODO
// JPA를 사용해 테이블과 매핑할 클래스에 붙여주는 어노테이션
// @Entity를 붙이면 JPA가 해당 클래스를 관리
public class Article {

    @Id  // TODO
    // 특정 속성을 기본키로 설정하는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // TODO
    // DBMS 종류에 따라 맞춰 자동적으로 값을 JPA에서 생성해 입력
    // strategy라는 옵션이 존재해 자동 생성 값을 어떻게 생성할지 지정 가능

    @Column(name = "id", updatable = false)
    // updatable = false
    // 엔티티의 필드나 속성이 데이터베이스에서 업데이트되지 않도록 지정
    // 가입 시점에서 설정하고 나중에 변경할 수 없게 할 때 사용
    private Long id;

    @Column(name = "title", nullable = false)
    // @Column 데이터베이스의 테이블에 있는 컬럼과 동일하게 1:1로 매칭되기 때문에
    // Entity 클래스안에 내부변수로 정의된다

    // nullable = false
    // 엔티티의 필드나 속성이 null 값이 될 수 없음을 의미, null이면 예외 발생
    // 이 필드가 반드시 지정되어야할 때 사용
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate // TODO
    // 생성된 시간 정보

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate // TODO
    // 수정된 시간 정보
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder  // TODO 빌더 패턴 설명 왜 쓰는지 왜 좋은지 대원이를 이해 시켜라!
    // @Builder
    // 빌더 패턴을 자동으로 적용?
    // 생성자의 매개변수 순서에 영향을 받지 않고 필요한 필드만 설정가능
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Column(name = "author", nullable = false)
    private String author;

    @Builder
    public Article(String author, String title, String content){
        this.author = author;
        this.title = title;
        this.content = content;
    }
}