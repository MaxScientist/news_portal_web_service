package com.example.newsportal_webservice.dao;

import com.example.newsportal_webservice.entity.News;

import java.util.List;

public interface NewsDAO {
    void update(long id, News updateData);
    void save(News news);
    void delete(long id);
    void deleteBunchOf();
    News findById(long id);
    List<News> findAll();
}
