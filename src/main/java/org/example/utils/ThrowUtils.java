package org.example.utils;

import org.junit.platform.commons.util.Preconditions;

public class ThrowUtils {

    public static String getShortStackTraceMessage(Throwable throwable) {
        return getShortStackTraceMessage(throwable, false);
    }


    /**
     * Read the stacktrace of the supplied {@link Throwable} into a String
     */
    public static String getShortStackTraceMessage(Throwable throwable, boolean isCause) {
        Preconditions.notNull(throwable, "Throwable must not be null");
        if (throwable instanceof AssertionError) {
            if (isCause) {
                return "Caused by " + throwable.getClass().getSimpleName() + ": " + throwable.getMessage();
            } else {
                return throwable.getClass().getSimpleName() + ": " + throwable.getMessage();
            }
        }
        String currentMsg;
        if (isCause) {
            if (throwable.getStackTrace().length == 0) {
                currentMsg = "Caused by " + throwable.getClass().getSimpleName() + ": " + throwable.getMessage();
            } else {
                currentMsg = "Caused by " + throwable.getClass().getSimpleName() + ": " +
                        throwable.getMessage() + "\n  " + throwable.getStackTrace()[0].toString();
            }
        } else {
            if (throwable.getStackTrace().length == 0) {
                currentMsg = throwable.getClass().getSimpleName() + ": " + throwable.getMessage();
            } else {
                currentMsg = throwable.getClass().getSimpleName() + ": " +
                        throwable.getMessage() + "\n  " + throwable.getStackTrace()[0].toString();
            }
        }
        Throwable cause = throwable.getCause();
        if (cause != null) {
            return currentMsg + "\n" + getShortStackTraceMessage(cause, true);
        }
        return currentMsg;
    }

    private ThrowUtils() { }
}
