package com.example.demo241106.domain.comment.service;

import com.example.demo241106.domain.article.entity.Article;
import com.example.demo241106.domain.article.repository.ArticleRepository;
import com.example.demo241106.domain.comment.Repository.CommentRepository;
import com.example.demo241106.domain.comment.dto.CommentDTO;
import com.example.demo241106.domain.comment.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    // 특정 게시글의 댓글 목록 조회
    public List<CommentDTO> getCommentsByArticleId(Long articleId) {
        List<Comment> commentList = commentRepository.findByArticleId(articleId);
        return commentList.stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }

    // 특정 게시글 내의 특정 댓글 조회
    public Comment getCommentByArticleIdAndCommentId(Long articleId, Long commentId) {
        return commentRepository.findByIdAndArticleId(commentId, articleId)
                .orElse(null);
    }

    // 댓글 생성
    public Comment write(Long articleId, String content, String author) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        Comment comment = Comment.builder()
                .content(content)
                .author(author)
                .article(article)
                .build();

        return commentRepository.save(comment);
    }

    // 댓글 수정
    public Comment update(Comment comment, String content, String author) {
        comment.setContent(content);
        comment.setAuthor(author);
        return commentRepository.save(comment);
    }

    // 댓글 삭제
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
}
