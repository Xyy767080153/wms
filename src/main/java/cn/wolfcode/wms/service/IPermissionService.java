package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.util.PageResult;

import java.util.List;

public interface IPermissionService {
    void delete(Long id);

    List<Permission> list();

    PageResult query(QueryObject qo);

    void reload();
}
