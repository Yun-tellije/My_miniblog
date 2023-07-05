package me.jungeun.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.jungeun.springbootdeveloper.domain.Article;
import me.jungeun.springbootdeveloper.dto.AddArticleRequest;
import me.jungeun.springbootdeveloper.dto.ArticleResponse;
import me.jungeun.springbootdeveloper.dto.UpdateArticleRequest;
import me.jungeun.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // TODO
// 초기화 되지 않은 final 필드나, @NonNull이 붙은 필드에 대해 생성자를 생성

@Service // TODO
// 서비스를 지정하는 어노테이션
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}