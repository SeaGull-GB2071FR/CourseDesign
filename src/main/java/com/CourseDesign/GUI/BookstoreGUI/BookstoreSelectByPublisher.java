package com.CourseDesign.GUI.BookstoreGUI;

import com.CourseDesign.DAO.TeacherDAO;
import com.CourseDesign.domain.OrderForm;
import com.CourseDesign.DAO.BookstoreDAO;
import com.CourseDesign.DAO.impl.BookstoreDAOImpl;
import com.CourseDesign.Util.GetConnUtil;
import com.CourseDesign.service.Impl.OrderFormSelectImpl;
import com.CourseDesign.service.OrderFormSelect;
import com.mysql.jdbc.StringUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

@SuppressWarnings("all")
public class BookstoreSelectByPublisher extends JFrame implements ActionListener {
    private BookstoreDAO bookstoreDAO;

    private Integer id;

    private JButton commitButton;

    private JTextField DepartmentJTextField;

    private JLabel DepartmentJLabel;
    private TeacherDAO teacherDAO;

    private Integer TeacherId;
    private OrderFormSelect orderFormSelect;

    DefaultTableModel tableModel;

    JTable table;

    public BookstoreSelectByPublisher(Integer id) throws Exception {
        this.id = id;

        orderFormSelect = new OrderFormSelectImpl();
        setTitle("生成订购单功能");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 420);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel fieldsPanel = new JPanel(new GridLayout(4, 3));

        DepartmentJLabel = new JLabel("出版社:");
        DepartmentJTextField = new JTextField(20);
        fieldsPanel.add(DepartmentJLabel);
        fieldsPanel.add(DepartmentJTextField);


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
        bookstoreDAO = new BookstoreDAOImpl();
        orderFormSelect = new OrderFormSelectImpl();
        try {
            if (e.getActionCommand() == "提交") {
                Connection connection = GetConnUtil.getConnect();
                QueryRunner queryRunner = new QueryRunner();

                String DepartmentString = DepartmentJTextField.getText();

                if (!StringUtils.isNullOrEmpty(DepartmentString)) {

                    List<OrderForm> OrderFormlist = bookstoreDAO.getOrderFormsByPublisher(DepartmentString);
                    DepartmentJTextField.setText("");

                    // 刷新表格
                    tableModel.setRowCount(0);
                    for (OrderForm o : OrderFormlist) {
                        tableModel.addRow(new Object[]{o.getId(), o.getDepartment(), o.getCourse(), o.getTextbook_name(), o.getPublisher(), o.getQuantity()});
                    }
                }else{
                    List<OrderForm> OrderFormlist = orderFormSelect.Select();

                    DepartmentJTextField.setText("");

                    // 刷新表格
                    tableModel.setRowCount(0);
                    for (OrderForm o : OrderFormlist) {
                        tableModel.addRow(new Object[]{o.getId(), o.getDepartment(), o.getCourse(), o.getTextbook_name(), o.getPublisher(), o.getQuantity()});
                    }
                }
            }


        } catch (Exception ex) {
            ex.getMessage();
        }
    }



}
