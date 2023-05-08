package com.keshe.DAO.impl;

import com.keshe.DAO.DepartmentHead;
import com.keshe.Util.GetConnUtil;
import com.keshe.domain.Approval_Opinions;
import com.keshe.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

// 系主任类
public class DepartmentHeadImpl extends User implements DepartmentHead {
    public DepartmentHeadImpl(String username, String password, String name) {
        super(username, password, name);
    }

    public DepartmentHeadImpl() {
    }

    @SuppressWarnings("all")
    public void approveOrderForm(String s) throws Exception {

//        DepartmentHeadGUI departmentHeadGUI = new DepartmentHeadGUI();
//        departmentHeadGUI.textbookTextArea.append("111");
//        系主任审核各教师提交的订购表，签署审核意见，粘贴：通过、不通过
        // 审核订购表
//        思路，查询is_approved == 0
        boolean flag = false;
        Scanner scanner = new Scanner(System.in);
        Connection connection = GetConnUtil.getConnect();
        QueryRunner queryRunner = new QueryRunner();

        String sql = "select * from approval_opinions where is_approved = ?";
        List<Approval_Opinions> opinions = queryRunner.query(connection, sql, new BeanListHandler<Approval_Opinions>(Approval_Opinions.class), 0);
        if (opinions.size() != 0) {

            sql = "update approval_opinions set is_approved = 1 where id = ?";

            if (opinions.size() == 0) {
                System.out.println("需要审批的记录已经为0了，无需审批咯~");
            } else {
                System.out.println("请输入您想审批的记录ID:");
                int id = Integer.parseInt(s);
                queryRunner.update(connection, sql, id);
                //更新opinions 里面的数据，让他在进入if时 opinions.size()改变
                sql = "select * from approval_opinions where is_approved = ?";
                opinions = queryRunner.query(connection, sql, new BeanListHandler<Approval_Opinions>(Approval_Opinions.class), 0);

                //系主任审批后需要修改order_forms里面的记录，显示已经通过
                sql = "update order_forms set is_approved_by_department_head = ? where id = ?";
                queryRunner.update(connection, sql, 1, id);
                System.out.println("id = " + id + "已经审批审批完毕\n");

            }


        } else {
            System.out.println("暂时没有需要审批的记录噢~");
        }
        GetConnUtil.close(null, null, connection);
    }
}