package com.thinkgem.jeesite.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author duanshao
 * 
 */
public class ThreadPoolUtils {
    private static final ExecutorService es = Executors.newCachedThreadPool();

    public static void execute(Runnable task) {
        es.execute(task);
    }
}
