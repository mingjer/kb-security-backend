package com.ppdai.qa.batme.dao.mapper.employee;

import com.ppdai.qa.batme.model.employee.entity.EmployeeInfo;

public interface EmployeeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeInfo record);

    int insertSelective(EmployeeInfo record);

    EmployeeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmployeeInfo record);

    int updateByPrimaryKey(EmployeeInfo record);
}