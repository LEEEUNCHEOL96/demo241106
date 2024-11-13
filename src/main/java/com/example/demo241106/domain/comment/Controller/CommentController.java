package com.example.demo241106.domain.comment.Controller;


import com.example.demo241106.domain.comment.service.CommentService;
import com.example.demo241106.domain.comment.dto.CommentDTO;
import com.example.demo241106.domain.comment.entity.Comment;
import com.example.demo241106.domain.comment.request.CommentCreateRequest;
import com.example.demo241106.domain.comment.request.CommentModifyRequest;
import com.example.demo241106.domain.comment.response.CommentCreateResponse;
import com.example.demo241106.domain.comment.response.CommentModifyResponse;
import com.example.demo241106.domain.comment.response.CommentResponse;
import com.example.demo241106.domain.comment.response.CommentsResponse;
import com.example.demo241106.global.RsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/demo241106/comments") // URL을 /comments로 변경
@Tag(name = "ApiV2CommentController", description = "댓글 CRUD API")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("") // 다건 조회
    @Operation(summary = "댓글 다건 조회")
    public RsData<CommentsResponse> list() {
        List<CommentDTO> commentDTOS = this.commentService.getList();

        return RsData.of("200", "댓글 다건 조회 성공", new CommentsResponse(commentDTOS));
    }

    @GetMapping("/{id}") // 단건 조회
    @Operation(summary = "댓글 단건 조회")
    public RsData<CommentResponse> getComment(@PathVariable("id") Long id) {
        Comment comment = this.commentService.getComment(id);

        if (comment == null)
            return RsData.of("500", "%d 번 댓글은 존재하지 않습니다.".formatted(id), null);

        CommentDTO commentDTO = new CommentDTO(comment);
        return RsData.of("200", "댓글 단건 조회 성공", new CommentResponse(commentDTO));
    }

    @PostMapping("") // 댓글 생성
    @Operation(summary = "댓글 생성")
    public RsData<CommentCreateResponse> create(@Valid @RequestBody CommentCreateRequest commentCreateRequest) {
        Comment comment = this.commentService.write(commentCreateRequest.getContent(), commentCreateRequest.getAuthor());

        return RsData.of("200", "댓글 등록 성공", new CommentCreateResponse(comment));
    }

    @PatchMapping("/{id}") // 댓글 수정
    @Operation(summary = "댓글 수정")
    public RsData<CommentModifyResponse> modify(@PathVariable("id") Long id, @Valid @RequestBody CommentModifyRequest commentModifyRequest) {
        Comment comment = this.commentService.getComment(id);

        if (comment == null)
            return RsData.of("500", "%d 번 댓글은 존재하지 않습니다.".formatted(id), null);

        comment = this.commentService.update(comment, commentModifyRequest.getContent(), commentModifyRequest.getAuthor());

        return RsData.of("200", "댓글 수정 성공", new CommentModifyResponse(comment));
    }

    @DeleteMapping("/{id}") // 댓글 삭제
    @Operation(summary = "댓글 삭제")
    public RsData<CommentResponse> delete(@PathVariable("id") Long id) {
        Comment comment = this.commentService.getComment(id);

        if (comment == null)
            return RsData.of("500", "%d 번 댓글은 존재하지 않습니다.".formatted(id), null);

        this.commentService.delete(comment);
        CommentDTO commentDTO = new CommentDTO(comment);

        return RsData.of("200", "%d 번 댓글 삭제 성공".formatted(id), new CommentResponse(commentDTO));
    }
}
