package com.example.demo241106.domain.article.repository;

import com.example.demo241106.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Long , Article> {
}
