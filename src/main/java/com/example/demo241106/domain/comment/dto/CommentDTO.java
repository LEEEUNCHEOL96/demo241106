package com.example.demo241106.domain.comment.dto;

import com.example.demo241106.domain.comment.entity.Comment;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
public class CommentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final String content;  // 댓글 내용

    private final String author;   // 댓글 작성자

    private final LocalDateTime createdDate; // 댓글 생성일

    @LastModifiedDate
    private final LocalDateTime modifiedDate; // 댓글 수정일

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.author = comment.getAuthor();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
    }
}