package com.keshe.Util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@SuppressWarnings("all")
public class GetConnUtil {


    private static DataSource ds;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src\\druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 编写getConnection 的方法
    public static Connection getConnect() throws SQLException {
        return ds.getConnection();
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection) throws Exception {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close(); // 把引用断掉，把使用完的连接放回连接池
            // 其他的connection.close 是断掉连接
        }
    }


}
