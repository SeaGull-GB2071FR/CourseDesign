package com.CourseDesign.GUI.TeacherGUI;

import com.CourseDesign.DAO.TeacherDAO;
import com.CourseDesign.DAO.impl.TeacherDAOImpl;
import com.CourseDesign.domain.Approval_Opinions;
import com.CourseDesign.domain.OrderForm;
import com.CourseDesign.service.Approval_Opinions_TextBook_id;
import com.CourseDesign.service.Impl.Approval_Opinions_TextBook_idImpl;
import com.CourseDesign.Util.GetConnUtil;
import com.CourseDesign.service.Impl.OrderFormSelectImpl;
import com.CourseDesign.service.OrderFormSelect;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

@SuppressWarnings("all")
public class TeacherUpdate extends JFrame implements ActionListener {

    private JButton commitButton;

    private JTextField DepartmentJTextField, CourseJTextField, TextbookNameJTextField, PublisherJTextField, QuantityJTextField, ApprovedIDField;

    private JLabel DepartmentJLabel, CourseJLabel, TextbookNameJLabel, PublisherJLabel, QuantityJLabel, ApprovedIdJLabel;
    private TeacherDAO teacherDAO;
    private Approval_Opinions_TextBook_id approvalOpinionsTextBookId;

    private Integer TeacherId;

    DefaultTableModel tableModel;

    private OrderFormSelect orderFormSelect;

    JTable table;

    public TeacherUpdate(Integer TeacherId) throws Exception {
        this.TeacherId = TeacherId;
        orderFormSelect = new OrderFormSelectImpl();


        setTitle("教师维护功能");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 420);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel fieldsPanel = new JPanel(new GridLayout(7, 2));

        DepartmentJLabel = new JLabel("专业:");
        DepartmentJTextField = new JTextField(20);
        fieldsPanel.add(DepartmentJLabel);
        fieldsPanel.add(DepartmentJTextField);

        CourseJLabel = new JLabel("课程:");
        CourseJTextField = new JTextField(20);
        fieldsPanel.add(CourseJLabel);
        fieldsPanel.add(CourseJTextField);

        TextbookNameJLabel = new JLabel("教材名:");
        TextbookNameJTextField = new JTextField(20);
        fieldsPanel.add(TextbookNameJLabel);
        fieldsPanel.add(TextbookNameJTextField);

        PublisherJLabel = new JLabel("出版社:");
        PublisherJTextField = new JTextField(20);
        fieldsPanel.add(PublisherJLabel);
        fieldsPanel.add(PublisherJTextField);

        QuantityJLabel = new JLabel("数量:");
        QuantityJTextField = new JTextField(20);
        fieldsPanel.add(QuantityJLabel);
        fieldsPanel.add(QuantityJTextField);

        ApprovedIdJLabel = new JLabel("修改ID");
        ApprovedIDField = new JTextField(20);
        fieldsPanel.add(ApprovedIdJLabel);
        fieldsPanel.add(ApprovedIDField);

        commitButton = new JButton("提交");
        commitButton.addActionListener(this);
        fieldsPanel.add(commitButton);

        panel.add(fieldsPanel, BorderLayout.SOUTH);


        List<OrderForm> list = orderFormSelect.Select();

        String[] columnNames = {"id", "专业", "课程", "教材名", "出版社", "数量"};
        String[][] tableValues = new String[list.size()][6];
        int i = 0;
        for (OrderForm o : list) {
            tableValues[i][0] = String.valueOf(o.getId());
            tableValues[i][1] = o.getDepartment();
            tableValues[i][2] = o.getCourse();
            tableValues[i][3] = o.getTextbook_name();
            tableValues[i][4] = o.getPublisher();
            tableValues[i][5] = String.valueOf(o.getQuantity());
            i++;
        }

        tableModel = new DefaultTableModel(columnNames, 0);
        for (OrderForm o : list) {
            tableModel.addRow(new Object[]{o.getId(), o.getDepartment(), o.getCourse(), o.getTextbook_name(), o.getPublisher(), o.getQuantity()});
        }

        table = new JTable(tableModel); // 使用新创建的数据模型
        JScrollPane scrollPane = new JScrollPane(table);

        // 设置表格大小
        table.setPreferredScrollableViewportSize(new Dimension(600, 250));

        panel.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(panel);
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        teacherDAO = new TeacherDAOImpl();
        orderFormSelect = new OrderFormSelectImpl();
        approvalOpinionsTextBookId = new Approval_Opinions_TextBook_idImpl();
        try {
            if (e.getActionCommand() == "提交") {
                Connection connection = GetConnUtil.getConnect();
                QueryRunner queryRunner = new QueryRunner();
                OrderForm orderForm = new OrderForm(DepartmentJTextField.getText(), CourseJTextField.getText()
                        , TextbookNameJTextField.getText(), PublisherJTextField.getText()
                        , Integer.parseInt(QuantityJTextField.getText()));
                teacherDAO.updateOrderForm(orderForm, Integer.parseInt(ApprovedIDField.getText()));
                Integer id = approvalOpinionsTextBookId.GetTextbookId(orderForm);

                String sql = "insert into approval_opinions values(0, ?, ?, 0,'无')";
                queryRunner.insert(connection, sql, new BeanHandler<Approval_Opinions>(Approval_Opinions.class), id, TeacherId);

                DepartmentJTextField.setText("");
                CourseJTextField.setText("");
                TextbookNameJTextField.setText("");
                PublisherJTextField.setText("");
                QuantityJTextField.setText("");
                ApprovedIDField.setText("");


                // 刷新表格
                tableModel.setRowCount(0);
                List<OrderForm> list = orderFormSelect.Select();
                for (OrderForm o : list) {
                    tableModel.addRow(new Object[]{o.getId(), o.getDepartment(), o.getCourse(),
                            o.getTextbook_name(), o.getPublisher(), o.getQuantity()});
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


//    public List<OrderForm> Select() throws Exception {
//        Connection connection = GetConnUtil.getConnect();
//        QueryRunner queryRunner = new QueryRunner();
//
//        String sql = "select * from order_forms";
//
//        List<OrderForm> list = queryRunner.query(connection, sql, new BeanListHandler<OrderForm>(OrderForm.class));
//
//        GetConnUtil.close(null, null, connection);
//
//        return list;
//    }


}
