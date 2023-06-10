package com.CourseDesign.service.Impl;

import com.CourseDesign.Util.GetConnUtil;
import com.CourseDesign.domain.OrderForm;
import com.CourseDesign.service.Approval_Opinions_TextBook_id;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;

public class Approval_Opinions_TextBook_idImpl implements Approval_Opinions_TextBook_id {

    //获取提交后获取ID
    public Integer GetTextbookId(OrderForm orderForm) throws Exception{
        Connection connection = GetConnUtil.getConnect();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from order_forms where department = ? and course = ? and textbook_name = ? and publisher = ?";
        OrderForm query = queryRunner.query(connection, sql, new BeanHandler<OrderForm>(OrderForm.class),
                orderForm.getDepartment(), orderForm.getCourse(), orderForm.getTextbook_name(), orderForm.getPublisher());
        return query.getId();
    }
}
