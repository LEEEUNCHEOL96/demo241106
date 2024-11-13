package com.example.demo241106.domain.comment.Controller;

import com.example.demo241106.domain.comment.dto.CommentDTO;
import com.example.demo241106.domain.comment.entity.Comment;
import com.example.demo241106.domain.comment.request.CommentCreateRequest;
import com.example.demo241106.domain.comment.request.CommentModifyRequest;
import com.example.demo241106.domain.comment.response.CommentCreateResponse;
import com.example.demo241106.domain.comment.response.CommentModifyResponse;
import com.example.demo241106.domain.comment.response.CommentResponse;
import com.example.demo241106.domain.comment.response.CommentsResponse;
import com.example.demo241106.domain.comment.service.CommentService;
import com.example.demo241106.global.RsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/demo241106/articles/{articleId}/comments")
@Tag(name = "ApiV2CommentController", description = "게시글 댓글 CRUD API")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("") // 특정 게시글의 댓글 다건 조회
    @Operation(summary = "게시글의 댓글 다건 조회")
    public RsData<CommentsResponse> list(@PathVariable("articleId") Long articleId) {
        List<CommentDTO> commentDTOS = this.commentService.getCommentsByArticleId(articleId);
        return RsData.of("200", "게시글의 댓글 다건 조회 성공", new CommentsResponse(commentDTOS));
    }

    @GetMapping("/{id}") // 특정 게시글의 특정 댓글 단건 조회
    @Operation(summary = "게시글의 특정 댓글 단건 조회")
    public RsData<CommentResponse> getComment(@PathVariable("articleId") Long articleId, @PathVariable("id") Long id) {
        Comment comment = this.commentService.getCommentByArticleIdAndCommentId(articleId, id);
        if (comment == null) {
            return RsData.of("500", "%d 번 댓글은 존재하지 않습니다.".formatted(id), null);
        }
        CommentDTO commentDTO = new CommentDTO(comment);
        return RsData.of("200", "게시글의 댓글 단건 조회 성공", new CommentResponse(commentDTO));
    }

    @PostMapping("") // 특정 게시글에 댓글 생성
    @Operation(summary = "게시글에 댓글 생성")
    public RsData<CommentCreateResponse> create(@PathVariable("articleId") Long articleId, @Valid @RequestBody CommentCreateRequest commentCreateRequest) {
        Comment comment = this.commentService.write(articleId, commentCreateRequest.getContent(), commentCreateRequest.getAuthor());
        return RsData.of("200", "댓글 등록 성공", new CommentCreateResponse(comment));
    }

    @PatchMapping("/{id}") // 특정 게시글의 특정 댓글 수정
    @Operation(summary = "게시글의 특정 댓글 수정")
    public RsData<CommentModifyResponse> modify(@PathVariable("articleId") Long articleId, @PathVariable("id") Long id, @Valid @RequestBody CommentModifyRequest commentModifyRequest) {
        Comment comment = this.commentService.getCommentByArticleIdAndCommentId(articleId, id);
        if (comment == null) {
            return RsData.of("500", "%d 번 댓글은 존재하지 않습니다.".formatted(id), null);
        }
        comment = this.commentService.update(comment, commentModifyRequest.getContent(), commentModifyRequest.getAuthor());
        return RsData.of("200", "댓글 수정 성공", new CommentModifyResponse(comment));
    }

    @DeleteMapping("/{id}") // 특정 게시글의 특정 댓글 삭제
    @Operation(summary = "게시글의 특정 댓글 삭제")
    public RsData<CommentResponse> delete(@PathVariable("articleId") Long articleId, @PathVariable("id") Long id) {
        Comment comment = this.commentService.getCommentByArticleIdAndCommentId(articleId, id);
        if (comment == null) {
            return RsData.of("500", "%d 번 댓글은 존재하지 않습니다.".formatted(id), null);
        }
        this.commentService.delete(comment);
        CommentDTO commentDTO = new CommentDTO(comment);
        return RsData.of("200", "%d 번 댓글 삭제 성공".formatted(id), new CommentResponse(commentDTO));
    }

    // 대댓글 생성
    @PostMapping("/{parentCommentId}/replies")
    @Operation(summary = "대댓글 생성")
    public RsData<CommentCreateResponse> createReply(
            @PathVariable("parentCommentId") Long parentCommentId,
            @Valid @RequestBody CommentCreateRequest commentCreateRequest) {

        Comment replyComment = this.commentService.writeReply(
                parentCommentId,
                commentCreateRequest.getContent(),
                commentCreateRequest.getAuthor()
        );

        return RsData.of("200", "대댓글 등록 성공", new CommentCreateResponse(replyComment));
    }
    // 대댓글 수정
    @PatchMapping("/{parentCommentId}/replies/{replyId}")
    @Operation(summary = "대댓글 수정")
    public RsData<CommentModifyResponse> modifyReply(
            @PathVariable("parentCommentId") Long parentCommentId,
            @PathVariable("replyId") Long replyId,
            @Valid @RequestBody CommentModifyRequest commentModifyRequest) {

        Comment replyComment = commentService.getReplyByParentIdAndReplyId(parentCommentId, replyId);

        if (replyComment == null) {
            return RsData.of("500", "%d 번 대댓글은 존재하지 않습니다.".formatted(replyId), null);
        }

        replyComment = commentService.update(replyComment, commentModifyRequest.getContent(), commentModifyRequest.getAuthor());
        return RsData.of("200", "대댓글 수정 성공", new CommentModifyResponse(replyComment));
    }

    // 대댓글 삭제
    @DeleteMapping("/{parentCommentId}/replies/{replyId}")
    @Operation(summary = "대댓글 삭제")
    public RsData<CommentResponse> deleteReply(
            @PathVariable("parentCommentId") Long parentCommentId,
            @PathVariable("replyId") Long replyId) {

        Comment replyComment = commentService.getReplyByParentIdAndReplyId(parentCommentId, replyId);

        if (replyComment == null) {
            return RsData.of("500", "%d 번 대댓글은 존재하지 않습니다.".formatted(replyId), null);
        }

        commentService.delete(replyComment);
        CommentDTO replyDTO = new CommentDTO(replyComment);
        return RsData.of("200", "%d 번 대댓글 삭제 성공".formatted(replyId), new CommentResponse(replyDTO));
    }
}