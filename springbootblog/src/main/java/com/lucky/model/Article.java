package com.lucky.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by lucky on 1/9/17.
 */
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String content;

    private LocalDate publishDay;
    private LocalDateTime lastChange;

    @ManyToOne
    private User author;

    private char status;


    public Article() {
    }

    public Article(String title, String content, User author) {
        this.status = 'n';
        this.title = title;
        this.content = content;
        this.author = author;
        this.publishDay = LocalDate.now();
        this.lastChange = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getPublishDay() {
        return publishDay;
    }

    public void setPublishDay(LocalDate publishDay) {
        this.publishDay = publishDay;
    }

    public LocalDateTime getLastChange() {
        return lastChange;
    }

    public void setLastChange(LocalDateTime lastChange) {
        this.lastChange = lastChange;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
