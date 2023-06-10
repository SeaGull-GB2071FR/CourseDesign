package com.CourseDesign.service;

import com.CourseDesign.domain.Approval_Opinions;

import java.util.List;

public interface DepartmentHeadSelect {
    public List<Approval_Opinions> Select() throws Exception;

    public String SelectName(Integer id) throws Exception;
}
