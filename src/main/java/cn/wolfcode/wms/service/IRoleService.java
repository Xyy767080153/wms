package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.util.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRoleService {
    void saveOrUpdate(Role entity,Long[] permissionIds);

    void delete(Long id);

    Role get(Long id);

    List<Role> list();

    PageResult query(QueryObject qo);


}
