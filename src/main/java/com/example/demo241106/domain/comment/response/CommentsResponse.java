package com.example.demo241106.domain.comment.response;

import com.example.demo241106.domain.article.dto.ArticleDTO;
import com.example.demo241106.domain.comment.dto.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
public class CommentsResponse {
    private final List<CommentDTO> commentDTOS;
}
