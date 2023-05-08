package com.keshe.GUI;

import com.keshe.DAO.Bookstore;
import com.keshe.DAO.impl.BookstoreImpl;
import com.keshe.Util.GetConnUtil;
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
public class BookstoreGUI extends JPanel implements ActionListener {

    private JTextField usernameField, chooseJTextField, PublisherField;
    private JPasswordField passwordField;
    private JTextArea jTextArea;

    private JLabel usernameLabel, passwordLabel, chooseJLabel, TextbookList, PublisherLabel;
    private JButton loginButton;
    private JLabel JLabel1, JLabel2;

    public BookstoreGUI() {

        JFrame frame = new JFrame("Bookstore");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setSize(1400, 400);
        JPanel panel = new JPanel();
        frame.add(panel);

        TextbookList = new JLabel("TextbookList");
        jTextArea = new JTextArea(10, 135);
        panel.add(TextbookList);
        panel.add(jTextArea);

        PublisherLabel = new JLabel("输入出版社:");
        PublisherField = new JTextField(10);
        panel.add(PublisherLabel);
        panel.add(PublisherField);

        chooseJLabel = new JLabel("choose");
        chooseJTextField = new JTextField(10);
        panel.add(chooseJLabel);
        panel.add(chooseJTextField);

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
        Bookstore bookstore = new BookstoreImpl();
        User user = null;

        try {
            Connection connection = GetConnUtil.getConnect();
            QueryRunner queryRunner = new QueryRunner();

            String sql = "select * from users where username = ? and PASSWORD = ?";
            user = queryRunner.query(connection, sql, new BeanHandler<User>(User.class), username, password);
            // 检查用户名和密码是否正确
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                //如果answer为空字符，直接查询可以修改的内容
                JOptionPane.showMessageDialog(null, "Login success!" + " 您的身份为 " + user.getType());
                if (chooseJTextField.getText().equals("")) {
                    jTextArea.append("请输入功能并填写下列空格 \n");
                    jTextArea.append("1,订购单按出版社组织 \n");
                    jTextArea.append("2,生成订购单 \n");

                } else if (chooseJTextField.getText().equals("1")) {
                    jTextArea.append("输入你想查询的出版社\n");
                    if (!PublisherField.getText().equals("")) {
                        List<OrderForm> orderFormsByPublisher = bookstore.getOrderFormsByPublisher(PublisherField.getText());
                        jTextArea.append("按出版社查询的结果如下:\n");
                        jTextArea.append(orderFormsByPublisher.toString());
                        if (orderFormsByPublisher.size() == 0)
                            jTextArea.append("未查询到出版社为" + PublisherField.getText() + "的书 \n");
                    }

                } else if (chooseJTextField.getText().equals("2")) {
                    List<OrderForm> orderFormsList = new ArrayList<>();
                    sql = "select * from order_forms order by publisher";
                    List<OrderForm> forms = queryRunner.query(connection, sql, new BeanListHandler<OrderForm>(OrderForm.class));
                    jTextArea.append("查询结果如下（订购单按出版社组织）\n");
                    jTextArea.append(forms.toString());

                }
            } else {
                JOptionPane.showMessageDialog(null, "Login failed. Please try again.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

class MyBookstoreJFrame extends JFrame {
    public static void main(String[] args) {
        new BookstoreGUI();
    }
}
