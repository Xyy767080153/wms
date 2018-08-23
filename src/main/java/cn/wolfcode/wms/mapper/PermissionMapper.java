package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.qo.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    List<Permission> selectAll();



    Integer selectForCount(QueryObject qo);
    List<?> selectForList(QueryObject qo);

    List<String> selectExpressions();
    List<String> selectByEmployeeId(Long employeeId);

}