package com.CourseDesign.service.Impl;

import com.CourseDesign.Util.GetConnUtil;
import com.CourseDesign.domain.OrderForm;
import com.CourseDesign.service.OrderFormSelect;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class OrderFormSelectImpl implements OrderFormSelect {
    @Override
    public List<OrderForm> Select() throws Exception {
        Connection connection = GetConnUtil.getConnect();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from order_forms";

        List<OrderForm> list = queryRunner.query(connection, sql, new BeanListHandler<OrderForm>(OrderForm.class));
        GetConnUtil.close(null, null, connection);

        return list;
    }
}
