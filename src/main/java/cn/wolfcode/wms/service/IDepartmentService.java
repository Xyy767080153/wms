package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.util.PageResult;

import java.util.List;

public interface IDepartmentService {
    void saveOrUpdate(Department entity);

    void delete(Long id);

    Department get(Long id);

    List<Department> list();

    PageResult query(QueryObject qo);
}
