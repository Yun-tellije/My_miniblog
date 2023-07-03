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
@NoArgsConstructor(access = AccessLevel.PROTECTED) // TODO
@Getter // TODO
@Entity // TODO
public class Article {

    @Id  // TODO
    @GeneratedValue(strategy = GenerationType.IDENTITY) // TODO
    @Column(name = "id", updatable = false)
    // updatable = false
    // 엔티티의 필드나 속성이 데이터베이스에서 업데이트되지 않도록 지정
    // 가입 시점에서 설정하고 나중에 변경할 수 없게 할 때 사용
    private Long id;

    @Column(name = "title", nullable = false)
    // nullable = false
    // 엔티티의 필드나 속성이 null 값이 될 수 없음을 의미, null이면 예외 발생
    // 이 필드가 반드시 지정되어야할 때 사용
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate // TODO
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate // TODO
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
}