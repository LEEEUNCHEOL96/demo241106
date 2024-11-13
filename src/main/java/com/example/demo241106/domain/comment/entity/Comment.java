package com.example.demo241106.domain.comment.entity;

import com.example.demo241106.domain.article.entity.Article;
import com.example.demo241106.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Comment extends BaseEntity {

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn//(name = "article_id")
    private Article article;  // 댓글이 속한 게시글
    private String content;  // 댓글 내용

    private String author;
}
