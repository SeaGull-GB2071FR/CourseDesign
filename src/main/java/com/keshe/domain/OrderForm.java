package com.keshe.domain;

// 订购表类
public class OrderForm {
    private Integer id;

    private String department;  // 专业系

    private String course;    //课程

    private String textbook_name;   //书名

    private String publisher;   //出版社

    private int quantity;   //数量

    private Integer is_approved_by_department_head;

    public OrderForm() {
    }

    @Override
    public String toString() {
        return "OrderForm{" +
                "id=" + id +
                ", department='" + department + '\'' +
                ", course='" + course + '\'' +
                ", textbookName='" + textbook_name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", quantity=" + quantity +
                ", isApprovedByDepartmentHead=" + is_approved_by_department_head +
                '}' + " \n";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTextbook_name() {
        return textbook_name;
    }

    public void setTextbook_name(String textbook_name) {
        this.textbook_name = textbook_name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getIs_approved_by_department_head() {
        return is_approved_by_department_head;
    }

    public void setIs_approved_by_department_head(Integer is_approved_by_department_head) {
        this.is_approved_by_department_head = is_approved_by_department_head;
    }

    public OrderForm(String department, String course, String textbookName, String publisher, int quantity) {
        this.department = department;
        this.course = course;
        this.textbook_name = textbookName;
        this.publisher = publisher;
        this.quantity = quantity;

    }
}
