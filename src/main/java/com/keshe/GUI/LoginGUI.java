package com.keshe.GUI;

import com.keshe.Util.GetConnUtil;
import com.keshe.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

@SuppressWarnings("all")
public class LoginGUI extends JPanel implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;


    public LoginGUI() {
        super(new BorderLayout());
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        frame = new JFrame("Login System");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.add(new JLabel("Teacher"));
        cardPanel.add(panel1, "panel1");

        panel2 = new JPanel();
        panel2.setBackground(Color.WHITE);
        panel2.add(new JLabel("DepartmentHead"));
        cardPanel.add(panel2, "panel2");

        panel3 = new JPanel();
        panel3.setBackground(Color.WHITE);
        panel3.add(new JLabel("Bookstore"));
        cardPanel.add(panel3, "panel3");

        panel4 = new JPanel();
        panel4.setBackground(Color.WHITE);
        panel4.add(new JLabel("TextbookDepartment"));
        cardPanel.add(panel4, "panel4");

        button1 = new JButton("Teacher");
        button2 = new JButton("DepartmentHead");
        button3 = new JButton("Bookstore");
        button4 = new JButton("TextbookDepartment");


        usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {//先实现登录
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Connection connection = GetConnUtil.getConnect();
            QueryRunner queryRunner = new QueryRunner();

            String sql = "select * from users where username = ? and PASSWORD = ?";
            User user = queryRunner.query(connection, sql, new BeanHandler<User>(User.class), username, password);
            // 检查用户名和密码是否正确
            if (user == null) {
                JOptionPane.showMessageDialog(null, "Login failed. Please try again.");
            }
//            if (username.equals(user.getUsername()) && password.equals(user.getPassword()))
            else {
                JOptionPane.showMessageDialog(null, "Login success!" + " 您的身份为 " + user.getType());

//                if (user.getType().equals("teacher")) {
//                    button1.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
////              cardLayout.show(new TeacherGUI(), "panel2");
//                            cardLayout.show(cardPanel, "panel1");
////              add TeacherGUI to MainGUI and then add it to cardPanel
//                            TeacherGUI teacherGUI = new TeacherGUI();
//                            add(teacherGUI, BorderLayout.NORTH);
//                            cardPanel.add(teacherGUI, "panel1");
//                            //跳转到TeacherGUI的功能
//                        }
//
//                    });
//                } else if (user.getType().equals("DepartmentHead")) {
//                    button2.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            cardLayout.show(cardPanel, "panel2");
//                        }
//                    });
//                } else if (user.getType().equals("Bookstore")) {
//                    button3.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            cardLayout.show(cardPanel, "panel3");
//                        }
//                    });
//                } else if (user.getType().equals("TextbookDepartment")) {
//                    button4.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            cardLayout.show(cardPanel, "panel4");
//                        }
//                    });
//                }

            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
