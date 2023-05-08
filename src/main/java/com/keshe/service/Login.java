package com.keshe.service;//package com.keshe.service;
//
//import com.keshe.DAO.Bookstore;
//import com.keshe.DAO.DepartmentHead;
//import com.keshe.DAO.Teacher;
//import com.keshe.DAO.TextbookDepartment;
//import com.keshe.DAO.impl.BookstoreImpl;
//import com.keshe.DAO.impl.DepartmentHeadImpl;
//import com.keshe.DAO.impl.TeacherImpl;
//import com.keshe.DAO.impl.TextbookDepartmentImpl;
//import com.keshe.Util.GetConnUtil;
//import com.keshe.domain.Approval_Opinions;
//import com.keshe.domain.OrderForm;
//import com.keshe.domain.User;
//import org.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.handlers.BeanHandler;
//
//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Login {
//    static Teacher teacher;
//
//    static Bookstore bookstore;
//
//    static DepartmentHead departmentHead;
//
//    static TextbookDepartment textbookDepartment;
//
//    @SuppressWarnings("all")
//    public void login() throws Exception {
//        Connection connection = GetConnUtil.getConnect();
//        QueryRunner queryRunner = new QueryRunner();
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("请输入username,password,name:");
//        User user = new User(scanner.next(), scanner.next(), scanner.next());
//        String sql = "select id,type from users where username = ? and password = ? and name = ?";
//
//        user = queryRunner.query(connection, sql, new BeanHandler<>(User.class),
//                user.getUsername(), user.getPassword(), user.getName());
//
//        if (user == null) {
//            System.out.println("非本校职务人员");
//            return;
//        }
//
//
//        if (user.getType().equals("teacher")) {
////            new TeacherService().Service(user);
////            OrderForm orderForm = null;
////            teacher = new TeacherImpl();
////
////            System.out.println("教师职位的权限如下:");
////            System.out.println("1, 填写教材订购表");
////            System.out.println("2, 维护教材订购表");
////            System.out.println("3, 退出");
////            int i = 0;
//
////            do {// 如果没选到功能就循环到选到功能为止
////                i = scanner.nextInt();
////                switch (i) {
////                    case 1:
////                        System.out.println("请输入department(专业系), course, textbookName, publisher, quantity 的格式");
////                        orderForm = new OrderForm(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.nextInt());
////                        orderForm = teacher.submitOrderForm(orderForm);
////                        Integer id = new Approval_Opinions_TextBook_id().GetTextbookId(orderForm);
//////                    写提交后在Approval_Opinions中的id
////                        sql = "insert into approval_opinion values(0, ?, ?, 0)";
//////                        在approval＿opinions中的id
////                        queryRunner.insert(connection, sql, new BeanHandler<Approval_Opinions>(Approval_Opinions.class), id, user.getId());
////                        break;
////                    case 2:
////                        System.out.println("请输入department(专业系), course, textbookName, publisher, quantity 的格式");
////                        orderForm = new OrderForm(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.nextInt());
////                        orderForm = teacher.updateOrderForm(orderForm);
//////                    写维护后的申请;
////                        id = new Approval_Opinions_TextBook_id().GetTextbookId(orderForm);
////                        sql = "insert into approval_opinion values(0, ?, ?, 0)";
////                        queryRunner.insert(connection, sql, new BeanHandler<Approval_Opinions>(Approval_Opinions.class), id, user.getId());
////                        break;
////
////                    case 3:
////                        System.out.println("退出教师权限");
////                        break;
////
////                    default:
////                        System.out.println("输入错误");
////                        break;
////                }
////            } while (i != 1 && i != 2 && i != 3);
//        } else if (user.getType().equals("departmentHead")) {
////            departmentHead = new DepartmentHeadImpl();
////            departmentHead.approveOrderForm();
////            系主任审批后需要修改order_forms里面的记录，显示已经通过
////            sql = "update order_forms set is_approved_by_department_head = ? where id = ?";
//
//        } else if (user.getType().equals("bookstore")) {
////            new BookstoreService().Service();
////            bookstore = new BookstoreImpl();
////
////            System.out.println("订购商的权限如下:");
////            System.out.println("1, 订购单按出版社组织");
////            System.out.println("2, 生成订购单");
////            System.out.println("3, 退出");
////            int i = 0;
////
////            do {
////                i = scanner.nextInt();
////                switch (i) {// 如果没选到功能就循环到选到功能为止
////                    case 1:
////                        System.out.println("输入你想查询的出版社");
////                        String s = scanner.next();
////                        List<OrderForm> orderFormsByPublisher = bookstore.getOrderFormsByPublisher(s);
////                        System.out.println("按出版社查询的结果如下:");
////                        for (Object o :
////                                orderFormsByPublisher) {
////                            System.out.println(o);
////                        }
////                        break;
////                    case 2:
////                        List<OrderForm> orderFormsList = new ArrayList<>();
////                        List<OrderForm> form = new OrderFormsListPut().ADD(orderFormsList);
////                        bookstore.generatePurchaseOrder(form);
////                        break;
////                    case 3:
////                        System.out.println("退出订购商权限");
////                        break;
////                    default:
////                        System.out.println("输入错误");
////                        break;
////                }
////            } while (i != 1 && i != 2 && i != 3);
//        } else if (user.getType().equals("textbookDepartment")) {
////            new TextbookDepartmentService().Service();
////            textbookDepartment = new TextbookDepartmentImpl();
////            System.out.println("教材科的权限如下:");
////            System.out.println("1, 按专业系查询订购表");
////            System.out.println("2, 批量审核订购表");
////            System.out.println("3, 退出");
////            int i = 0;
////
////            do {
////                i = scanner.nextInt();
////                switch (i) {
////                    case 1:
////                        System.out.println("请输入你想要查询的专业系");
////                        List<OrderForm> orderFormsByDepartment = textbookDepartment.getOrderFormsByDepartment(new Scanner(System.in).next());
////                        System.out.println("查询结果如下:");
////                        for (Object o :
////                                orderFormsByDepartment) {
////                            System.out.println(o);
////                        }
////                        break;
////                    case 2:
////                        //批量审核
////                        List<Integer> addId = new TextbookApproveListAdd().Add();
////                        textbookDepartment.approveOrderForms(addId);
////                        break;
////                    case 3:
////                        System.out.println("退出教材科权限");
////                        break;
////                    default:
////                        System.out.println("输入错误");
////                        break;
////                }
////
////            } while (i != 1 && i != 2 && i != 3);
//        }
//
//        GetConnUtil.close(null, null, connection);
//    }
//}
//
