package com.sardine.base.until;

import com.sardine.base.page.Page;
import com.sardine.base.page.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 分页工具类
 * <p/>
 * <p>Created by Wakim on 2015/7/24.
 *
 * @author Wakim
 * @version 0.1-SNAPSHOT
 */
public class PageUtils {
    public static <T> Page<T> mapToPage(Map<?, T> map, Pageable pageable) {
        Set set = map.entrySet();
        if (set.size() == 0) {
            return null;
        }
        List<T> tList = new ArrayList();
        for (Object o : set) {
            tList.add(map.get(o));
        }

        if (pageable.getPageSize() == 0) {
            pageable.setPageSize(tList.size());
        }

        pageable.setCountOfElements(tList.size());

        if (pageable.exists() && pageable.getPageNumber() != 0) {
            throw new IndexOutOfBoundsException("页面越界");
        } else if (pageable.getCountOfElements() == 0) {
            return null;
        }

        List<T> list = new ArrayList<>();

        for (int index = pageable.getPageOffset() * pageable.getPageSize(); index < (pageable.getPageSize() * (pageable.getPageOffset() + 1)); index++) {
            list.add(tList.get(index));
        }

        return new Page<>(pageable, list);

    }
}
