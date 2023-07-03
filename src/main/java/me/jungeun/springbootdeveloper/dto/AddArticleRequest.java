package me.jungeun.springbootdeveloper.dto;

//import me.jungeun.springbootdeveloper.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jungeun.springbootdeveloper.domain.Article;

@AllArgsConstructor // TODO
@NoArgsConstructor // TODO
@Getter // TODO
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