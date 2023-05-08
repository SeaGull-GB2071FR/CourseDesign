package com.keshe.domain;

public class Approval_Opinions {
    private Integer id;

    private Integer order_form_id;

    private Integer department_head_id;

    private Integer is_approved;

    public Approval_Opinions(Integer id, Integer order_form_id, Integer department_head_id) {
        this.id = id;
        this.order_form_id = order_form_id;
        this.department_head_id = department_head_id;
    }

    public Approval_Opinions() {
    }

    @Override
    public String toString() {
        return "Approval_Opinion{" +
                "id=" + id +
                ", order_form_id=" + order_form_id +
                ", department_head_id=" + department_head_id +
                ", is_approved=" + is_approved +
                '}' + "\n";
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
}
