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

    // 대댓글 생성 메소드 추가
    public Comment writeReply(Long parentCommentId, String content, String author) {
        // 부모 댓글 조회
        Comment parentComment = commentRepository.findById(parentCommentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 부모 댓글이 존재하지 않습니다."));

        // 대댓글 생성
        Comment replyComment = Comment.builder()
                .content(content)
                .author(author)
                .article(parentComment.getArticle()) // 부모 댓글의 Article로 설정
                .parent(parentComment) // 부모 댓글 설정
                .build();

        // 대댓글 저장
        return commentRepository.save(replyComment);
    }
    // 부모 댓글 ID와 대댓글 ID로 특정 대댓글 조회
    public Comment getReplyByParentIdAndReplyId(Long parentCommentId, Long replyId) {
        return commentRepository.findByIdAndParentId(replyId, parentCommentId)
                .orElse(null);
    }
}
