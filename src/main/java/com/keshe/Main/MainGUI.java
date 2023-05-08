package com.keshe.Main;

import com.keshe.GUI.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("all")
public class MainGUI extends JPanel {
    private JPanel currentPanel;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;

    public MainGUI() {
        super(new BorderLayout());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.add(new JLabel("panel 1"));
        cardPanel.add(panel1, "panel1");

        panel2 = new JPanel();
        panel2.setBackground(Color.WHITE);
        panel2.add(new JLabel("Teacher"));
        cardPanel.add(panel2, "panel2");

        panel3 = new JPanel();
        panel3.setBackground(Color.WHITE);
        panel3.add(new JLabel("DepartmentHead"));
        cardPanel.add(panel3, "panel3");

        panel4 = new JPanel();
        panel4.setBackground(Color.WHITE);
        panel4.add(new JLabel("Bookstore"));
        cardPanel.add(panel4, "panel4");

        panel5 = new JPanel();
        panel5.setBackground(Color.WHITE);
        panel5.add(new JLabel("TextbookDepartment"));
        cardPanel.add(panel5, "panel5");

        add(cardPanel, BorderLayout.CENTER);

        button1 = new JButton("panel 1");
        button1.addActionListener(new ActionListener() {
            LoginGUI loginGUI = null;

            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "panel1");
                loginGUI = new LoginGUI();
                add(loginGUI, BorderLayout.NORTH);
                cardPanel.add(loginGUI, "panel1");

            }
        });


        button2 = new JButton("Teacher");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                cardLayout.show(new TeacherGUI(), "panel2");
                cardLayout.show(cardPanel, "panel2");
                // add TeacherGUI to MainGUI and then add it to cardPanel
                TeacherGUI teacherGUI = new TeacherGUI();
                add(teacherGUI, BorderLayout.NORTH);
                cardPanel.add(teacherGUI, "panel2");
                //跳转到TeacherGUI的功能
            }

        });

        button3 = new JButton("DepartmentHead");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "panel3");
                DepartmentHeadGUI departmentHeadGUI = new DepartmentHeadGUI();
                add(departmentHeadGUI, BorderLayout.NORTH);
                cardPanel.add(departmentHeadGUI, "panel3");
            }
        });

        button4 = new JButton("Bookstore");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "panel4");
                BookstoreGUI bookstoreGUI = new BookstoreGUI();
                add(bookstoreGUI, BorderLayout.NORTH);
                cardPanel.add(bookstoreGUI, "panel4");
            }
        });

        button5 = new JButton("TextbookDepartment");
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "panel5");
                TextbookDepartmentGUI textbookDepartmentGUI = new TextbookDepartmentGUI();
                add(textbookDepartmentGUI, BorderLayout.NORTH);
                cardPanel.add(textbookDepartmentGUI, "panel5");
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("教材订购管理系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainGUI mainGUI = new MainGUI();
        frame.getContentPane().add(mainGUI);

        frame.pack();
        frame.setVisible(true);
    }

        public static void main(String[] args) {
        createAndShowGUI();
    }

}

/**
 * 这段代码使用了CardLayout布局管理器来实现在多个面板之间的切换。
 * 可以看到，每个面板都被添加到了cardPanel中，并通过一个字符串标识符来唯一地识别它们。
 * 当用户点击按钮时，通过cardLayout的show()方法来显示相应的面板。这样就可以实现多个面板之间的切换了。
 */