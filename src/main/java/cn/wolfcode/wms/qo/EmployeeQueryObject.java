package cn.wolfcode.wms.qo;

import cn.wolfcode.wms.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class EmployeeQueryObject extends QueryObject{
    private String keyword;
    private Long deptId = -1L;
    public String getKeyword(){
        return StringUtil.empty2Null(keyword);
    }
}
