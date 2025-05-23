package com.example.demo241106.domain.comment.Repository;

import com.example.demo241106.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(Long articleId);
    Optional<Comment> findByIdAndArticleId(Long commentId, Long articleId);
    Optional<Comment> findByIdAndParentId(Long replyId, Long parentId);
}

