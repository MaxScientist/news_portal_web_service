package com.example.newsportal_webservice.service;

import com.example.newsportal_webservice.entity.News;

import java.util.List;

public interface NewsService {

    void update(long id, News updateNewsData);
    void save(News news);
    void delete(long id);
    News findById(long id);
    List<News> findAll();
}
