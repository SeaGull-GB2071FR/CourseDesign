package com.CourseDesign.GUI.TextbookDepartmentGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextbookDepartmentButton extends JFrame implements ActionListener {

    JButton SelectButton, BatchReviewButton,QuitButton;

    JFrame frame;

    private Integer id;

    public TextbookDepartmentButton(Integer id) {
        this.id = id;

        frame = new JFrame("教材科功能选择");
        SelectButton = new JButton("按专业查询");
        BatchReviewButton = new JButton("批量审核");
        QuitButton = new JButton("退出");

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(SelectButton);
        panel.add(BatchReviewButton);
        panel.add(QuitButton);

        frame.add(panel);

        SelectButton.addActionListener(this);
        BatchReviewButton.addActionListener(this);
        QuitButton.addActionListener(this);

        frame.setTitle("教材订购管理系统");
        frame.setSize(200, 200);
        frame.setLocation(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TextbookDepartmentSelect textbookDepartmentSelect;
        TextbookDepartmentBatchReview textbookDepartmentBatchReview;
        try {
            if (e.getActionCommand() == "按专业查询") {

                textbookDepartmentSelect = new TextbookDepartmentSelect(id);

            } else if (e.getActionCommand() == "批量审核") {
                textbookDepartmentBatchReview = new TextbookDepartmentBatchReview(id);
            } else {
                frame.dispose(); // 关闭当前页面
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new TextbookDepartmentButton(1);
    }
}
