package com.example.demo241106.domain.comment.response;

import com.example.demo241106.domain.comment.dto.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CommentResponse {
    private final CommentDTO comment;
}

