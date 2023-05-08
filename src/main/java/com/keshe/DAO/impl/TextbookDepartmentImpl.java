package com.keshe.DAO.impl;

import com.keshe.DAO.TextbookDepartment;
import com.keshe.Util.GetConnUtil;
import com.keshe.domain.OrderForm;
import com.keshe.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;


@SuppressWarnings("all")
// 教材科类
public class TextbookDepartmentImpl extends User implements TextbookDepartment {
    public TextbookDepartmentImpl(String username, String password, String name) {
        super(username, password, name);
    }

    public TextbookDepartmentImpl() {
    }

    public List<OrderForm> getOrderFormsByDepartment(String department) throws Exception {
        // 按专业系查询订购表
        Connection connection = GetConnUtil.getConnect();
        QueryRunner queryRunner = new QueryRunner();

        String sql = "select * from order_forms where department = ?";
        List<OrderForm> orderFormList = queryRunner.query(connection, sql, new BeanListHandler<OrderForm>(OrderForm.class), department);

        return orderFormList;
    }

    public void approveOrderForms(List<Integer> id) throws Exception {
        // 批量审核订购表
        Connection connection = GetConnUtil.getConnect();
        QueryRunner queryRunner = new QueryRunner();


        String sql = "update order_forms set is_approved_by_department_head = 1 where id = ?";
        for (Integer ID : id) {
            queryRunner.update(connection, sql, ID);
            sql = "update approval_opinions set is_approved = 1 where id = ?";

            queryRunner.update(connection, sql, ID);
        }

        System.out.println("批量修改完毕");

    }
}