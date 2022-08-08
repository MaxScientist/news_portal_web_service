package com.example.newsportal_webservice.dao.impl;

import com.example.newsportal_webservice.dao.NewsDAO;
import com.example.newsportal_webservice.entity.News;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
public class NewsDAOImpl implements NewsDAO {

    @PersistenceContext(unitName = "NewsPersistenceUnit")
    private EntityManager entityManager;

    @Override
    public void update(long id, News updateData) {
        News news = entityManager.find(News.class, id);
        news.setContentOfNews(updateData.getContentOfNews());
        news.setShortDescription(updateData.getShortDescription());
        news.setTitle(updateData.getTitle());
        entityManager.merge(news);
    }

    @Override
    @Transactional
    public void save(News news) {
        entityManager.persist(news);
        entityManager.flush();

    }

    @Override
    @Transactional
    public void delete(long id) {
        News news = entityManager.find(News.class, id);
        entityManager.remove(news);
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public void deleteBunchOf() {

    }

    @Override
    public News findById(long id) {
        return entityManager.find(News.class, id);
    }

    @Override
    public List<News> findAll() {
        List<News> newsList = entityManager.createQuery("from News", News.class).getResultList();

        return newsList;
    }
}
