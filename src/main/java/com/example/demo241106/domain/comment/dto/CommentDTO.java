package com.example.demo241106.domain.comment.dto;

import com.example.demo241106.domain.comment.entity.Comment;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentDTO {
    private final Long id;
    private final String content;
    private final String author;
    // 대댓글
    private List<CommentDTO> replies;

    private final LocalDateTime createdDate;

    @LastModifiedDate
    private final LocalDateTime modifiedDate;



    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.author = comment.getAuthor();
        // 대댓글 리스트를 DTO로 변환
        this.replies = comment.getReplies().stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
    }
}
