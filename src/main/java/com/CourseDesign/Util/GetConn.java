package com.CourseDesign.Util;

import java.sql.Connection;

public class GetConn {
    public void getConn() throws Exception{
        Connection connection = GetConnUtil.getConnect();
    }
}
