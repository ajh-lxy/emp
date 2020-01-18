package com.fh.service.impl;

import com.fh.bean.EmpBean;
import com.fh.mapper.EmpMapper;
import com.fh.service.EmpService;
import com.fh.utils.page.PageBean;
import com.fh.utils.response.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 * @title: EmpServiceImpl
 * @projectName service_emp_api
 * @description: TODO
 * @date 2020/1/1811:18
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;


    @Override
    public ResponseServer queryEmpList(PageBean<EmpBean> page, EmpBean empBean) {
        //条件
        Map map = new HashMap();
        map.put("page",page);
        List<EmpBean> drugBeanList = empMapper.queryEmpList(map);
        page.setData(drugBeanList);
        page.setRecordsTotal((long) drugBeanList.size());
        return ResponseServer.success(page);
    }
}
