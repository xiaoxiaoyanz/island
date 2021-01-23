package com.wucc.island.web.uidtest;

import com.baidu.fsg.uid.UidGenerator;
import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.wucc.island.api.helloworld.HelloWoldServiceI;
import com.wucc.island.entity.helloworld.HelloWorld;
import com.wucc.island.web.IslandTest;
import lombok.Builder;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Test for {@link CachedUidGenerator}
 * 
 * @author yutianbao
 */
//@ContextConfiguration(locations = { "classpath:uid/cached-uid-spring.xml" })
public class CachedUidGeneratorTest extends IslandTest {
    private static final int SIZE = 70000000; // 700w
    private static final boolean VERBOSE = false;
    private static final int THREADS = Runtime.getRuntime().availableProcessors() << 1;

    @Resource
    private UidGenerator uidGenerator;

    @Resource
    private HelloWoldServiceI helloWoldService;

    /**
     * Test for serially generate
     * 
     * @throws IOException
     */
    @Test
    public void testSerialGenerate() throws IOException {
        // Generate UID serially
        Set<Long> uidSet = new HashSet<>(SIZE);

        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            doGenerate(uidSet, i);
        }
        long end = System.currentTimeMillis();

        long second = (end - start) / 1000;

        System.out.println(second);


        // Check UIDs are all unique
        checkUniqueID(uidSet);
    }

    /**
     * Test for parallel generate
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    public void testParallelGenerate() throws InterruptedException, IOException {

        long start = System.currentTimeMillis();
        AtomicInteger control = new AtomicInteger(-1);
        Set<Long> uidSet = new ConcurrentSkipListSet<>();

        // Initialize threads
        List<Thread> threadList = new ArrayList<>(THREADS);
        for (int i = 0; i < THREADS; i++) {
            Thread thread = new Thread(() -> workerRun(uidSet, control));
            thread.setName("UID-generator-" + i);

            threadList.add(thread);
            thread.start();
        }

        // Wait for worker done
        for (Thread thread : threadList) {
            thread.join();
        }

        long end = System.currentTimeMillis();

        long time = (start - end) / 1000;
        System.out.println(time);

        // Check generate 700w times
        Assert.assertEquals(SIZE, control.get());

        // Check UIDs are all unique
        checkUniqueID(uidSet);
    }

    /**
     * Woker run
     */
    private void workerRun(Set<Long> uidSet, AtomicInteger control) {
        for (;;) {
            int myPosition = control.updateAndGet(old -> (old == SIZE ? SIZE : old + 1));
            if (myPosition == SIZE) {
                return;
            }

            doGenerate(uidSet, myPosition);
        }
    }

    /**
     * Do generating
     */
    private void doGenerate(Set<Long> uidSet, int index) {
        long uid = uidGenerator.getUID();
        String parsedInfo = uidGenerator.parseUID(uid);
        boolean existed = !uidSet.add(uid);
        if (existed) {
            System.out.println("Found duplicate UID " + uid);
        }

        // Check UID is positive, and can be parsed
        Assert.assertTrue(uid > 0L);
        Assert.assertTrue(StringUtils.isNotBlank(parsedInfo));
        helloWoldService.save(HelloWorld.builder()
                .id(uid)
                .name("haha")
                .password("123456")
                .description("测试")
                .build());

        if (VERBOSE) {
            System.out.println(Thread.currentThread().getName() + " No." + index + " >>> " + parsedInfo);
        }
    }

    /**
     * Check UIDs are all unique
     */
    private void checkUniqueID(Set<Long> uidSet) throws IOException {
        System.out.println(uidSet.size());
        Assert.assertEquals(SIZE, uidSet.size());
    }

}
