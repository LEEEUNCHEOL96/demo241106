package com.example.demo241106.domain.comment.service;

import com.example.demo241106.domain.comment.Repository.CommentRepository;
import com.example.demo241106.domain.comment.dto.CommentDTO;
import com.example.demo241106.domain.comment.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    // 댓글 목록 조회
    public List<CommentDTO> getList() {
        List<Comment> commentList = this.commentRepository.findAll();

        List<CommentDTO> commentDTOList = commentList.stream()
                .map(comment -> new CommentDTO(comment))
                .collect(Collectors.toList());
        return commentDTOList;
    }

    // 댓글 단건 조회
    public Comment getComment(Long id) {
        Optional<Comment> optionalComment = this.commentRepository.findById(id);

        return optionalComment.orElse(null);
    }

    // 댓글 생성
    public Comment write(String content, String author) {
        Comment comment = Comment.builder()
                .content(content)
                .author(author)
                .build();
        this.commentRepository.save(comment);
        return comment;
    }

    // 댓글 수정
    public Comment update(Comment comment, String content, String author) {
        comment.setContent(content);
        comment.setAuthor(author);
        this.commentRepository.save(comment);
        return comment;
    }

    // 댓글 삭제
    public void delete(Comment comment) {
        this.commentRepository.delete(comment);
    }
}
