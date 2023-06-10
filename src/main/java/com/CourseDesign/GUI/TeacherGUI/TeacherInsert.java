package com.CourseDesign.GUI.TeacherGUI;

import com.CourseDesign.DAO.TeacherDAO;
import com.CourseDesign.DAO.impl.TeacherDAOImpl;
import com.CourseDesign.domain.OrderForm;
import com.CourseDesign.service.Approval_Opinions_TextBook_id;
import com.CourseDesign.service.Impl.Approval_Opinions_TextBook_idImpl;
import com.keshe.DAO.Teacher;
import com.keshe.DAO.impl.TeacherImpl;
import com.keshe.Util.GetConnUtil;
import com.keshe.domain.Approval_Opinions;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;


@SuppressWarnings("all")
public class TeacherInsert extends JFrame implements ActionListener {


    private JButton commitButton;

    private JTextField DepartmentJTextField, DepartmentJTextField2, CourseJTextField, CourseJTextField2, TextbookNameJTextField, TextbookNameJTextField2,
            PublisherJTextField, PublisherJTextField2, QuantityJTextField, QuantityJTextField2;

    private JLabel DepartmentJLabel1, DepartmentJLabel2, CourseJLabel1, CourseJLabel2, TextbookNameJLabel1, TextbookNameJLabel2,
            PublisherJLabel1, PublisherJLabel2, QuantityJLabel1, QuantityJLabel2;
    private TeacherDAO teacher;

    private Integer TeacherId;

    private Approval_Opinions_TextBook_id approvalOpinionsTextBookId;

    public TeacherInsert(Integer TeacherId) {
        this.TeacherId = TeacherId;
        JFrame frame = new JFrame("教师填写功能");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setSize(280, 230);
        JPanel panel = new JPanel(new GridLayout(6, 2));

        DepartmentJLabel1 = new JLabel("专业:");
        DepartmentJTextField = new JTextField(10);
        panel.add(DepartmentJLabel1);
        panel.add(DepartmentJTextField);

        CourseJLabel1 = new JLabel("课程:");
        CourseJTextField = new JTextField(10);
        panel.add(CourseJLabel1);
        panel.add(CourseJTextField);

        TextbookNameJLabel1 = new JLabel("教材名:");
        TextbookNameJTextField = new JTextField(10);
        panel.add(TextbookNameJLabel1);
        panel.add(TextbookNameJTextField);

        PublisherJLabel1 = new JLabel("出版社:");
        PublisherJTextField = new JTextField(10);
        panel.add(PublisherJLabel1);
        panel.add(PublisherJTextField);

        QuantityJLabel1 = new JLabel("数量:");
        QuantityJTextField = new JTextField(10);
        panel.add(QuantityJLabel1);
        panel.add(QuantityJTextField);

        commitButton = new JButton("提交");
        commitButton.addActionListener(this);
        panel.add(new JLabel(""));
        panel.add(commitButton);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "提交") {

            try {
                Connection connection = GetConnUtil.getConnect();
                QueryRunner queryRunner = new QueryRunner();

                teacher = new TeacherDAOImpl();
                approvalOpinionsTextBookId = new Approval_Opinions_TextBook_idImpl();

                if (!(DepartmentJTextField.getText().equals("") && CourseJTextField.getText().equals("") &&
                        TextbookNameJTextField.getText().equals("") && PublisherJTextField.getText().equals("") &&
                        QuantityJTextField.getText().equals(""))) {
                    OrderForm orderForm1 = new OrderForm(DepartmentJTextField.getText(), CourseJTextField.getText()
                            , TextbookNameJTextField.getText(), PublisherJTextField.getText()
                            , Integer.parseInt(QuantityJTextField.getText()));
                    teacher.submitOrderForm(orderForm1);
                    Integer id = approvalOpinionsTextBookId.GetTextbookId(orderForm1);

                    String sql = "insert into approval_opinions values(0, ?, ?, 0,'无')";
                    queryRunner.insert(connection, sql, new BeanHandler<Approval_Opinions>(Approval_Opinions.class), id, TeacherId);
                    JOptionPane.showMessageDialog(null, "添加成功");

                    DepartmentJTextField.setText("");
                    CourseJTextField.setText("");
                    TextbookNameJTextField.setText("");
                    PublisherJTextField.setText("");
                    QuantityJTextField.setText("");

                } else {
                    JOptionPane.showMessageDialog(null, "添加失败，请写入信息");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }


}
