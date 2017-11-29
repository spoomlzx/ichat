package com.lan.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * package com.lan.common.config
 *
 * @author lanzongxiao
 * @date 29/11/2017
 */
@Configuration
public class AsyncTaskExecutePool implements AsyncConfigurer {
    private final static Logger log = LoggerFactory.getLogger(AsyncTaskExecutePool.class);

    /**
     * rejection-policy：当pool已经达到max size的时候，如何处理新任务
     * ABORT: 抛出TaskRejectedException，然后不执行
     * DISCARD: 不执行，也不抛出异常
     * DISCARD_OLDEST: 丢弃queue中最旧的那个任务
     * CALLER_RUNS:不在新线程中执行任务，而是由调用者所在的线程来执行
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); //最小线程数
        executor.setMaxPoolSize(10); //最大线程数，queue占满后会新建线程处理任务，但是总线程数不会超过maxSize
        executor.setQueueCapacity(25); //队列容量，coreSize占满后，task放入queue
        executor.setKeepAliveSeconds(60); //超过coreSize的那些线程，在完成task后多少时间内会被结束掉
        executor.setThreadNamePrefix("IChatTask-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    // 异步任务中异常处理
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable arg0, Method arg1, Object... arg2) {
                log.error("==========================" + arg0.getMessage() + "=======================", arg0);
                log.error("exception method:" + arg1.getName());
            }
        };
    }
}
