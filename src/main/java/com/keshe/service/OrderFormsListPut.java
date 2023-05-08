package com.keshe.service;

import com.keshe.Util.GetConnUtil;
import com.keshe.domain.OrderForm;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class OrderFormsListPut {

    public List<OrderForm> ADD(List<OrderForm> orderFormsList) throws Exception {
        OrderForm orderForm = new OrderForm("","","","",0);
        orderFormsList.add(orderForm);//为了让后面的索引与id符合，让list 的0 索引为空
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);

        Connection connection = GetConnUtil.getConnect();
        QueryRunner queryRunner = new QueryRunner();

        String sql = "select * from order_forms";
        List<OrderForm> list = queryRunner.query(connection, sql, new BeanListHandler<OrderForm>(OrderForm.class));
        for (Object o :
                list) {
            System.out.println(o);
        }

        while (flag){
            System.out.println("请输入想要打印订购单的ID :");
            orderFormsList.add(list.get(scanner.nextInt()));
            System.out.println("是否继续，是(true)，否(false)");
            flag = scanner.nextBoolean();
        }
        System.out.println("退出成功");

        return orderFormsList;
    }
}
