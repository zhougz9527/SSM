package com.ssm.dao;

import com.ssm.entity.Appointment;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Think
 * @Date: 2019/3/18
 * @Time: 14:51
 */
public interface AppointmentDao {

    /**
     * 插入预约图书记录
     * @param bookId
     * @param studentId
     * @return
     */
    int insertAppointment(@Param("bookId") long bookId, @Param("studentId") long studentId);

    /**
     * 通过主键查询预约图书记录, 并且携带图书实体
     * @param bookId
     * @param studentId
     * @return
     */
    Appointment queryByKeyWithBook(@Param("bookId") long bookId, @Param("studentId") long studentId);

}
