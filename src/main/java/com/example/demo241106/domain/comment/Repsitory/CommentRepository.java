package com.example.demo241106.domain.comment.Repsitory;

import com.example.demo241106.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글에 대한 모든 댓글 조회
    List<Comment> findByArticleId(Long articleId);

    // 특정 댓글 조회 (게시글과 댓글 ID를 조합하여 조회)
    Comment findByIdAndArticleId(Long commentId, Long articleId);
}
