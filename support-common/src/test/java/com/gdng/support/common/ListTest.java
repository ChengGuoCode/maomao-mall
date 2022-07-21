package com.gdng.support.common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/21 15:04
 * @Description:
 * @Version: 1.0.0
 */
public class ListTest {

    private static final List<Integer> list;

    static {
        list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
    }

    @Test
    public void testListSort() {
        list.sort((o1, o2) -> o1 > o2 ? 1 : -1);
        System.out.println(list);
        // 正数后移，负数前移
    }

    @Test
    public void testListSub() {
        System.out.println(list.subList(3, 3));
    }

}
