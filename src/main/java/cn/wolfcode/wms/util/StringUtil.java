package cn.wolfcode.wms.util;

import org.springframework.util.StringUtils;

public class StringUtil {
    public static String empty2Null(String s){
        return StringUtils.hasLength(s) ? s : null;
    }
}
