package me.scpark.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.scpark.domain.Article;
import me.scpark.dto.AddArticleRequest;
import me.scpark.dto.UpdateArticleRequest;
import me.scpark.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    @Autowired
    private final BlogRepository blogRepository;


    public Article save(AddArticleRequest dto){
        return blogRepository.save(dto.toEntity());
    }

    public List<Article>getAll(){
        return blogRepository.findAll();

    }
    public Article findById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("文章不存在: " + id));
    }
    public void delete(Long id) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("文章不存在: " + id));

        blogRepository.delete(article);
    }




    public Article update(long id, UpdateArticleRequest req) {

        Article article = blogRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("not found: " + id));

        //Article article = new Article(req.getTitle(), req.getContent());
       // article.setId(id);

        article.update(req.getTitle(), req.getContent());

        return blogRepository.save(article);
    }





}
