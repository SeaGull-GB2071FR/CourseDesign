package com.CourseDesign.service.Impl;

import com.CourseDesign.Util.GetConnUtil;
import com.CourseDesign.domain.Approval_Opinions;
import com.CourseDesign.service.TextbookSelect;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class TextbookSelectImpl implements TextbookSelect {
    @Override
    @SuppressWarnings("all")
    public List<Approval_Opinions> Select() throws Exception {
        Connection connection = GetConnUtil.getConnect();
        QueryRunner queryRunner = new QueryRunner();

        String sql = "select t1.id,t1.order_form_id, t2.textbook_name,t1.department_head_id,t1.is_approved " +
                "from approval_opinions t1 " +
                "join order_forms t2 on t1.department_head_id = t2.id ";

        List<Approval_Opinions> list = queryRunner.query(connection, sql, new BeanListHandler<Approval_Opinions>(Approval_Opinions.class));

        GetConnUtil.close(null, null, connection);
        return list;
    }
}
