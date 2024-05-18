package org.example.utils.exec;

public interface ExecutableConsumer {

    void exec() throws Throwable;

    default void execSilent() {
        try {
            exec();
        } catch (Throwable ignore) { }
    }

    static void exec(ExecutableConsumer consumer) throws Throwable {
        consumer.exec();
    }

    static void execSilent(ExecutableConsumer consumer) {
        consumer.execSilent();
    }
}
