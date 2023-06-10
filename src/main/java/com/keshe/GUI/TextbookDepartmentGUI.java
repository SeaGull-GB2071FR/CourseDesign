package com.keshe.GUI;

import com.keshe.DAO.TextbookDepartment;
import com.keshe.DAO.impl.TextbookDepartmentImpl;
import com.keshe.Util.GetConnUtil;
import com.keshe.domain.Approval_Opinions;
import com.keshe.domain.OrderForm;
import com.keshe.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class TextbookDepartmentGUI extends JPanel implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JLabel usernameLabel, passwordLabel, TextbookList, chooseJLabel, answerJLabel;
    private JButton loginButton;
    private JTextField chooseField, answerField;
    private JTextArea jTextArea;

    static TextbookDepartment textbookDepartment = new TextbookDepartmentImpl();

    public TextbookDepartmentGUI() {

        JFrame frame = new JFrame("TextbookDepartment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        frame.setSize(1400, 400);
        JPanel panel = new JPanel();
        frame.add(panel);

        TextbookList = new JLabel("TextbookList:");
        panel.add(TextbookList);
        jTextArea = new JTextArea(10, 130);
        panel.add(jTextArea);

        chooseJLabel = new JLabel("chooseJLabel");
        chooseField = new JTextField(10);
        panel.add(chooseJLabel);
        panel.add(chooseField);

        answerJLabel = new JLabel("输入专业或者ID :");
        answerField = new JTextField(10);
        panel.add(answerJLabel);
        panel.add(answerField);


        usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(loginButton);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        User user = null;

        try {
            Connection connection = GetConnUtil.getConnect();
            QueryRunner queryRunner = new QueryRunner();
            String s = chooseField.getText();

            String sql = "select * from users where username = ? and PASSWORD = ?";
            user = queryRunner.query(connection, sql, new BeanHandler<User>(User.class), username, password);
            // 检查用户名和密码是否正确
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                JOptionPane.showMessageDialog(null, "Login success!" + " 您的身份为 " + user.getType());
                if (s.equals("")) {
                    jTextArea.append("请输入功能 \n");
                    jTextArea.append("1, 按专业系查询订购表\n");
                    jTextArea.append("2, 审核订购表\n");

                } else if (s.equals("1")) {
                    if (answerField.getText().equals(""))
                        jTextArea.append("请输入你想要查询的专业系 \n");
                    else {
                        List<OrderForm> orderFormsByDepartment = textbookDepartment.getOrderFormsByDepartment(answerField.getText());
                        if (orderFormsByDepartment.size()!=0)
                            jTextArea.append(orderFormsByDepartment.toString());
                        else
                            jTextArea.append("无该专业系" + answerField.getText());
                    }
                } else if (s.equals("2")) {
                    List<Integer> addId = new ArrayList();
                    if (answerField.getText().equals("")) {
                        sql = "select * from approval_opinions where is_approved = ?";
                        List<Approval_Opinions> opinions = queryRunner.query(connection, sql, new BeanListHandler<Approval_Opinions>(Approval_Opinions.class), 0);
                        jTextArea.append(opinions.toString());
                        jTextArea.append("输入你想修改的id \n");
                    } else {
                        addId.add(Integer.parseInt(answerField.getText()));
                        sql = "update order_forms set is_approved_by_department_head = 1 where id = ?";
                        queryRunner.update(connection, sql, Integer.parseInt(answerField.getText()));
                        sql = "update approval_opinions set is_approved = 1 where id = ?";
                        queryRunner.update(connection, sql, Integer.parseInt(answerField.getText()));
                        jTextArea.append(addId.toString() + "审批成功 \n");
                    }
                }


            } else {
                JOptionPane.showMessageDialog(null, "Login failed. Please try again.");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}


class MyTextbookDepartmentFrame extends JFrame {


    public static void main(String[] args) {
        new TextbookDepartmentGUI();
    }
}