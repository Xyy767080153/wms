package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.qo.EmployeeQueryObject;
import cn.wolfcode.wms.qo.QueryObject;
import cn.wolfcode.wms.service.IDepartmentService;
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
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;
    @RequestMapping("list")
    public String list(@ModelAttribute("qo") EmployeeQueryObject qo, Model model){
        PageResult result = departmentService.query(qo);
//        List<Department> list = departmentService.list();
        model.addAttribute("result",result);
        return "department/list";
    }

    @RequestMapping("input")
    public String input(Long id,Model model){
        if (Optional.ofNullable(id).isPresent()) {
            Department entity = departmentService.get(id);
            model.addAttribute("entity",entity);
        }
        return "department/input";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Department entity){
        departmentService.saveOrUpdate(entity);
        return new JSONResult();
    }

    /*@RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){

        if (Optional.ofNullable(id).isPresent()) {
            departmentService.delete(id);
        }

        JSONResult jsonResult = new JSONResult();
        return jsonResult;
    }*/

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        if (Optional.ofNullable(id).isPresent()) {
            departmentService.delete(id); //有ID
        }
        return new JSONResult(); //象征性返回
    }
}
