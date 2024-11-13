package com.example.demo241106.domain.comment.Service;

import com.example.demo241106.domain.article.entity.Article;
import com.example.demo241106.domain.article.service.ArticleService;
import com.example.demo241106.domain.comment.Repsitory.CommentRepository;
import com.example.demo241106.domain.comment.entity.Comment;

import java.util.List;

public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleService articleService;

    public CommentService(CommentRepository commentRepository, ArticleService articleService) {
        this.commentRepository = commentRepository;
        this.articleService = articleService;
    }

    // 댓글 작성
    public Comment createComment(Long articleId, String content) {
        Article article = articleService.getArticle(articleId);  // 게시글 조회

        Comment comment = Comment.builder()
                .article(article)
                .content(content)
                .build();

        return commentRepository.save(comment);
    }

    // 댓글 수정
    public Comment modifyComment(Long commentId, Long articleId, String content) {
        Comment comment = commentRepository.findByIdAndArticleId(commentId, articleId);

        if (comment == null) {
            throw new IllegalArgumentException("댓글을 찾을 수 없습니다.");
        }

        comment.setContent(content);
        return commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId, Long articleId) {
        Comment comment = commentRepository.findByIdAndArticleId(commentId, articleId);

        if (comment == null) {
            throw new IllegalArgumentException("댓글을 찾을 수 없습니다.");
        }

        commentRepository.delete(comment);
    }

    // 특정 게시글에 대한 댓글 조회
    public List<Comment> getCommentsByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }
}



