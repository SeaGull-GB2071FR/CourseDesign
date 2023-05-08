package com.keshe.service;

import com.keshe.Util.GetConnUtil;
import com.keshe.domain.OrderForm;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextbookApproveListAdd {
    public List<Integer> Add() throws SQLException {
        ArrayList<Integer> TextBookID = new ArrayList<>();
        Connection connection = GetConnUtil.getConnect();
        QueryRunner queryRunner = new QueryRunner();
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;


        String sql = "select * from order_forms where is_approved_by_department_head = 0";
        List<OrderForm> orderForm = queryRunner.query(connection, sql, new BeanListHandler<OrderForm>(OrderForm.class));
        for (Object o :
                orderForm) {
            System.out.println(o);
        }

        do{
            System.out.println("输入你想改的记录:");
            TextBookID.add(scanner.nextInt());
//            出现过.InputMismatchException异常
            System.out.println("是否想继续输入: 是(true),否(false)");
            flag = scanner.nextBoolean();
        }while (flag);


        return TextBookID;
    }
}
