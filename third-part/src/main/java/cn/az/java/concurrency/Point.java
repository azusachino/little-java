package cn.az.java.concurrency;

import java.util.concurrent.locks.StampedLock;

/**
 * @author az
 * @since 2020-03-26
 */
public class Point {
    private double x, y;
    private final StampedLock sl = new StampedLock();


    void move(double deltaX, double deltaY) {
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    // a read-only method

    double distanceFromOrigin() {
        long stamp = sl.tryOptimisticRead();
        try {
            retryHoldingLock:
            for (; ; stamp = sl.readLock()) {
                if (stamp == 0L) {
                    continue retryHoldingLock;
                }
                // possibly racy reads
                double currentX = x;
                double currentY = y;
                if (!sl.validate(stamp)) {
                    continue retryHoldingLock;
                }
                return Math.hypot(currentX, currentY);
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) {
                sl.unlockRead(stamp);
            }
        }
    }


    void moveIfAtOrigin(double newX, double newY) {
        long stamp = sl.tryOptimisticRead();
        try {
            retryHoldingLock:
            for (; ; stamp = sl.writeLock()) {
                if (stamp == 0L) {
                    continue retryHoldingLock;
                }
                // possibly racy reads
                double currentX = x;
                double currentY = y;
                if (!sl.validate(stamp)) {
                    continue retryHoldingLock;
                }
                if (currentX != 0.0 || currentY != 0.0) {
                    break;
                }
                stamp = sl.tryConvertToWriteLock(stamp);
                if (stamp == 0L) {
                    continue retryHoldingLock;
                }
                // exclusive access
                x = newX;
                y = newY;
                return;
            }
        } finally {
            if (StampedLock.isWriteLockStamp(stamp)) {
                sl.unlockWrite(stamp);
            }
        }
    }


/*    void moveIfAtOrigin(double newX, double newY) {
        long stamp = sl.readLock();
        try {
            while (x == 0.0 && y == 0.0) {
                long ws = sl.tryConvertToWriteLock(stamp);
                if (ws != 0L) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        } finally {
            sl.unlock(stamp);
        }
    }*/
}
