package com.fh.mapper;

import com.fh.bean.EmpBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 * @title: EmpMapper
 * @projectName service_emp_api
 * @description: TODO
 * @date 2020/1/1811:39
 */
@Repository
@Mapper
public interface EmpMapper {
    /**
     * 查询
     * @param map
     * @return
     */
    List<EmpBean> queryEmpList(Map map);
}
