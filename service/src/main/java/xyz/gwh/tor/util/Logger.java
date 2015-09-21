package xyz.gwh.tor.util;

import android.util.Log;

/**
 * A friendlier wrapper for android.util.Log.
 * Logs message to logcat with a tag generated from the calling class and method.
 */
public final class Logger {

    /**
     * Calls LogUtils.log() with Log.VERBOSE level.
     */
    public static void v(String msg) {
        log(msg, Log.VERBOSE);
    }

    /**
     * Calls LogUtils.log() with Log.DEBUG level.
     */
    public static void d(String msg) {
        log(msg, Log.DEBUG);
    }

    /**
     * Calls LogUtils.log() with Log.INFO level.
     */
    public static void i(String msg) {
        log(msg, Log.INFO);
    }

    /**
     * Calls LogUtils.log() with Log.WARN level.
     */
    public static void w(String msg) {
        log(msg, Log.WARN);
    }

    /**
     * Calls LogUtils.log() with Log.ERROR level.
     */
    public static void e(String msg) {
        log(msg, Log.ERROR);
    }

    /**
     * Logs message to logcat with a tag generated from the calling class and method.
     * <p/>
     * Example output:
     * xyz.gwh.android.friendly D/MyActivity => foo()﹕ Hello world!
     */
    public static void log(String msg, int level) {
        final StackTraceElement[] elements = Thread.currentThread().getStackTrace();

        /*
         * The first three stack trace elements should be as follows:
         * [0] VMStack.getThreadStackTrace()
         * [1] Thread.getStackTrace()
         * [2] Logger.log()
         *
         * We iterate to the next non-Logger method and take that as
         * the class and method that called a Logger method.
         */
        int index = 3;
        for (; index < elements.length; index++) {
            if (!elements[index].getClassName().contains(Logger.class.getSimpleName())) {
                break;
            }
        }

        final StackTraceElement element = elements[index];
        final String canonicalClassName = element.getClassName();
        final String simpleClassName = canonicalClassName.substring(canonicalClassName.lastIndexOf('.') + 1);
        final String methodName = element.getMethodName() + "()";
        final String tag = simpleClassName + "." + methodName;

        switch (level) {
            case Log.VERBOSE:
                Log.v(tag, msg);
                break;
            case Log.DEBUG:
                Log.d(tag, msg);
                break;
            case Log.INFO:
                Log.i(tag, msg);
                break;
            case Log.WARN:
                Log.w(tag, msg);
                break;
            case Log.ERROR:
                Log.e(tag, msg);
                break;
            default:
                Log.d(tag, msg);
                break;
        }
    }

    private Logger() {
        // restrict instantiation
    }
}