package com.keshe.service;

import com.keshe.DAO.TextbookDepartment;
import com.keshe.DAO.impl.TextbookDepartmentImpl;
import com.keshe.domain.OrderForm;

import java.util.List;
import java.util.Scanner;

public class TextbookDepartmentService {

    static TextbookDepartment textbookDepartment;


    public void Service(String s) throws Exception {
        textbookDepartment = new TextbookDepartmentImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.println("教材科的权限如下:");
        System.out.println("1, 按专业系查询订购表");
        System.out.println("2, 批量审核订购表");
        System.out.println("3, 退出");
        int i = 0;

        do {
            i = Integer.parseInt(s);
            switch (i) {
                case 1:
                    System.out.println("请输入你想要查询的专业系");
                    List<OrderForm> orderFormsByDepartment = textbookDepartment.getOrderFormsByDepartment(new Scanner(System.in).next());
                    System.out.println("查询结果如下:");
                    for (Object o :
                            orderFormsByDepartment) {
                        System.out.println(o);
                    }
                    break;
                case 2:
                    //批量审核
                    List<Integer> addId = new TextbookApproveListAdd().Add();
                    textbookDepartment.approveOrderForms(addId);
                    break;
                case 3:
                    System.out.println("退出教材科权限");
                    break;
                default:
                    System.out.println("输入错误");
                    break;
            }

        } while (i != 1 && i != 2 && i != 3);
    }
}

