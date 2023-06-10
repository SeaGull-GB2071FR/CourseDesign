package com.CourseDesign.DAO.impl;

import com.CourseDesign.DAO.TextbookDepartmentDAO;
import com.CourseDesign.Util.GetConnUtil;
import com.CourseDesign.domain.OrderForm;
import com.CourseDesign.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;


@SuppressWarnings("all")
// 教材科类
public class TextbookDepartmentDAOImpl extends User implements TextbookDepartmentDAO {
    public TextbookDepartmentDAOImpl(String username, String password, String name) {
        super(username, password, name);
    }

    public TextbookDepartmentDAOImpl() {
    }

    public List<OrderForm> getOrderFormsByDepartment(String department) throws Exception {
        // 按专业系查询订购表
        Connection connection = GetConnUtil.getConnect();
        QueryRunner queryRunner = new QueryRunner();

        String sql = "select * from order_forms where department like ?";
        String departmentPattern = "%" + department + "%"; // 添加通配符%，实现模糊匹配
        List<OrderForm> orderFormList = queryRunner.query(connection, sql, new BeanListHandler<OrderForm>(OrderForm.class), departmentPattern);

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

    @Override
    public void approveOrderFormsNot(List<Integer> id) throws Exception {
        Connection connection = GetConnUtil.getConnect();
        QueryRunner queryRunner = new QueryRunner();


        String sql = "update order_forms set is_approved_by_department_head = 0 where id = ?";
        for (Integer ID : id) {
            queryRunner.update(connection, sql, ID);
            sql = "update approval_opinions set is_approved = 0 where id = ?";

            queryRunner.update(connection, sql, ID);
        }

        System.out.println("批量修改完毕");
    }
}