package com.keshe.DAO;

import com.keshe.domain.OrderForm;

import java.util.List;

public interface TextbookDepartment {
    public List<OrderForm> getOrderFormsByDepartment(String department) throws Exception;
    public void approveOrderForms(List<Integer> id) throws Exception;
}
