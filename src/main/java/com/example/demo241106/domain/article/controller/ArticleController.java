package com.example.demo241106.domain.article.controller;

import com.example.demo241106.domain.article.dto.ArticleDTO;
import com.example.demo241106.domain.article.entity.Article;
import com.example.demo241106.domain.article.request.ArticleCreateRequest;
import com.example.demo241106.domain.article.request.ArticleModifyRequest;
import com.example.demo241106.domain.article.response.ArticleResponse;
import com.example.demo241106.domain.article.response.ArticlesResponse;
import com.example.demo241106.domain.article.service.ArticleService;
import com.example.demo241106.global.RsData.RsData;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/demo241106/articles")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("") // 다건조회
    public RsData<ArticlesResponse> list() {
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        Article article = new Article("첫 번째 게시글", "내용입니다.", "작성자");
        articleDTOS.add(new ArticleDTO(article));

        Article article2 = new Article("두 번째 게시글", "내용입니다.2", "작성자2");
        articleDTOS.add(new ArticleDTO(article2));

        Article article3 = new Article("세 번째 게시글", "내용입니다.3", "작성자3");
        articleDTOS.add(new ArticleDTO(article3));

        return RsData.of("200","게시글 다건 조회 성공", new ArticlesResponse(articleDTOS));
    }


    @GetExchange("/{id}") //단건조회
    public RsData<ArticleResponse> getArticle(@PathVariable("id") Long id){
        Article article = new Article("첫 번째 게시글","내용입니다.","작성자");
        ArticleDTO articleDTO = new ArticleDTO(article);

        return RsData.of("200","게시글 단건 조회 성공", new ArticleResponse(articleDTO));
    }

    @PostMapping("") // 생성
    public String create(@Valid @RequestBody ArticleCreateRequest articleCreateRequest){

        System.out.println(articleCreateRequest.getSubject());
        System.out.println(articleCreateRequest.getContent());
        System.out.println(articleCreateRequest.getAuthor());

        return "등록완료";
    }

    @PatchMapping("/{id}") // 수정
    public String modify(@PathVariable("id") Long id, @Valid @RequestBody ArticleModifyRequest articleModifyRequest){

        System.out.println(id);
        System.out.println(articleModifyRequest.getSubject());
        System.out.println(articleModifyRequest.getContent());
        System.out.println(articleModifyRequest.getAuthor());

        return "수정";
    }

    @DeleteMapping("/{id}")  // 샂게
    public String delete(@PathVariable("id") Long id){
        System.out.println(id);

        return "삭제";
    }
}