package org.winter.common.util;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PageUtils {

    public static Pageable startPage() {
        if (ObjectUtil.isAllNotEmpty(ServletUtils.getParameter("pageNumber"), ServletUtils.getParameter("pageSize"))) {
            int pageNumber = ServletUtils.getParameterToInt("pageNumber");
            int pageSize = ServletUtils.getParameterToInt("pageSize");
            return PageRequest.of(pageNumber - 1, pageSize);
        }
        //默认分页一页5条
        return PageRequest.of(0, 5);
    }

    public static Pageable startPage(Sort sort) {
        if (ObjectUtil.isAllNotEmpty(ServletUtils.getParameter("pageNumber"), ServletUtils.getParameter("pageSize"))) {
            int pageNumber = ServletUtils.getParameterToInt("pageNumber");
            int pageSize = ServletUtils.getParameterToInt("pageSize");
            return PageRequest.of(pageNumber - 1, pageSize, sort);
        }
        //默认分页一页5条
        return PageRequest.of(0, 5);
    }

    /**
     * 自定义分页
     */
    public static Map<String, Object> toPage(int page, int size, List<?> list, long totalElements) {
        List<?> content;
        int fromIndex = page * size;
        int toIndex = page * size + size;
        if (fromIndex > list.size()) {
            content = new ArrayList<>();
        } else if (toIndex >= list.size()) {
            content = list.subList(fromIndex, list.size());
        } else {
            content = list.subList(fromIndex, toIndex);
        }
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("pageNumber", page);
        map.put("pageSize", size);
        map.put("content", content);
        map.put("totalElements", totalElements);
        return map;
    }

}
