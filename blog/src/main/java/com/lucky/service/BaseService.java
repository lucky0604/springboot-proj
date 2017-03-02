package com.lucky.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lucky on 3/1/17.
 */
public interface BaseService<T extends Serializable> {
    void add(T t) throws Exception;

    /**
     * 集合查询
     *
     * @param pageNum 页码
     * @param pageSize 每页的查询数量
     * @return
     */
    List<T> findAll(int pageNum, int pageSize);

    T findOneById(Serializable id);
}
