package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.mapper.RoleMapper;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;
    public void saveOrUpdate(Role entity ,Long[] permissionIds) {
        if (!Optional.ofNullable(entity.getId()).isPresent()) {
            roleMapper.insert(entity);

        }else {
            //先打破关系
            roleMapper.deleteRelation(entity.getId());
            roleMapper.updateByPrimaryKey(entity);
        }
        //保存/维护关系
        if (Optional.ofNullable(permissionIds).isPresent()) {
            for (Long permissionId : permissionIds) {
                roleMapper.insertRelation(entity.getId(),permissionId);
            }
        }
    }

    public void delete(Long id) {
        //删除关系
        roleMapper.deleteRelation(id);
        roleMapper.deleteByPrimaryKey(id);
    }

    public Role get(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public List<Role> list() {
        return roleMapper.selectAll();
    }

    public PageResult query(QueryObject qo) {
        int rows = roleMapper.selectForCount(qo);
        if (rows == 0){
            return PageResult.empty_page;
        }
        List<?> data = roleMapper.selectForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),data,rows);
    }
}
