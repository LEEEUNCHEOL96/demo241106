package com.example.demo241106.domain.article.controller;

import com.example.demo241106.domain.article.dto.ArticleDTO;
import com.example.demo241106.domain.article.entity.Article;
import com.example.demo241106.domain.article.request.ArticleCreateRequest;
import com.example.demo241106.domain.article.request.ArticleModifyRequest;
import com.example.demo241106.domain.article.response.ArticleCreateResponse;
import com.example.demo241106.domain.article.response.ArticleModifyResponse;
import com.example.demo241106.domain.article.response.ArticleResponse;
import com.example.demo241106.domain.article.response.ArticlesResponse;
import com.example.demo241106.domain.article.service.ArticleService;
import com.example.demo241106.global.RsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/demo241106/articles", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
//
@Tag(name = "ApiV2ArticleController", description = "게시글 CRUD API")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("") // 다건조회
    @Operation(summary = "게시글 다건 조회")
    public RsData<ArticlesResponse> list() {
        List<ArticleDTO> articleDTOS = this.articleService.getList();

        return RsData.of("200","게시글 다건 조회 성공", new ArticlesResponse(articleDTOS));
    }


    @GetMapping("/{id}") //단건조회
    @Operation(summary = "게시글 단건 조회")
    public RsData<ArticleResponse> getArticle(@PathVariable("id") Long id){
        Article article = this.articleService.getArticle(id);

        if(article ==null)
            return RsData.of("500","%d 번게시물은 존재하지 않습니다.".formatted(id),null);

        ArticleDTO articleDTO = new ArticleDTO(article);
        return RsData.of("200","게시글 단건 조회 성공", new ArticleResponse(articleDTO));
    }

    @PostMapping("") // 생성
    @Operation(summary = "게시글 생성")
    public RsData<ArticleCreateResponse> create(@Valid @RequestBody ArticleCreateRequest articleCreateRequest){
        Article article = this.articleService.write(articleCreateRequest.getSubject(),articleCreateRequest.getContent(),articleCreateRequest.getAuthor());

        return RsData.of("200","게시글 등록 성공", new ArticleCreateResponse(article));
    }

    @PatchMapping("/{id}") // 수정
    @Operation(summary = "게시글 수정")
    public RsData<ArticleModifyResponse> modify(@PathVariable("id") Long id, @Valid @RequestBody ArticleModifyRequest articleModifyRequest){
        Article article = this.articleService.getArticle(id);

        if(article ==null)
            return RsData.of("500","%d 번게시물은 존재하지 않습니다.".formatted(id),null);

        article = this.articleService.update(article,articleModifyRequest.getSubject(),articleModifyRequest.getContent(),articleModifyRequest.getAuthor());


        return RsData.of("200","게시글 수정 성공",new ArticleModifyResponse(article));
    }

    @DeleteMapping("/{id}")  // 삭제
    @Operation(summary = "게시글 삭제")
    public RsData<ArticleResponse> delete(@PathVariable("id") Long id){
        Article article = this.articleService.getArticle(id);

        if(article ==null)
            return RsData.of("500","%d 번게시물은 존재하지 않습니다.".formatted(id),null);

        this.articleService.delete(article);
        ArticleDTO articleDTO = new ArticleDTO(article);

        return RsData.of("200","%d 번 게시물 삭제 성공".formatted(id), new ArticleResponse(articleDTO));
    }
}