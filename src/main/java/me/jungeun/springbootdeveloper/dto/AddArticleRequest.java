package me.jungeun.springbootdeveloper.dto;

//import me.jungeun.springbootdeveloper.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jungeun.springbootdeveloper.domain.Article;

@AllArgsConstructor // TODO
// 모든 필드 값을 파라미터로 받는 생성자를 만들어줌

@NoArgsConstructor // TODO
// 파라미터가 없는 기본 생성자를 생성

@Getter // TODO
// get 메소드 생성
public class AddArticleRequest {

    private String title;
    private String content;

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}