package com.CourseDesign.DAO;

import com.CourseDesign.domain.OrderForm;

import java.util.List;

public interface TextbookDepartmentDAO {
    public List<OrderForm> getOrderFormsByDepartment(String department) throws Exception;
    public void approveOrderForms(List<Integer> id) throws Exception;
    public void approveOrderFormsNot(List<Integer> id) throws Exception;
}
