package org.example.utils.exec;

public interface ExecutableSupplier<T> {

    T exec() throws Throwable;

    default T execSilent() {
        try {
            return exec();
        } catch (Throwable ignore) { }
        return null;
    }

    static <T> T exec(ExecutableSupplier<T> consumer) throws Throwable {
        return consumer.exec();
    }

    static <T> T execSilent(ExecutableSupplier<T> consumer) {
        return consumer.execSilent();
    }
}
