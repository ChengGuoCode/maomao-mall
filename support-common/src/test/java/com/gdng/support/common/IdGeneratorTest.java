package com.gdng.support.common;

import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.exception.GdngException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/8 09:53
 * @Description:
 * @Version: 1.0.0
 */
public class IdGeneratorTest {

    private static final IdGenerator orderNoGenerator = new IdGenerator("O");
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    @Test
    public void testNextId() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        List<String> exist = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                for (int j = 0; j < 50; j++) {
                    String id = orderNoGenerator.nextId();
                    if (exist.contains(id)) {
                        throw new GdngException(GlobalResponseEnum.SYSTEM_ERR);
                    }
                    exist.add(id);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(exist);
    }

}
