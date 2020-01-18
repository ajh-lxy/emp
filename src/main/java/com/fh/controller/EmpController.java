package com.fh.controller;

import com.fh.bean.EmpBean;
import com.fh.service.EmpService;
import com.fh.utils.page.PageBean;
import com.fh.utils.response.ResponseServer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Lenovo
 * @title: EmpController
 * @projectName service_emp_api
 * @description: TODO
 * @date 2020/1/1811:41
 */
@RestController
@RequestMapping("emp")
@CrossOrigin
public class EmpController {

    @Resource
    private EmpService empService;


    /**
     * 查询学生
     * @param page
     * @param empBean
     * @return
     */
    @RequestMapping("queryEmpList")
    public ResponseServer queryEmpList(PageBean<EmpBean> page, EmpBean empBean){
        ResponseServer responseServer = empService.queryEmpList(page,empBean);
        return responseServer;
    }
}
