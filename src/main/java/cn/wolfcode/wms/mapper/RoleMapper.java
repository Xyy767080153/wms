package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    Integer selectForCount(QueryObject qo);
    List<?> selectForList(QueryObject qo);

    //插入中间表关系
    void insertRelation(@Param("roleId") Long roleId,
                        @Param("permissionId") Long permissionId);

    //删除关系
    void deleteRelation(Long roleId);
}