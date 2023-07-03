package me.jungeun.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.jungeun.springbootdeveloper.domain.Article;
import me.jungeun.springbootdeveloper.dto.AddArticleRequest;
import me.jungeun.springbootdeveloper.dto.ArticleResponse;
import me.jungeun.springbootdeveloper.dto.UpdateArticleRequest;
import me.jungeun.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // TODO
@RestController // TODO
public class BlogApiController {

    private final BlogService blogService;
// new String("sdf", "sdf)
    // new String().title("sdf").contend("sdf)
    @PostMapping("/api/articles") // TODO
    // 새로운 글 추가. 요청 바디에는 'AddArticleRequest' 객체가 필요
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) { // RequestBody -> dto
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED) // 201번 응답 코드 번호
                .body(savedArticle);
    }

    @GetMapping("/api/articles") // TODO
    // 모든 글 조회. 'ArticleResponse' 객체의 리스트 반환
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }
    @GetMapping("/api/articles/{id}")
    // 특정 ID의 글을 조회. 'ArticleResponse' 객체를 반환
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}") // TODO
    // 특정 ID의 글을 삭제.
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}") // TODO
    // 특정 ID의 글을 업데이트. 요청 바디에는 'UpdateArticleRequest' 객체가 필요
    public ResponseEntity<Article> updateArticle(@PathVariable long id, // TODO
                                                 @RequestBody UpdateArticleRequest request) { // TODO
        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }
    // 각 API 메서드는 'ResponseEntity' 객체를 반환하며,
    // 적절한 HTTP 상태 코드와 응답 본문을 포함합니다.
}
