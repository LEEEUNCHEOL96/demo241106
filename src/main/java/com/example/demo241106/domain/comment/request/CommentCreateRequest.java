package com.example.demo241106.domain.comment.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequest {
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
    @NotBlank
    private String author;
}