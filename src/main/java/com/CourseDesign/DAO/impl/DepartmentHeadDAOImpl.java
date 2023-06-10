package com.CourseDesign.DAO.impl;

import com.CourseDesign.DAO.DepartmentHeadDAO;
import com.CourseDesign.Util.GetConnUtil;
import com.CourseDesign.domain.Approval_Opinions;
import com.CourseDesign.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

// 系主任类
public class DepartmentHeadDAOImpl extends User implements DepartmentHeadDAO {
    public DepartmentHeadDAOImpl(String username, String password, String name) {
        super(username, password, name);
    }

    public DepartmentHeadDAOImpl() {
    }

    @SuppressWarnings("all")
    public void approveOrderForm(String idString, String opinionString) throws Exception {

//        DepartmentHeadGUI departmentHeadGUI = new DepartmentHeadGUI();
//        departmentHeadGUI.textbookTextArea.append("111");
//        系主任审核各教师提交的订购表，签署审核意见，粘贴：通过、不通过
        // 审核订购表
//        思路，查询is_approved == 0
        boolean flag = false;
        if (opinionString.equals(""))
            opinionString = "无";
        Scanner scanner = new Scanner(System.in);
        Connection connection = GetConnUtil.getConnect();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from approval_opinions where is_approved = ?";

        List<Approval_Opinions> opinions = queryRunner.query(connection, sql,
                new BeanListHandler<Approval_Opinions>(Approval_Opinions.class), 0);

        sql = "update approval_opinions set is_approved = 1 ,opinion = ? where id = ?";
        int id = Integer.parseInt(idString);

        queryRunner.update(connection, sql, opinionString, id);

        //系主任审批后需要修改order_forms里面的记录，显示已经通过
        sql = "update order_forms set is_approved_by_department_head = ? where id = ?";
        queryRunner.update(connection, sql, 1, id);
        System.out.println("id = " + id + "已经审批审批完毕\n");


        GetConnUtil.close(null, null, connection);
    }

    @Override
    @SuppressWarnings("all")
    public void approveOrderFormNot(String idString, String opinionString) throws Exception {
        boolean flag = false;
        if (opinionString.equals(""))
            opinionString = "无";
        Scanner scanner = new Scanner(System.in);
        Connection connection = GetConnUtil.getConnect();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from approval_opinions where is_approved = ?";
        List<Approval_Opinions> opinions = queryRunner.query(connection, sql,
                new BeanListHandler<Approval_Opinions>(Approval_Opinions.class), 1);

        sql = "update approval_opinions set is_approved = 0 , opinion = ? where id = ?";
        int id = Integer.parseInt(idString);

        queryRunner.update(connection, sql, opinionString, id);


        //系主任审批后需要修改order_forms里面的记录，显示已经通过
        sql = "update order_forms set is_approved_by_department_head = ? where id = ?";
        queryRunner.update(connection, sql, 0, id);
        System.out.println("id = " + id + "已经审批审批完毕\n");


        GetConnUtil.close(null, null, connection);
    }
}