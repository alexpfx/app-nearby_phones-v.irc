package br.com.alexpfx.irctest.app.mvp.model.interactor.executor;

import br.com.alexpfx.irctest.app.mvp.model.interactor.Interactor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexandre on 05/07/15.
 */
public class ThreadExecutor implements Executor {

    public enum ThreadExecutorSingleton {
        INSTANCE;

        private ThreadExecutor instance = new ThreadExecutor();

        public ThreadExecutor get() {
            return instance;
        }

    }

    public static final int MAX_POOL_SIZE = 4;
    public static final int CORE_POOL_SIZE = 2;
    public static final int KEEP_ALIVE_TIME = 120;
    public static final LinkedBlockingDeque<Runnable> WORK_QUEUE = new LinkedBlockingDeque<Runnable>();
    private ThreadPoolExecutor threadPoolExecutor;

    private ThreadExecutor() {
        int corePoolSize = CORE_POOL_SIZE;
        int maxPoolSize = MAX_POOL_SIZE;
        long keepAliveTime = KEEP_ALIVE_TIME;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQuee = WORK_QUEUE;
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, workQuee);
    }

    @Override
    public void run(final Interactor interactor) {
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                interactor.run();
            }
        });
    }
}
