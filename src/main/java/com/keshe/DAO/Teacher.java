package com.keshe.DAO;

import com.keshe.domain.OrderForm;

import java.sql.SQLException;

public interface Teacher {

    public OrderForm submitOrderForm(OrderForm orderForm) throws SQLException, Exception;
    public OrderForm updateOrderForm(OrderForm orderForm,Integer integer) throws Exception;
}
