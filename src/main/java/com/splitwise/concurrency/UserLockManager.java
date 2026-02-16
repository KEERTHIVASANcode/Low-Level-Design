
package com.splitwise.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Provides fine-grained locking per user ID.
 * Prevents deadlock using ordered locking.
 */
public class UserLockManager {

    private final ConcurrentHashMap<String, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    private ReentrantLock getLock(String userId) {
        return lockMap.computeIfAbsent(userId, id -> new ReentrantLock());
    }

    public void lockUsers(String id1, String id2) {
        String first = id1.compareTo(id2) < 0 ? id1 : id2;
        String second = first.equals(id1) ? id2 : id1;

        getLock(first).lock();
        getLock(second).lock();
    }

    public void unlockUsers(String id1, String id2) {
        String first = id1.compareTo(id2) < 0 ? id1 : id2;
        String second = first.equals(id1) ? id2 : id1;

        getLock(second).unlock();
        getLock(first).unlock();
    }
}
