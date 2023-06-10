package com.CourseDesign.GUI.BookstoreGUI;

import com.CourseDesign.DAO.BookstoreDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("all")
public class BookstoreButton extends JFrame implements ActionListener {

    private Integer id;

    private BookstoreDAO bookstoreDAO;

    private JButton SelectByPublisherButton,QuitButton;

    private JLabel SelectByPublisherLabel,QuitLabel;

    JFrame frame;

    public BookstoreButton(Integer id) {
        this.id = id;
        frame = new JFrame("订购商功能选择");
        SelectByPublisherButton = new JButton("生成订购单");
        QuitButton = new JButton("退出");

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(SelectByPublisherButton);
        panel.add(QuitButton);

        frame.add(panel);
        SelectByPublisherButton.addActionListener(this);
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
        if (e.getActionCommand() == "生成订购单") {
            try {
                BookstoreSelectByPublisher bookstoreSelectByPublisher = new BookstoreSelectByPublisher(id);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (e.getActionCommand() == "退出") {
            frame.dispose(); // 关闭当前页面
        }else {

        }
    }

}
