package com.lucky.dao;

import com.lucky.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by lucky on 1/9/17.
 */
public interface ArticleDao extends CrudRepository<Article, Integer>, PagingAndSortingRepository<Article, Integer> {
    public Iterable<Article> findAllByOrderByIdDesc();
    public Page<Article> findAllByOrderByIdDesc(Pageable pageable);
}