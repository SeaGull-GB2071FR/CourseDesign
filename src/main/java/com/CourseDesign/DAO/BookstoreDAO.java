package com.CourseDesign.DAO;

import com.CourseDesign.domain.OrderForm;

import java.util.List;

public interface BookstoreDAO {
    public List<OrderForm> getOrderFormsByPublisher(String publisher) throws Exception;
    public void generatePurchaseOrder(List<OrderForm> orderForms) throws Exception;
}
