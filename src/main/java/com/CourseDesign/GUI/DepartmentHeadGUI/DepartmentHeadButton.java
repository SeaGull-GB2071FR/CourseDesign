package com.CourseDesign.GUI.DepartmentHeadGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepartmentHeadButton extends JFrame implements ActionListener {
    JButton ReviewButton, QuitButton;

    JFrame frame;

    private Integer DepartmentHeadId;

    public DepartmentHeadButton(Integer id) {
        this.DepartmentHeadId = id;

        frame = new JFrame("系主任功能选择");
        ReviewButton = new JButton("审核");
        QuitButton = new JButton("退出");

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(ReviewButton);
        panel.add(QuitButton);

        frame.add(panel);

        ReviewButton.addActionListener(this);
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
        DepartmentHeadReview departmentHeadReview;


        if (e.getActionCommand() == "审核") {
            try {
                departmentHeadReview = new DepartmentHeadReview(DepartmentHeadId);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            frame.dispose(); // 关闭当前页面
        }
    }
}
