package com.example.demo241106.domain.comment.response;

import com.example.demo241106.domain.article.entity.Article;
import com.example.demo241106.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentModifyResponse {
    private final Comment comment;

}
