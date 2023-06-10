package com.CourseDesign.GUI.TextbookDepartmentGUI;

import com.CourseDesign.DAO.TextbookDepartmentDAO;
import com.CourseDesign.DAO.impl.TextbookDepartmentDAOImpl;
import com.CourseDesign.domain.OrderForm;
import com.CourseDesign.service.Impl.OrderFormSelectImpl;
import com.CourseDesign.service.OrderFormSelect;
import com.mysql.jdbc.StringUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@SuppressWarnings("all")
public class TextbookDepartmentSelect extends JFrame implements ActionListener {
    private JLabel DepartmentLabel;

    private JTextField DepartmentField;

    private Integer TextbookDepartmentId;

    private JButton commitButton;

    private TextbookDepartmentDAO textbookDepartmentDAO;

    private OrderFormSelect orderFormSelect;

    DefaultTableModel tableModel;

    JTable table;

    public TextbookDepartmentSelect(Integer TextbookDepartmentId) throws Exception {
        this.TextbookDepartmentId = TextbookDepartmentId;
        orderFormSelect = new OrderFormSelectImpl();

        setTitle("教材科按专业系查询订购表功能");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 420);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel fieldsPanel = new JPanel();

        DepartmentLabel = new JLabel("专业");
        DepartmentField = new JTextField(20);
        fieldsPanel.add(DepartmentLabel);
        fieldsPanel.add(DepartmentField);


        commitButton = new JButton("查询");
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
        textbookDepartmentDAO = new TextbookDepartmentDAOImpl();
        orderFormSelect = new OrderFormSelectImpl();
        String departmentString = DepartmentField.getText();
        try {
            if (e.getActionCommand() == "查询") {
                if (!StringUtils.isNullOrEmpty(departmentString)) {
                    List<OrderForm> list = textbookDepartmentDAO.getOrderFormsByDepartment(departmentString);

                    DepartmentField.setText("");

                    // 刷新表格
                    tableModel.setRowCount(0);
                    for (OrderForm o : list) {
                        tableModel.addRow(new Object[]{o.getId(), o.getDepartment(), o.getCourse(), o.getTextbook_name(), o.getPublisher(), o.getQuantity()});
                    }
                } else {
                    List<OrderForm> list = orderFormSelect.Select();

                    tableModel.setRowCount(0);
                    for (OrderForm o : list) {
                        tableModel.addRow(new Object[]{o.getId(), o.getDepartment(), o.getCourse(),
                                o.getTextbook_name(), o.getPublisher(), o.getQuantity()});
                    }
                }
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }


}
