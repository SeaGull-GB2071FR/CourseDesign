package com.CourseDesign.DAO;

import com.CourseDesign.domain.OrderForm;

import java.sql.SQLException;

public interface TeacherDAO {

    public OrderForm submitOrderForm(OrderForm orderForm) throws SQLException, Exception;
    public OrderForm updateOrderForm(OrderForm orderForm, Integer integer) throws Exception;
}
