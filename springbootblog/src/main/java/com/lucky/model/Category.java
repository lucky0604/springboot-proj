package com.lucky.model;

import javax.persistence.*;

/**
 * Created by lucky on 1/9/17.
 */
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String categoryname;

    public Category() {
    }

    public Category(String categoryname) {
        this.categoryname = categoryname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
}
