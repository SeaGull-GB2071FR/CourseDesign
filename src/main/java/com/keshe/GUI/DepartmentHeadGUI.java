package com.keshe.GUI;

import com.keshe.DAO.DepartmentHead;
import com.keshe.DAO.impl.DepartmentHeadImpl;
import com.keshe.Util.GetConnUtil;
import com.keshe.domain.Approval_Opinions;
import com.keshe.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

@SuppressWarnings("All")
public class DepartmentHeadGUI extends User implements ActionListener {
    private JTextField usernameField, answerJField;
    private JPasswordField passwordField;

    private JLabel usernameLabel, passwordLabel, answerLabel;
    private JButton loginButton;
    public JTextArea textbookTextArea;

    private JLabel TextbookListJLabel;

    public DepartmentHeadGUI() {

        try {
            JFrame frame = new JFrame("DepartmentHead");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1400, 400);
            JPanel panel = new JPanel();
            frame.add(panel);

            TextbookListJLabel = new JLabel("TextbookList:");
            panel.add(TextbookListJLabel);
            textbookTextArea = new JTextArea(10, 135);
////        textbookTextArea.setBounds(150, 200, 600, 150);
            panel.add(textbookTextArea);

            answerLabel = new JLabel("审核ID:");
            panel.add(answerLabel);
            answerJField = new JTextField(5);
            panel.add(answerJField);


            usernameLabel = new JLabel("Username:");
            usernameField = new JTextField(20);
            passwordLabel = new JLabel("Password:");
            passwordField = new JPasswordField(20);
            panel.add(usernameLabel);
            panel.add(usernameField);
            panel.add(passwordLabel);
            panel.add(passwordField);


            loginButton = new JButton("提交");
            loginButton.addActionListener(this);
            panel.add(new JLabel(""));
            panel.add(loginButton);


            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String answer = answerJField.getText();
        User user = null;
        DepartmentHead departmentHead;

        try {
            Connection connection = GetConnUtil.getConnect();
            QueryRunner queryRunner = new QueryRunner();

            String sql = "select * from users where username = ? and PASSWORD = ?";
            user = queryRunner.query(connection, sql, new BeanHandler<User>(User.class), username, password);
            // 检查用户名和密码是否正确
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                //如果answer为空字符，直接查询可以修改的内容
                if (!answer.equals("")) {
                    JOptionPane.showMessageDialog(null, "Login success!" + " 您的身份为 " + user.getType());
                    departmentHead = new DepartmentHeadImpl();

                    departmentHead.approveOrderForm(answer);

                    textbookTextArea.append("id = " + answer + "已经修改完成\n");
                    sql = "select * from approval_opinions where is_approved = ?";
                    List<Approval_Opinions> opinions = queryRunner.query(connection, sql, new BeanListHandler<Approval_Opinions>(Approval_Opinions.class), 0);

                } else {//如果不为空，这进入审批
                    JOptionPane.showMessageDialog(null, "Login success!" + " 您的身份为 " + user.getType());
                    sql = "select * from approval_opinions where is_approved = ?";
                    List<Approval_Opinions> opinions = queryRunner.query(connection, sql, new BeanListHandler<Approval_Opinions>(Approval_Opinions.class), 0);
                    textbookTextArea.append(opinions.toString());
                    textbookTextArea.append("请在choose框内填写你要审批的id \n");
                }


            } else {
                JOptionPane.showMessageDialog(null, "Login failed. Please try again.");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class MyDepartmentHeadFrame extends JFrame {
    public static void main(String[] args) {
        new DepartmentHeadGUI();
    }
}