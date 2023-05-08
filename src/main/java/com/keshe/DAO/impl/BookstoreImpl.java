package com.keshe.DAO.impl;

import com.keshe.DAO.Bookstore;
import com.keshe.Util.GetConnUtil;
import com.keshe.domain.OrderForm;
import com.keshe.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

// 订购商类
public class BookstoreImpl extends User implements Bookstore {
    public BookstoreImpl(String username, String password, String name) {
        super(username, password, name);
    }

    public BookstoreImpl() {
    }

    @Override
    public List<OrderForm> getOrderFormsByPublisher(String publisher) throws  Exception{ //按出版社查询
        Connection connection = GetConnUtil.getConnect();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from order_forms where publisher = ?";
        List<OrderForm> query = queryRunner.query(connection, sql, new BeanListHandler<OrderForm>(OrderForm.class), publisher);
        // 按出版社查询订购表
        GetConnUtil.close(null, null, connection);

        return query;

    }
    @Override
    public void generatePurchaseOrder(List<OrderForm> orderForms) throws Exception{  // 输入想购买的单子
        // 生成订购单
        System.out.println("生成订购单");
        for (Object o: orderForms) {
            System.out.println(o);
        }
    }
}