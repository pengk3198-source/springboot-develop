package me.scpark.service;

import lombok.RequiredArgsConstructor;
import me.scpark.domain.Article;
import me.scpark.dto.AddArticleRequest;
import me.scpark.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {

    @Autowired
    private final BlogRepository blogRepository;


    public Article save(AddArticleRequest dto){
        return blogRepository.save(dto.toEntity());
    }


}
