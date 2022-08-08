package com.example.newsportal_webservice.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class News implements Serializable {

//    @Id
//    @Column(name = "ID")
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
//    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
//    private long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(length = 150, unique = true)
    private String title;

    @Column(length = 750, name = "CONTENT_OF_NEWS")
    private String contentOfNews;

    @Column(length = 750, name = "SHORT_DESCRIPTION")
    private String shortDescription;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentOfNews() {
        return contentOfNews;
    }

    public void setContentOfNews(String content) {
        this.contentOfNews = content;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
