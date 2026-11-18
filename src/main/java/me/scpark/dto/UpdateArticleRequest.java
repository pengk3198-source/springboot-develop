package me.scpark.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateArticleRequest {
    private String title;
    private String content;


    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}