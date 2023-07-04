package me.jungeun.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.jungeun.springbootdeveloper.domain.Article;
import me.jungeun.springbootdeveloper.dto.ArticleListViewResponse;
import me.jungeun.springbootdeveloper.dto.ArticleViewResponse;
import me.jungeun.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
// 초기화 되지 않은 final 필드나, @NonNull이 붙은 필드에 대해 생성자를 생성

@Controller // TODO
// 컨트롤러 클래스에 작성하여 Controller임을 나타내고 bean으로 등록
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    // HTTP Get Method에 해당하는 단축 표현, 서버의 리소스를 조회할 때 사용

    // 모든 글을 조회. 'ArticleListViewResponse' 객체의 리스트를 모델에 추가하고
    // 'articleList' 뷰를 반환
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    // HTTP Get Method에 해당하는 단축 표현, 서버의 리소스를 조회할 때 사용

    // 특정 ID의 글을 조회. 'ArticleViewResponse' 객체를 모델에 추가
    // 'article' 뷰를 반환
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }


    @GetMapping("/new-article")
    // HTTP Get Method에 해당하는 단축 표현, 서버의 리소스를 조회할 때 사용

    // 새로운 글 작성 페이지를 불러옴. 선택적으로 'id' 매게변수를 받아 해당 ID의 글 조회
    // 'ArticleViewResponse' 객체를 모델에 추가, 'newArticle' 뷰를 반환
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }
    // 각 메서드는 'Model' 객체를 매개변수로 받아 모델에 필요한 데이터를 추가하고,
    // 뷰 이름을 반환
    // 이를 통해 뷰 템플릿 엔진은 해당 뷰의 템플릿을 렌더링하여 클라이언트에게 응답
}