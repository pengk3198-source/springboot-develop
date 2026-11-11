package me.scpark.service;

import lombok.RequiredArgsConstructor;
import me.scpark.domain.Article;
import me.scpark.dto.AddArticleRequest;
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


}
