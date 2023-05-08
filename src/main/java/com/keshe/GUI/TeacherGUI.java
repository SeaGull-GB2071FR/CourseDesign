package com.keshe.GUI;

import com.keshe.DAO.Teacher;
import com.keshe.DAO.impl.TeacherImpl;
import com.keshe.Util.GetConnUtil;
import com.keshe.domain.Approval_Opinions;
import com.keshe.domain.OrderForm;
import com.keshe.domain.User;
import com.keshe.service.Approval_Opinions_TextBook_id;
import com.keshe.service.TeacherService;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

/**
 * 所有功能的实现，将要采用，输入文本然后将字符串等传到其他Service中进行其他功能的实现
 */

@SuppressWarnings("all")
public class TeacherGUI extends JPanel implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;

    private JLabel usernameLabel, passwordLabel;
    private JButton loginButton;
    private JComboBox chooseComboBox;

    private JTextField DepartmentJTextField1, DepartmentJTextField2, CourseJTextField1, CourseJTextField2, TextbookNameJTextField1, TextbookNameJTextField2,
            PublisherJTextField1, PublisherJTextField2, QuantityJTextField1, QuantityJTextField2, chooseJTextField, ApprovedIDField;

    private JLabel DepartmentJLabel1, DepartmentJLabel2, CourseJLabel1, CourseJLabel2, TextbookNameJLabel1, TextbookNameJLabel2,
            PublisherJLabel1, PublisherJLabel2, QuantityJLabel1, QuantityJLabel2, chooseJLabel, ApprovedIdJLabel;

    public JTextArea textbookTextArea;

    public TeacherGUI() {

        JFrame frame = new JFrame("Teacher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setSize(1400, 400);
        JPanel panel = new JPanel();
        frame.add(panel);

        textbookTextArea = new JTextArea(10, 135);
        panel.add(textbookTextArea);

        DepartmentJLabel1 = new JLabel("Department:");
        DepartmentJTextField1 = new JTextField(20);
        panel.add(DepartmentJLabel1);
        panel.add(DepartmentJTextField1);

        CourseJLabel1 = new JLabel("Course:");
        CourseJTextField1 = new JTextField(20);
        panel.add(CourseJLabel1);
        panel.add(CourseJTextField1);

        TextbookNameJLabel1 = new JLabel("Textbook Name:");
        TextbookNameJTextField1 = new JTextField(20);
        panel.add(TextbookNameJLabel1);
        panel.add(TextbookNameJTextField1);

        PublisherJLabel1 = new JLabel("Publisher:");
        PublisherJTextField1 = new JTextField(20);
        panel.add(PublisherJLabel1);
        panel.add(PublisherJTextField1);

        QuantityJLabel1 = new JLabel("Quantity:");
        QuantityJTextField1 = new JTextField(20);
        panel.add(QuantityJLabel1);
        panel.add(QuantityJTextField1);

        ApprovedIdJLabel = new JLabel("被修改ID");
        ApprovedIDField = new JTextField(20);
        panel.add(ApprovedIdJLabel);
        panel.add(ApprovedIDField);
/**
 * 如果输入1，填写
 * 如果输入2，维护
 * 如果输入3，退出
 */
        chooseJLabel = new JLabel("choose");
        chooseJTextField = new JTextField(10);
//        chooseComboBox = new JComboBox<>();
//        chooseComboBox.setBounds(150, 420, 100, 30);
        panel.add(chooseJLabel);
//        panel.add(chooseComboBox);
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

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        TeacherService teacherService = new TeacherService();
        Teacher teacher = new TeacherImpl();
        User user = null;


        try {
            Connection connection = GetConnUtil.getConnect();
            QueryRunner queryRunner = new QueryRunner();

            String sql = "select * from users where username = ? and PASSWORD = ?";
            user = queryRunner.query(connection, sql, new BeanHandler<User>(User.class), username, password);
            // 检查用户名和密码是否正确
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                JOptionPane.showMessageDialog(null, "Login success!" + " 您的身份为 " + user.getType());
                if (chooseJTextField.getText().equals("")) {
                    textbookTextArea.append("请输入功能并填写下列空格 \n");
                    textbookTextArea.append("1,填写教材信息 \n");
                    textbookTextArea.append("2,维护教材信息 \n");
                }
                if (chooseJTextField.getText().equals("1")) {

                    if (!(DepartmentJTextField1.getText().equals("") && CourseJTextField1.getText().equals("") &&
                            TextbookNameJTextField1.getText().equals("") && PublisherJTextField1.getText().equals("") &&
                            QuantityJTextField1.getText().equals(""))) {
                        OrderForm orderForm1 = new OrderForm(DepartmentJTextField1.getText(), CourseJTextField1.getText()
                                , TextbookNameJTextField1.getText(), PublisherJTextField1.getText()
                                , Integer.parseInt(QuantityJTextField1.getText()));
                        orderForm1 = teacher.submitOrderForm(orderForm1);
                        Integer id = new Approval_Opinions_TextBook_id().GetTextbookId(orderForm1);
                        sql = "insert into approval_opinions values(0, ?, ?, 0)";
                        queryRunner.insert(connection, sql, new BeanHandler<Approval_Opinions>(Approval_Opinions.class), id, user.getId());

                        textbookTextArea.append("填写完毕\n");
                    } else {
                        textbookTextArea.append("请输入填写内容\n");
                    }

                } else if (chooseJTextField.getText().equals("2")) {
                    if (ApprovedIDField.getText().equals("")) {
                        sql = "select * from order_forms";
                        List<OrderForm> query = queryRunner.query(connection, sql, new BeanListHandler<OrderForm>(OrderForm.class));
                        textbookTextArea.append(query.toString());
                    } else if (!ApprovedIDField.getText().equals("")) {

                        OrderForm orderForm2 = new OrderForm(DepartmentJTextField1.getText(), CourseJTextField1.getText()
                                , TextbookNameJTextField1.getText(), PublisherJTextField1.getText()
                                , Integer.parseInt(QuantityJTextField1.getText()));

                        teacher.updateOrderForm(orderForm2, Integer.parseInt(ApprovedIDField.getText()));

                        Integer id = new Approval_Opinions_TextBook_id().GetTextbookId(orderForm2);

                        sql = "insert into approval_opinions values(0, ?, ?, 0)";
                        queryRunner.insert(connection, sql, new BeanHandler<Approval_Opinions>(Approval_Opinions.class), id, user.getId());

                        textbookTextArea.append("维护完毕\n");
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

class MyTeacherFrame extends JFrame {


    public static void main(String[] args) {
        new TeacherGUI();
    }

}






