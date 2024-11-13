package com.example.demo241106.domain.comment.response;

import com.example.demo241106.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCreateResponse {
    private final Comment comment;
}
