package com.keshe.DAO;

import com.keshe.domain.OrderForm;

import java.util.List;

public interface Bookstore {
    public List<OrderForm> getOrderFormsByPublisher(String publisher) throws Exception;
    public void generatePurchaseOrder(List<OrderForm> orderForms) throws Exception;
}
