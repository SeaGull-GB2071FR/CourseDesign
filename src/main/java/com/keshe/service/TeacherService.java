package com.keshe.service;

import com.keshe.DAO.Teacher;
import com.keshe.DAO.impl.TeacherImpl;
import com.keshe.GUI.TeacherGUI;
import com.keshe.Util.GetConnUtil;
import com.keshe.domain.Approval_Opinions;
import com.keshe.domain.OrderForm;
import com.keshe.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.util.Scanner;

public class TeacherService {
//    public void Service(OrderForm orderForm, TeacherImpl teacher, User user) throws Exception {
//    static OrderForm orderForm;

    static Teacher teacher;
@SuppressWarnings("all")
    public void Service(User user,OrderForm orderForm,Integer integer) throws Exception {
        TeacherGUI teacherGUI = new TeacherGUI();
        Connection connection = GetConnUtil.getConnect();
        QueryRunner queryRunner = new QueryRunner();
        teacher = new TeacherImpl();
        String sql = "";

        Scanner scanner = new Scanner(System.in);

        System.out.println("教师职位的权限如下:");
        System.out.println("1, 填写教材订购表");
        System.out.println("2, 维护教材订购表");
        System.out.println("3, 退出");

        int i = integer;

        do {// 如果没选到功能就循环到选到功能为止

            switch (i) {
                case 1:
//                    System.out.println("请输入department(专业系), course, textbookName, publisher, quantity 的格式");
//                    orderForm = new OrderForm(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.nextInt());
                    orderForm = teacher.submitOrderForm(orderForm);
                    Integer id = new Approval_Opinions_TextBook_id().GetTextbookId(orderForm);
//                    写提交后在Approval_Opinions中的id
                    sql = "insert into approval_opinions values(0, ?, ?, 0)";
                    queryRunner.insert(connection, sql, new BeanHandler<Approval_Opinions>(Approval_Opinions.class), id, user.getId());
                    break;
                case 2:
//                    System.out.println("请输入department(专业系), course, textbookName, publisher, quantity 的格式");
//                    orderForm = new OrderForm(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.nextInt());
//                    orderForm = teacher.updateOrderForm(orderForm);
//                    写维护后的申请;
                    id = new Approval_Opinions_TextBook_id().GetTextbookId(orderForm);
                    sql = "insert into approval_opinions values(0, ?, ?, 0)";
                    queryRunner.insert(connection, sql, new BeanHandler<Approval_Opinions>(Approval_Opinions.class), id, user.getId());
                    break;

                case 3:
                    System.out.println("退出教师权限");
                    break;

                default:
                    System.out.println("输入错误");
                    break;
            }
        } while (i != 1 && i != 2 && i != 3);
    }
}
