package com.CourseDesign.DAO.impl;

import com.CourseDesign.DAO.BookstoreDAO;
import com.CourseDesign.Util.GetConnUtil;
import com.CourseDesign.domain.OrderForm;
import com.CourseDesign.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

// 订购商类
public class BookstoreDAOImpl extends User implements BookstoreDAO {
    public BookstoreDAOImpl(String username, String password, String name) {
        super(username, password, name);
    }

    public BookstoreDAOImpl() {
    }

    @Override
    public List<OrderForm> getOrderFormsByPublisher(String publisher) throws  Exception{ //按出版社查询
        Connection connection = GetConnUtil.getConnect();
        QueryRunner queryRunner = new QueryRunner();

        String sql = "select * from order_forms where publisher like ?";
        String publisherPattern = "%" + publisher + "%"; // 添加通配符%，实现模糊匹配
        List<OrderForm> query = queryRunner.query(connection, sql,
                new BeanListHandler<OrderForm>(OrderForm.class), publisherPattern);

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