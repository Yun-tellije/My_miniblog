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

@RequiredArgsConstructor
// 초기화 되지않은 final 필드나, @NonNull이 붙은 필드에 대해 생성자를 생성
// DI에는 필드 주입, 수정자 주입, 생성자 주입 3가지 방법이 존재
// 생성자 주입을 위한 코드는 번거로움이 존재 -> 그래서 이거 사용

@RestController
// @Controller + @ResponseBody
// Json 형태로 객체 데이터를 반환하는 용도로 사용

public class BlogApiController {

    private final BlogService blogService;
// new String("sdf", "sdf)
    // new String().title("sdf").contend("sdf)
    @PostMapping("/api/articles") // TODO
    // HTTP Post Method에 해당하는 단축 표현, 서버에 리소스를 등록할 때 사용

    // 새로운 글 추가. 요청 바디에는 'AddArticleRequest' 객체가 필요
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) { // RequestBody -> dto
        // HTTPRequest의 본문 requestBody의 내용을 자바 객체로 매핑하는 역할

        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED) // 201번 응답 코드 번호
                .body(savedArticle);
    }

    @GetMapping("/api/articles") // TODO
    // HTTP Get Method에 해당하는 단축 표현, 서버의 리소스를 조회할 때 사용

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
    // HTTP Get Method에 해당하는 단축 표현, 서버의 리소스를 조회할 때 사용

    // 특정 ID의 글을 조회. 'ArticleResponse' 객체를 반환
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        // 데이터를 받아올 때 사용, 값을 하나만 받아올 수 있다
        // <-> RequestParam

        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}") // TODO
    // 서버의 리소스를 삭제

    // 특정 ID의 글을 삭제.
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}") // TODO
    // 서버의 리소스를 모두 수정

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
