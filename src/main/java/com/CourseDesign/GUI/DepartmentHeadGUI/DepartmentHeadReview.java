package com.CourseDesign.GUI.DepartmentHeadGUI;

import com.CourseDesign.DAO.DepartmentHeadDAO;
import com.CourseDesign.DAO.impl.DepartmentHeadDAOImpl;
import com.CourseDesign.domain.Approval_Opinions;
import com.CourseDesign.service.DepartmentHeadSelect;
import com.CourseDesign.service.Impl.DepartmentHeadSelectImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@SuppressWarnings("all")
public class DepartmentHeadReview extends JFrame implements ActionListener {
    private Integer DepartmentHeadId;

    private JButton commitButton;

    private JLabel ReviewLabel, IdLabel, OpinionLabel;

    private JTextField ReviewField, IdField, OpinionField;

    DefaultTableModel tableModel;

    JTable table;

    DepartmentHeadDAO departmentHeadDAO;

    private DepartmentHeadSelect departmentHeadSelect;


    public DepartmentHeadReview(Integer departmentHeadId) throws Exception {
        this.DepartmentHeadId = departmentHeadId;
        departmentHeadSelect = new DepartmentHeadSelectImpl();

        setTitle("系主任审核功能");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 420);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel fieldsPanel = new JPanel();

        IdLabel = new JLabel("Id");
        IdField = new JTextField(10);
        fieldsPanel.add(IdLabel);
        fieldsPanel.add(IdField);

        ReviewLabel = new JLabel("审核:");
        ReviewField = new JTextField(10);
        fieldsPanel.add(ReviewLabel);
        fieldsPanel.add(ReviewField);

        OpinionLabel = new JLabel("意见");
        OpinionField = new JTextField(10);
        fieldsPanel.add(OpinionLabel);
        fieldsPanel.add(OpinionField);

        commitButton = new JButton("提交");
        commitButton.addActionListener(this);
        fieldsPanel.add(commitButton);
        panel.add(fieldsPanel, BorderLayout.SOUTH);

        List<Approval_Opinions> list = departmentHeadSelect.Select();


        String[] columnNames = {"id", "教材Id", "教材名", "提交者", "是否审核通过", "意见"};
        String[][] tableValues = new String[list.size()][7];
        int i = 0;
        for (Approval_Opinions o : list) {
            tableValues[i][0] = String.valueOf(o.getId());
            tableValues[i][1] = String.valueOf(o.getOrder_form_id());
            tableValues[i][2] = o.getTextbook_name();
            tableValues[i][3] = departmentHeadSelect.SelectName(o.getDepartment_head_id());
            tableValues[i][4] = String.valueOf(o.getIs_approved());
            tableValues[i][5] = o.getOpinion();

            i++;
        }

        tableModel = new DefaultTableModel(columnNames, 0);
        for (Approval_Opinions o : list) {
            tableModel.addRow(new Object[]{o.getId(), o.getOrder_form_id(), o.getTextbook_name(), departmentHeadSelect.SelectName(o.getDepartment_head_id()), o.getIs_approved(), o.getOpinion()});
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
        String sql = "";
        departmentHeadDAO = new DepartmentHeadDAOImpl();
        departmentHeadSelect = new DepartmentHeadSelectImpl();
        try {
            if (e.getActionCommand() == "提交") {
                String ReviewString = ReviewField.getText();
                String IdString = IdField.getText();
                String opinionString = OpinionField.getText();
                if (ReviewString.equals("通过")) {
                    departmentHeadDAO.approveOrderForm(IdString, opinionString);
                    tableModel.setRowCount(0);
                    List<Approval_Opinions> list = departmentHeadSelect.Select();
                    for (Approval_Opinions o : list) {
                        tableModel.addRow(new Object[]{o.getId(), o.getOrder_form_id(),
                                o.getTextbook_name(), o.getDepartment_head_id(), o.getIs_approved(), o.getOpinion()});
                    }
                    JOptionPane.showMessageDialog(null, IdString + "审核通过");

                } else if (ReviewString.equals("不通过")) {
                    departmentHeadDAO.approveOrderFormNot(IdString, opinionString);
                    tableModel.setRowCount(0);
                    List<Approval_Opinions> list = departmentHeadSelect.Select();
                    for (Approval_Opinions o : list) {
                        tableModel.addRow(new Object[]{o.getId(), o.getOrder_form_id(),
                                o.getTextbook_name(), o.getDepartment_head_id(),
                                o.getIs_approved(), o.getOpinion()});
                    }
                    JOptionPane.showMessageDialog(null, IdString + "审核不通过");
                } else {
                    JOptionPane.showMessageDialog(null, "请输入”通过“ 或者”不通过“");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
