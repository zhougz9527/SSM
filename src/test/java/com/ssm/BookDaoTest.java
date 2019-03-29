package com.ssm;

import com.ssm.dao.BookDao;
import com.ssm.entity.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Think
 * @Date: 2019/3/18
 * @Time: 15:49
 */
public class BookDaoTest extends BaseTest {

    @Autowired
    private BookDao bookDao;


    @Test
    public void testQueryById() throws Exception {
        long bookId = 1000;
        Book book = bookDao.queryById(bookId);
        System.out.println("bookId=" + book.getBookId() + ", name=" + book.getName() + ", number=" + 10);
    }

}
