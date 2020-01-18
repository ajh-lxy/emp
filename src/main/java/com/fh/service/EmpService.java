package com.fh.service;

import com.fh.bean.EmpBean;
import com.fh.utils.page.PageBean;
import com.fh.utils.response.ResponseServer;

/**
 * @author Lenovo
 * @title: EmpService
 * @projectName service_emp_api
 * @description: TODO
 * @date 2020/1/1811:18
 */
public interface EmpService {
    /**
     * 查询员工信息
     * @param page
     * @param empBean
     * @return
     */
    ResponseServer queryEmpList(PageBean<EmpBean> page, EmpBean empBean);
}
