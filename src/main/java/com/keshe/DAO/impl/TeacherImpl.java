package com.keshe.DAO.impl;

import com.keshe.DAO.Teacher;
import com.keshe.Util.GetConnUtil;
import com.keshe.domain.OrderForm;
import com.keshe.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;

@SuppressWarnings("all")
public class TeacherImpl extends User implements Teacher {
    public TeacherImpl(String username, String password, String name) {
        super(username, password, name);
    }

    public TeacherImpl() {
    }
//    教师：填写/维护教材订购表（教务系统上有下载可参考），提交后不能删除

    public OrderForm submitOrderForm(OrderForm orderForm) throws Exception {
        Connection connection = GetConnUtil.getConnect();
        QueryRunner queryRunner = new QueryRunner();
        // 提交订购表
        String sql = "insert into order_forms values(0 , ? , ? , ? , ? , ? , 0)";
        queryRunner.insert(connection, sql, new BeanHandler<OrderForm>(OrderForm.class),
                orderForm.getDepartment(), orderForm.getCourse(),
                orderForm.getTextbook_name(), orderForm.getPublisher(), orderForm.getQuantity());

        GetConnUtil.close(null, null, connection);
        return orderForm;

    }

    public OrderForm updateOrderForm(OrderForm orderForm,Integer integer) throws Exception {
        Connection connection = GetConnUtil.getConnect();
        QueryRunner queryRunner = new QueryRunner();
        // 更新订购表
        String sql = "update order_forms set department = ? , course = ? , textbook_name = ? , publisher = ? , quantity = ?  , is_approved_by_department_head = ? where id = ?";
        int id = integer;
        int affectedRow = queryRunner.update(connection, sql, orderForm.getDepartment(), orderForm.getCourse(), orderForm.getTextbook_name(),
                orderForm.getPublisher(), orderForm.getQuantity(), 0, id);
        System.out.println(affectedRow > 0 ? "成功" : "失败");

        GetConnUtil.close(null, null, connection);
        return orderForm;
    }

}