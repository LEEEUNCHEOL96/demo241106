package com.example.demo241106.domain.article.dto;

import com.example.demo241106.domain.article.entity.Article;
import com.example.demo241106.domain.comment.dto.CommentDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ArticleDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final String subject;

    private final String content;

    private final String author;
    private List<CommentDTO> comments; // 댓글 목록 추가

    // 좋아요 수
    private int likesCount;

    private final LocalDateTime createdDate;

    @LastModifiedDate
    private final LocalDateTime modifiedDate;

    public ArticleDTO(Article article){
        this.id = article.getId();
        this.subject = article.getSubject();
        this.content = article.getContent();
        this.author = article.getAuthor();
        this.comments = article.getComments().stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
        this.createdDate = article.getCreatedDate();
        this.modifiedDate = article.getModifiedDate();
    }
}
