package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.qo.EmployeeQueryObject;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.util.JSONResult;
import cn.wolfcode.wms.util.PageResult;
import cn.wolfcode.wms.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") EmployeeQueryObject qo, Model model){
        PageResult result = roleService.query(qo);
//        List<Role> list = roleService.list();
        model.addAttribute("result",result);
        return "role/list";
    }

    @RequestMapping("input")
    public String input(Long id,Model model){
        //查询所有的权限共享出去
        model.addAttribute("permissions",permissionService.list());
        if (Optional.ofNullable(id).isPresent()) {
            Role entity = roleService.get(id);
            model.addAttribute("entity",entity);
        }
        return "role/input";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Role entity,Long[] permissionIds){
        roleService.saveOrUpdate(entity,permissionIds);
        return new JSONResult();
    }

    /*@RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){

        if (Optional.ofNullable(id).isPresent()) {
            roleService.delete(id);
        }

        JSONResult jsonResult = new JSONResult();
        return jsonResult;
    }*/

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        if (Optional.ofNullable(id).isPresent()) {
            roleService.delete(id); //有ID
        }
        return new JSONResult(); //象征性返回
    }
}
