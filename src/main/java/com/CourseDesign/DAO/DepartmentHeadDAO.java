package com.CourseDesign.DAO;

public interface DepartmentHeadDAO {
    public void approveOrderForm(String id,String opinionString) throws Exception;
    public void approveOrderFormNot(String id,String opinionString) throws Exception;
}
