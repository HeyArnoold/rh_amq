package org.example.utils.exec;

import org.example.utils.ThrowUtils;

public class Executor {

    public static <T> T supply(ExecutableSupplier<T> goal) {
        try {
            return goal.exec();
        } catch (Throwable throwable) {
            if (throwable instanceof RuntimeException) {
                throw (RuntimeException) throwable;
            }
            if (throwable instanceof AssertionError) {
                throw (AssertionError) throwable;
            }
            throw new WrappedException(ThrowUtils.getShortStackTraceMessage(throwable));
        }
    }

    public static <T> T exec(ExecutableSupplier<T> goal) {
        return supply(goal);
    }

    public static void exec(ExecutableConsumer goal) {
        try {
            goal.exec();
        } catch (Throwable throwable) {
            if (throwable instanceof RuntimeException) {
                throw (RuntimeException) throwable;
            }
            if (throwable instanceof AssertionError) {
                throw (AssertionError) throwable;
            }
            throw new WrappedException(throwable);
        }
    }

    public static void execQuietly(ExecutableConsumer goal) {
        try {
            goal.exec();
        } catch (Throwable ignore) {

        }
    }
}
