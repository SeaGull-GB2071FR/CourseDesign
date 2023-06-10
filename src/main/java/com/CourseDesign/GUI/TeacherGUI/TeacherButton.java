package com.CourseDesign.GUI.TeacherGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("all")
public class TeacherButton extends JFrame implements ActionListener {
    JButton InsertButton, UpdateButton, QuitButton;

    JFrame frame;

    private Integer id;

    public TeacherButton(Integer id) {
        this.id = id;
        frame = new JFrame("教师功能选择");
        InsertButton = new JButton("填写");
        UpdateButton = new JButton("维护");
        QuitButton = new JButton("退出");

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(InsertButton);
        panel.add(UpdateButton);
        panel.add(QuitButton);

        frame.add(panel);

        InsertButton.addActionListener(this);
        UpdateButton.addActionListener(this);
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
        TeacherInsert teacherInsert;
        TeacherUpdate teacherUpdate;
        if (e.getActionCommand() == "填写") {
            teacherInsert = new TeacherInsert(id);
        } else if (e.getActionCommand() == "维护") {
            try {
                teacherUpdate = new TeacherUpdate(id);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            frame.dispose(); // 关闭当前页面
        }
    }
}
