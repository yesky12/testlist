package com.leo.test.concurrency;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by gonglin on 12/11/13.
 */
public class OneShotLatch {
    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }


    private class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected int tryAcquireShared(int arg) {
            // 如果闭锁是开的 (state == 1)，那么这个操作将成功，否则失败
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1); //打开闭锁
            return true;// 其它纯种可以访问
        }
    }
}
