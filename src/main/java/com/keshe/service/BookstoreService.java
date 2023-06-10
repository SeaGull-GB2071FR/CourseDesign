package com.keshe.service;

import com.keshe.DAO.Bookstore;
import com.keshe.DAO.impl.BookstoreImpl;
import com.keshe.domain.OrderForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookstoreService {
    static Bookstore bookstore;

    public void Service() throws Exception{
        bookstore = new BookstoreImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.println("订购商的权限如下:");
        System.out.println("1, 订购单按出版社组织");
        System.out.println("2, 生成订购单");
        System.out.println("3, 退出");
        int i = 0;

        do {
            i = scanner.nextInt();
            switch (i) {// 如果没选到功能就循环到选到功能为止
                case 1:
                    System.out.println("输入你想查询的出版社");
                    String s = scanner.next();
                    List<OrderForm> orderFormsByPublisher = bookstore.getOrderFormsByPublisher(s);
                    System.out.println("按出版社查询的结果如下:");
                    for (Object o :
                            orderFormsByPublisher) {
                        System.out.println(o);
                    }
                    break;
                case 2:
                    List<OrderForm> orderFormsList = new ArrayList();
                    List<OrderForm> form = new OrderFormsListPut().ADD(orderFormsList);

                    bookstore.generatePurchaseOrder(form);
                    break;
                case 3:
                    System.out.println("退出订购商权限");
                    break;
                default:
                    System.out.println("输入错误");
                    break;
            }
        } while (i != 1 && i != 2 && i != 3);

    }
}
