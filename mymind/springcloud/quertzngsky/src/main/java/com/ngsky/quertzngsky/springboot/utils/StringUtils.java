package com.ngsky.quertzngsky.springboot.utils;

import java.util.List;
import java.util.Map;

/**
 * <dl>
 * <dt>StringUtils</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 12/13/2018 8:21 AM</dd>
 * </dl>
 *
 * @author daxiong
 */
public enum StringUtils {

    getStringUtil;

    // 是否为空
    public boolean isEmpty(String str) {
        return (str == null) || (str.length() == 0) || (("").equals(str));
    }

    // 去除空格
    public String trim(String str) {
        return str == null ? null : str.trim();
    }

    // 获取map参数
    public String getMapString(Map<String, String> map) {
        String ret = "";
        for (Map.Entry entry : map.entrySet()) {
            ret += entry.getValue() + " ";
        }
        return ret;
    }

    // 获取List参数值
    public String getListString(List<String> list) {
        String ret = "";
        for (String s : list) {
            ret += s + " ";
        }
        return ret;
    }

}
