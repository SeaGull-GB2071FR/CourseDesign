package com.CourseDesign.GUI;


import com.keshe.Util.GetConnUtil;
import com.keshe.domain.User;
import com.CourseDesign.GUI.BookstoreGUI.BookstoreButton;
import com.CourseDesign.GUI.DepartmentHeadGUI.DepartmentHeadButton;
import com.CourseDesign.GUI.TeacherGUI.TeacherButton;
import com.CourseDesign.GUI.TextbookDepartmentGUI.TextbookDepartmentButton;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginGUI extends JFrame implements ActionListener {

    //定义组件
    JButton loginButton, resetButton, outButton;
    JRadioButton TeacherJrb, DepartmentHeadJrb, BookstoreJrb, TextbookDepartmentJrb;
    JPanel jPanel1, jPanel2, jPanel3, jPanel4;
    JTextField usernameField;
    JLabel usernameLabel, passwordLabel, permissionsLabel;
    JPasswordField passwordField;
    ButtonGroup bg;


    public static void main(String[] args) {

        LoginGUI loginGUI = new LoginGUI();
    }

    public LoginGUI() {
        //创建组件
        loginButton = new JButton("登录");
        resetButton = new JButton("重置");
        outButton = new JButton("退出");

        //设置监听
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        outButton.addActionListener(this);

        TeacherJrb = new JRadioButton("教师");
        DepartmentHeadJrb = new JRadioButton("系主任");
        BookstoreJrb = new JRadioButton("订购商");
        TextbookDepartmentJrb = new JRadioButton("教材科");
        bg = new ButtonGroup();
        bg.add(TeacherJrb);
        bg.add(DepartmentHeadJrb);
        bg.add(BookstoreJrb);
        bg.add(TextbookDepartmentJrb);
        TeacherJrb.setSelected(true);  //初始页面默认选择权限为 教师

        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();
        jPanel4 = new JPanel();

        usernameLabel = new JLabel("用户名：");
        passwordLabel = new JLabel("密    码：");
        permissionsLabel = new JLabel("权    限：");

        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);

        String ImgPath = "com/CourseDesign/img/1.png";


        //加入到JPanel中
        jPanel1.add(usernameLabel);
        jPanel1.add(usernameField);

        jPanel2.add(passwordLabel);
        jPanel2.add(passwordField);

        jPanel3.add(permissionsLabel);    //添加标签
        jPanel3.add(TeacherJrb);
        jPanel3.add(DepartmentHeadJrb);
        jPanel3.add(BookstoreJrb);
        jPanel3.add(TextbookDepartmentJrb);

        jPanel4.add(loginButton);        //添加按钮
        jPanel4.add(resetButton);
        jPanel4.add(outButton);

        //加入JFrame中
        this.add(jPanel1);
        this.add(jPanel2);
        this.add(jPanel3);
        this.add(jPanel4);


        this.setLayout(new GridLayout(4, 1));            //选择GridLayout布局管理器
        this.setTitle("教材订购管理系统");
        this.setSize(400, 200);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出
        this.setVisible(true);
        this.setResizable(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (e.getActionCommand() == "重置") {
            clear();
        } else if (e.getActionCommand() == "登录") {

            try {
                Connection connection = GetConnUtil.getConnect();
                QueryRunner queryRunner = new QueryRunner();

                String sql = "select * from users where username = ? and PASSWORD = ?";
                User user = queryRunner.query(connection, sql, new BeanHandler<User>(User.class), username, password);
                // 检查用户名和密码是否正确
                if (user == null) {
                    JOptionPane.showMessageDialog(null, "登陆失败,请重新尝试.");
                    clear();
                } else {
                    if (TeacherJrb.isSelected() && user.getType().equals("teacher")) {
                        JOptionPane.showMessageDialog(null, "登陆成功!");
                        TeacherButton teacherButton = new TeacherButton(user.getId());
                    } else if (DepartmentHeadJrb.isSelected() && user.getType().equals("departmentHead")) {
                        JOptionPane.showMessageDialog(null, "登陆成功!");
                        DepartmentHeadButton departmentHeadButton = new DepartmentHeadButton(user.getId());
                    } else if (BookstoreJrb.isSelected() && user.getType().equals("bookstore")) {
                        JOptionPane.showMessageDialog(null, "登陆成功!");
                        BookstoreButton bookStoreButton = new BookstoreButton(user.getId());
                    } else if (TextbookDepartmentJrb.isSelected() && user.getType().equals("textbookDepartment")) {
                        JOptionPane.showMessageDialog(null, "登陆成功!");
                        TextbookDepartmentButton textbookDepartmentButton = new TextbookDepartmentButton(user.getId());
                    } else {
                        JOptionPane.showMessageDialog(null, "登陆失败!" + " 您的身份为 " + user.getType() + "请重新选择职业");
                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            System.exit(0);
        }
    }


    public void clear() {
        usernameField.setText("");
        passwordField.setText("");
    }


}
