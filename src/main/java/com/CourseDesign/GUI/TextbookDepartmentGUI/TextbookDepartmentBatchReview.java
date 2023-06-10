package com.CourseDesign.GUI.TextbookDepartmentGUI;

import com.CourseDesign.DAO.TextbookDepartmentDAO;
import com.CourseDesign.DAO.impl.TextbookDepartmentDAOImpl;
import com.CourseDesign.domain.Approval_Opinions;
import com.CourseDesign.service.Impl.TextbookSelectImpl;
import com.CourseDesign.service.TextbookSelect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class TextbookDepartmentBatchReview extends JFrame implements ActionListener {
    private Integer TextbookDepartmentId;

    private JButton ReviewButton;

    private TextbookDepartmentDAO textbookDepartmentDAO;

    private JLabel ReviewLabel, IdLabel;

    private JTextField ReviewField, IdField;

    private TextbookSelect textbookSelect;

    DefaultTableModel tableModel;

    JTable table;

    public TextbookDepartmentBatchReview(Integer TextbookDepartmentId) throws Exception {
        this.TextbookDepartmentId = TextbookDepartmentId;
        textbookSelect = new TextbookSelectImpl();

        setTitle("教材科批量审核功能");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 420);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel fieldsPanel = new JPanel();

        ReviewLabel = new JLabel("审核");
        ReviewField = new JTextField(20);
        fieldsPanel.add(ReviewLabel);
        fieldsPanel.add(ReviewField);

        ReviewButton = new JButton("批量审核");
        ReviewButton.addActionListener(this);
        fieldsPanel.add(ReviewButton);
        panel.add(fieldsPanel, BorderLayout.SOUTH);

        List<Approval_Opinions> list = textbookSelect.Select();

        String[] columnNames = {"id", "教材Id", "教材名", "提交者Id", "是否审核通过"};
        String[][] tableValues = new String[list.size()][6];
        int i = 0;
        for (Approval_Opinions o : list) {
            tableValues[i][0] = String.valueOf(o.getId());
            tableValues[i][1] = String.valueOf(o.getOrder_form_id());
            tableValues[i][2] = String.valueOf(o.getTextbook_name());
            tableValues[i][3] = String.valueOf(o.getDepartment_head_id());
            tableValues[i][4] = String.valueOf(o.getIs_approved());

            i++;
        }

        tableModel = new DefaultTableModel(columnNames, 0);
        for (Approval_Opinions o : list) {
            tableModel.addRow(new Object[]{o.getId(), o.getOrder_form_id(), o.getTextbook_name(), o.getDepartment_head_id(), o.getIs_approved()});
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
        textbookDepartmentDAO = new TextbookDepartmentDAOImpl();
        textbookSelect = new TextbookSelectImpl();
        int[] selectedRows = table.getSelectedRows();
        List<Integer> selectlist = toList(selectedRows);
        try {
            if (e.getActionCommand() == "批量审核") {
                String ReviewString = ReviewField.getText();
                if (ReviewString.equals("通过")) {
                    textbookDepartmentDAO.approveOrderForms(selectlist);
                    tableModel.setRowCount(0);
                    List<Approval_Opinions> list = textbookSelect.Select();
                    for (Approval_Opinions o : list) {
                        tableModel.addRow(new Object[]{o.getId(), o.getOrder_form_id(), o.getTextbook_name(), o.getDepartment_head_id(), o.getIs_approved()});
                    }
                    JOptionPane.showMessageDialog(null,  "审核通过");

                } else if (ReviewString.equals("不通过")) {
//                    textbookDepartment.approveOrderFormNot(IdString);
                    textbookDepartmentDAO.approveOrderFormsNot(selectlist);
                    tableModel.setRowCount(0);
                    List<Approval_Opinions> list = textbookSelect.Select();
                    for (Approval_Opinions o : list) {
                        tableModel.addRow(new Object[]{o.getId(), o.getOrder_form_id(), o.getTextbook_name(), o.getDepartment_head_id(), o.getIs_approved()});
                    }
                    JOptionPane.showMessageDialog(null,  "审核不通过");
                } else {
                    JOptionPane.showMessageDialog(null, "请输入”通过“ 或者”不通过“");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    private static List<Integer> toList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int num : array) {
            list.add(num);
        }
        return list;
    }
}
