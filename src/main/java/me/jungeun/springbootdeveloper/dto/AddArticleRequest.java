package me.jungeun.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jungeun.springbootdeveloper.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;

    private String content;

    public Article toEntity(String author) {
        return Article.builder() // 객체생성 builder : 보기 편하라고
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}