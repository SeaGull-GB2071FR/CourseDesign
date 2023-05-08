package com.keshe.Main;//package com.keshe.Main;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//
//public class TextbookOrderSystem extends JFrame implements ActionListener {
//
//    private JLabel titleLabel, instructionLabel, statusLabel, departmentLabel;
//    private JTextField teacherNameTextField, courseNameTextField, courseNumberTextField;
//    private JTextArea textbookTextArea;
//    private JButton submitButton, approveButton, rejectButton, generateButton;
//    private JComboBox<String> departmentComboBox;
//    private Connection conn;
//    private PreparedStatement ps;
//    private ResultSet rs;
//
//    public TextbookOrderSystem() {
//        setTitle("教材订购管理系统");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(800, 600);
//
//        titleLabel = new JLabel("教材订购管理系统");
//        titleLabel.setFont(new Font("宋体", Font.BOLD, 30));
//        titleLabel.setBounds(250, 30, 300, 50);
//
//        instructionLabel = new JLabel("请填写以下信息：");
//        instructionLabel.setFont(new Font("宋体", Font.BOLD, 20));
//        instructionLabel.setBounds(50, 100, 200, 30);
//
//        JLabel teacherNameLabel = new JLabel("教师姓名：");
//        teacherNameLabel.setBounds(50, 150, 100, 30);
//        teacherNameTextField = new JTextField();
//        teacherNameTextField.setBounds(150, 150, 100, 30);
//
//        JLabel courseNameLabel = new JLabel("课程名称：");
//        courseNameLabel.setBounds(300, 150, 100, 30);
//        courseNameTextField = new JTextField();
//        courseNameTextField.setBounds(400, 150, 100, 30);
//
//        JLabel courseNumberLabel = new JLabel("课程编号：");
//        courseNumberLabel.setBounds(550, 150, 100, 30);
//        courseNumberTextField = new JTextField();
//        courseNumberTextField.setBounds(650, 150, 100, 30);
//
//        JLabel textbookLabel = new JLabel("教材列表：");
//        textbookLabel.setBounds(50, 200, 100, 30);
//        textbookTextArea = new JTextArea();
//        textbookTextArea.setBounds(150, 200, 600, 150);
//
//        submitButton = new JButton("提交");
//        submitButton.setBounds(350, 370, 100, 30);
//        submitButton.addActionListener(this);
//
//        departmentLabel = new JLabel("所属系别：");
//        departmentLabel.setBounds(50, 420, 100, 30);
//        departmentComboBox = new JComboBox<>();
//        departmentComboBox.setBounds(150, 420, 100, 30);
//
//        approveButton = new JButton("通过");
//        approveButton.setBounds(300, 420, 100, 30);
//        approveButton.addActionListener(this);
//
//        rejectButton = new JButton("不通过");
//        rejectButton.setBounds(450, 420, 100, 30);
//        rejectButton.addActionListener(this);
//
//        generateButton = new JButton("生成订购单");
//        generateButton.setBounds(600, 420, 120, 30);
//        generateButton.addActionListener(this);
//
//        statusLabel = new JLabel("当前状态：未提交");
//        statusLabel.setBounds(50, 470, 300, 30);
//
//        add(titleLabel);
//        add(instructionLabel);
//        add(teacherNameLabel);
//        add(teacherNameTextField);
//        add(courseNameLabel);
//        add(courseNameTextField);
//        add(courseNumberLabel);
//        add(courseNumberTextField);
//        add(textbookLabel);
//        add(textbookTextArea);
//        add(submitButton);
//        add(departmentLabel);
//        add(departmentComboBox);
//        add(approveButton);
//        add(rejectButton);
//        add(generateButton);
//        add(statusLabel);
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/textbook_order", "root", "GB2071FR");
//            ps = conn.prepareStatement("INSERT INTO orders (teacher_name, course_name, course_number, textbooks, department, status) VALUES (?, ?, ?, ?, ?, ?)");
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//
//        loadDepartments();
//        setVisible(true);
//    }
//
//    private void loadDepartments() {
//        try {
//            ps = conn.prepareStatement("SELECT * FROM departments");
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                departmentComboBox.addItem(rs.getString("name"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == submitButton) {
//            submitOrder();
//        } else if (e.getSource() == approveButton) {
//            updateOrderStatus("通过");
//        } else if (e.getSource() == rejectButton) {
//            updateOrderStatus("不通过");
//        } else if (e.getSource() == generateButton) {
//            generateOrderForm();
//        }
//    }
//
//    private void submitOrder() {
//        try {
//            ps.setString(1, teacherNameTextField.getText());
//            ps.setString(2, courseNameTextField.getText());
//            ps.setString(3, courseNumberTextField.getText());
//            ps.setString(4, textbookTextArea.getText());
//            ps.setString(5, departmentComboBox.getSelectedItem().toString());
//            ps.setString(6, "未审核");
//
//            ps.executeUpdate();
//            statusLabel.setText("当前状态：已提交");
//            submitButton.setEnabled(false);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void updateOrderStatus(String status) {
//        try {
//            ps = conn.prepareStatement("UPDATE orders SET status = ? WHERE department = ? AND status = ?");
//            ps.setString(1, status);
//            ps.setString(2, departmentComboBox.getSelectedItem().toString());
//            ps.setString(3, "未审核");
//
//            int rowsAffected = ps.executeUpdate();
//
//            if (rowsAffected > 0) {
//                statusLabel.setText("当前状态：审核" + status);
//                approveButton.setEnabled(false);
//                rejectButton.setEnabled(false);
//            } else {
//                JOptionPane.showMessageDialog(this, "当前没有需要审核的订购单！");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void generateOrderForm() {
//        try {
//            ps = conn.prepareStatement("SELECT * FROM orders WHERE department = ? AND status = ?");
//            ps.setString(1, departmentComboBox.getSelectedItem().toString());
//            ps.setString(2, "审核通过");
//
//            rs = ps.executeQuery();
//
//            StringBuilder sb = new StringBuilder();
//            sb.append("出版社1：\n");
//
//            while (rs.next()) {
//                sb.append(rs.getString("teacher_name")).append("（").append(rs.getString("course_name")).append("）\n");
//                sb.append(rs.getString("textbooks")).append("\n");
//            }
//
//            JOptionPane.showMessageDialog(this, sb.toString());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        new TextbookOrderSystem();
//    }
//}
