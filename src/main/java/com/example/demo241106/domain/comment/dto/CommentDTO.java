package com.example.demo241106.domain.comment.dto;

import com.example.demo241106.domain.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentDTO {
    private final Long id;
    private final String content;
    private final String author;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.author = comment.getAuthor();
    }
}
