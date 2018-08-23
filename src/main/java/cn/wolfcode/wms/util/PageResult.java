package cn.wolfcode.wms.util;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class PageResult {

    public static final PageResult empty_page = new PageResult(1,1,Collections.EMPTY_LIST,0 );
    private int currentPage;
    private int pageSize;
    private List<?> data;
    private int rows;

    private int endPage;
    private int prevPage;
    private int nextPage;

    public PageResult(int currentPage, int pageSize, List<?> data, int rows) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.data = data;
        this.rows = rows;
        if (rows <= pageSize){
            endPage = 1;
            prevPage = 1;
            nextPage = 1;
            return;
        }
        endPage = rows%pageSize == 0 ? rows/pageSize : rows/pageSize+1;
        prevPage = currentPage > 1 ? currentPage - 1 : 1;
        nextPage = currentPage < endPage ? currentPage + 1 : endPage;
    }


}
