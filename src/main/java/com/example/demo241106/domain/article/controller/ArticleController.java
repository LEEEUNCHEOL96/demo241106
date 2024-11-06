package com.example.demo241106.domain.article.controller;

import com.example.demo241106.domain.article.dto.ArticleDTO;
import com.example.demo241106.domain.article.entity.Article;
import com.example.demo241106.domain.article.service.ArticleService;
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
    public List<ArticleDTO> list() {
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        Article article = new Article("첫 번째 게시글", "내용입니다.", "작성자");
        articleDTOS.add(new ArticleDTO(article));

        Article article2 = new Article("두 번째 게시글", "내용입니다.2", "작성자2");
        articleDTOS.add(new ArticleDTO(article2));

        Article article3 = new Article("세 번째 게시글", "내용입니다.3", "작성자3");
        articleDTOS.add(new ArticleDTO(article3));

        return articleDTOS;
    }

    @GetExchange("/{id}") //단건조회
    public ArticleDTO getArticle(@PathVariable("id") Long id){
        Article article = new Article("첫 번째 게시글","내용입니다.","작성자");
        ArticleDTO articleDTO = new ArticleDTO(article);

        return articleDTO;
    }

    @PostMapping("") // 생성
    public String create(@RequestParam("subject") String subject,
                         @RequestParam("content") String content,
                         @RequestParam("author") String author){

        System.out.println(subject);
        System.out.println(content);
        System.out.println(author);

        return "등록";
    }

    @PatchMapping("/{id}") // 수정
    public String modify(@PathVariable("id") Long id,
                         @RequestParam("subject") String subject,
                         @RequestParam("content") String content,
                         @RequestParam("author") String author){

        System.out.println(id);
        System.out.println(subject);
        System.out.println(content);
        System.out.println(author);

        return "수정";
    }

    @DeleteMapping("/{id}")  // 샂게
    public String delete(@PathVariable("id") Long id){
        System.out.println(id);

        return "삭제";
    }
}