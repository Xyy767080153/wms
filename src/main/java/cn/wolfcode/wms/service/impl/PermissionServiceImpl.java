package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.mapper.PermissionMapper;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.util.PageResult;
import cn.wolfcode.wms.util.PermissionUtil;
import cn.wolfcode.wms.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private ApplicationContext ctx;

    public void reload(){
        //获取所有的权限表达式
        List<String> exps = permissionMapper.selectExpressions();
        //获取系统所有控制器
        Collection<Object> ctrls = ctx.getBeansWithAnnotation(Controller.class).values();
        //遍历
        ctrls.forEach(ctrl -> {
            //获取每个控制器中所有的方法
            Method[] ms = ctrl.getClass().getDeclaredMethods();
            //遍历所有方法
            for (Method m : ms) {
                //获取每个方法的权限表达式
                String exp = PermissionUtil.buildExpression(m);
                //获取所有权限注释
                RequiredPermission anno = m.getAnnotation(RequiredPermission.class);
                //判断  如果有注释并且方法的权限表达式系统内不存在,则设置并保存到数据库
                if (anno != null && !exps.contains(exp)) {
                    Permission p = new Permission();
                    p.setName(anno.value());
                    p.setExpression(exp);
                    permissionMapper.insert(p);
                }
            }
        });
    }

    public void delete(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    public List<Permission> list() {
        return permissionMapper.selectAll();
    }

    public PageResult query(QueryObject qo) {
        int rows = permissionMapper.selectForCount(qo);
        if (rows == 0){
            return PageResult.empty_page;
        }
        List<?> data = permissionMapper.selectForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),data,rows);
    }
}
