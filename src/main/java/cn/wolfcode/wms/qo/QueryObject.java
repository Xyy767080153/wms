package cn.wolfcode.wms.qo;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class QueryObject {
    private int currentPage = 1;
    private int pageSize = 5;
    public Integer getStart(){
        return (currentPage - 1) * pageSize;
    }
}
