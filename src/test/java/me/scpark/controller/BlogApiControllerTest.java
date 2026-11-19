package me.scpark.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.scpark.dto.AddArticleRequest;
import me.scpark.dto.UpdateArticleRequest;
import me.scpark.repository.BlogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.List;
import me.scpark.domain.Article;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.*;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BlogRepository blogRepository;

    @DisplayName("블로그 글 추가에 성공한다.")
    @Test
    public void addArticle() throws Exception{
        // 준비 given : 데이터, 요청 URL
        String title = "find";
        String content = "find";

        AddArticleRequest req = new AddArticleRequest(title, content);
        String reqBody = objectMapper.writeValueAsString(req);
        String url = "/api/articles";

        // 실행 when : 게시글 등록 요청
        ResultActions result=mockMvc.perform(post(URI.create(url))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(reqBody));

        // 검증 then : DB에 레코드 1개가 등록되어 있고 그 레코드의 내용이 위의 값과 같은지 확인

        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(2);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);

    }
    @DisplayName("블로그 글 전체 조회에 성공한다. get")
    @Test
    public void findAllArticles() throws Exception {

        // 준비 given
        Article article = blogRepository.save(
                Article.builder()
                        .title("find")
                        .content("find")
                        .build()
        );

        // 실행 when
        ResultActions result = mockMvc.perform(
                get("/api/articles")
        );

        // 검증 then
        result.andExpect(status().isOk());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles)
                .extracting(Article::getId)
                .contains(article.getId());
    }
    @DisplayName("블로그 글 삭제에 성공한다.deleter")
    @Test
    public void deleteArticle() throws Exception {

        // 준비 given
        Article article = blogRepository.save(
                Article.builder()
                        .title("delter")
                        .content("deleter")
                        .build()
        );

        Long id = article.getId();

        // 실행 when
        ResultActions result = mockMvc.perform(
                delete("/api/articles/{id}", id)
        );

        // 검증 then
        result.andExpect(status().isNoContent());

        assertThat(blogRepository.findById(id)).isEmpty();
    }
    @DisplayName("블로그 글 수정에 성공한다.change")
    @Test
    public void updateArticle() throws Exception {

        // 준비 given
        Article article = blogRepository.save(
                Article.builder()
                        .title("change")
                        .content("change")
                        .build()
        );

        Long id = article.getId();

        UpdateArticleRequest request =
                new UpdateArticleRequest(
                        "after",
                        "after"
                );

        String reqBody =
                objectMapper.writeValueAsString(request);

        // 실행 when
        ResultActions result = mockMvc.perform(
                put("/api/articles/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqBody)
        );

        // 검증 then
        result.andExpect(status().isOk());

        Article updatedArticle =
                blogRepository.findById(id).orElseThrow();

        assertThat(updatedArticle.getTitle())
                .isEqualTo("after");

        assertThat(updatedArticle.getContent())
                .isEqualTo("after");
    }


}