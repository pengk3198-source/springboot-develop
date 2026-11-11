package me.scpark.controller;

import lombok.RequiredArgsConstructor;
import me.scpark.domain.Article;
import me.scpark.dto.AddArticleRequest;
import me.scpark.dto.ArticleResponse;
import me.scpark.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> addArticle(
            @RequestBody AddArticleRequest request) {

        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ArticleResponse(savedArticle));
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> getAllArticles(){
//      List<Article> articleList=  blogService.getAll();
//      List<ArticleResponse> articleResponseList=new ArrayList<>();
//
//      for(Article article:articleList){
//          ArticleResponse ar=new ArticleResponse(article);
//          articleResponseList.add(ar);
//      }
        List<ArticleResponse> articleResponseList=blogService
                .getAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
      return ResponseEntity.ok().body(articleResponseList);

    }
    @GetMapping("/api/articles/{id}")
    public Article getArticle(@PathVariable Long id) {
        return blogService.findById(id);
    }

}