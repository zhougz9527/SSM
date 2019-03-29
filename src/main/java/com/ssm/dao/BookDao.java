package com.ssm.dao;

import com.ssm.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Think
 * @Date: 2019/3/18
 * @Time: 14:51
 */
public interface BookDao {

    /**
     * 通过Id查询单本图书
     * @param id
     * @return
     */
    Book queryById(long id);

    /**
     * 查询所有图书
     * @param offset
     * @param limit
     * @return
     */
    List<Book> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 减少馆藏数量
     * @param bookId
     * @return
     */
    int reduceNumber(long bookId);


}
