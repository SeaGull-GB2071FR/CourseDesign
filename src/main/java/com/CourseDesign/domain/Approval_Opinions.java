package com.CourseDesign.domain;

public class Approval_Opinions {
    private Integer id;

    private Integer order_form_id;

    private String textbook_name;

    private Integer department_head_id;

    private Integer is_approved;

    private String opinion;

    public Approval_Opinions(Integer id, Integer order_form_id, String textbook_name,
                             Integer department_head_id, Integer is_approved, String opinion) {
        this.id = id;
        this.order_form_id = order_form_id;
        this.textbook_name = textbook_name;
        this.department_head_id = department_head_id;
        this.is_approved = is_approved;
        this.opinion = opinion;
    }

    public Approval_Opinions() {
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder_form_id() {
        return order_form_id;
    }

    public void setOrder_form_id(Integer order_form_id) {
        this.order_form_id = order_form_id;
    }

    public Integer getDepartment_head_id() {
        return department_head_id;
    }

    public void setDepartment_head_id(Integer department_head_id) {
        this.department_head_id = department_head_id;
    }

    public Integer getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(Integer is_approved) {
        this.is_approved = is_approved;
    }

    public String getTextbook_name() {
        return textbook_name;
    }

    public void setTextbook_name(String textbook_name) {
        this.textbook_name = textbook_name;
    }
}
