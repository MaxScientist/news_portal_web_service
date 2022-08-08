package com.example.newsportal_webservice.service.impl;


import com.example.newsportal_webservice.dao.NewsDAO;
import com.example.newsportal_webservice.entity.News;
import com.example.newsportal_webservice.service.NewsService;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

//@Dependent
//@Stateless
//@EJB(beanInterface = NewsService.class, name = "NewsService")
//@LocalBean
public class NewsServiceImpl implements NewsService {

    @Inject
//    @EJB
    private NewsDAO newsDAO;

    @Override
    public void update(long id, News updateNewsData) {
        newsDAO.update(id, updateNewsData);
    }

    @Override
    public void save(News news) {
        newsDAO.save(news);
    }

    @Override
    public void delete(long id) {
        newsDAO.delete(id);
    }

    @Override
    public News findById(long id) {
        return newsDAO.findById(id);
    }

    @Override
    public List<News> findAll() {
        return newsDAO.findAll();
    }
}
