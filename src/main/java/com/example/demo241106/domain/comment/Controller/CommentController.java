package com.example.demo241106.domain.comment.Controller;


import com.example.demo241106.domain.comment.Service.CommentService;
import com.example.demo241106.domain.comment.entity.Comment;
import com.example.demo241106.global.RsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/demo241106/articles/{articleId}/comments")
@Tag(name = "ApiV2CommentController", description = "댓글 CRUD API")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("")
    @Operation(summary = "게시글에 대한 모든 댓글 조회")
    public RsData<List<Comment>> getComments(@PathVariable Long articleId) {
        List<Comment> comments = commentService.getCommentsByArticleId(articleId);
        return RsData.of("200", "댓글 조회 성공", comments);
    }

    @PostMapping("")
    @Operation(summary = "댓글 생성")
    public RsData<Comment> createComment(@PathVariable Long articleId, @Valid @RequestBody String content) {
        Comment comment = commentService.createComment(articleId, content);
        return RsData.of("200", "댓글 생성 성공", comment);
    }

    @   PatchMapping("/{commentId}")
    @Operation(summary = "댓글 수정")
    public RsData<Comment> modifyComment(@PathVariable Long articleId, @PathVariable Long commentId, @Valid @RequestBody String content) {
        Comment comment = commentService.modifyComment(commentId, articleId, content);
        return RsData.of("200", "댓글 수정 성공", comment);
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "댓글 삭제")
    public RsData<String> deleteComment(@PathVariable Long articleId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId, articleId);
        return RsData.of("200", "댓글 삭제 성공", "댓글이 삭제되었습니다.");
    }
}