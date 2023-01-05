package Assertions;

import Exceptions.AssertionFailedError;
import Functions.Executable;
import Functions.ThrowingSupplier;

import java.time.Duration;
import java.util.function.Supplier;

public class Assertions {
    public static void assertEquals(String expected, String actual) {
        assertEquals(expected, actual, (String) null);
    }

    public static void assertEquals(String expected, String actual, String message) {
        if (!expected.equals(actual)) {
            String printMessage = message == null ?
                    "Expected: " + expected + "\nActual: " + actual :
                    message + "==>\nExpected: " + expected + "\nActual: " + actual;
            throw new AssertionFailedError(printMessage);
        }
    }

    public static void assertEquals(String expected, String actual, Supplier<String> message) {
        assertEquals(expected, actual, message.get());
    }

    public static void assertEquals(double expected, double actual) {
        assertEquals(expected, actual, (String) null);
    }

    public static void assertEquals(double expected, double actual, String message) {
        if (expected != actual) {
            String printMessage = message == null ?
                    "Expected: " + expected + "\nActual: " + actual :
                    message + "==>\nExpected: " + expected + "\nActual: " + actual;
            throw new AssertionFailedError(printMessage);
        }
    }

    public static void assertEquals(double expected, double actual, Supplier<String> message) {
        assertEquals(expected, actual, message.get());
    }

    public static void assertEquals(int expected, int actual) {
        assertEquals(expected, actual, (String) null);
    }

    public static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            String printMessage = message == null ?
                    "Expected: " + expected + "\nActual: " + actual :
                    message + "==>\nExpected: " + expected + "\nActual: " + actual;
            throw new AssertionFailedError(printMessage);
        }
    }

    public static void assertEquals(int expected, int actual, Supplier<String> message) {
        assertEquals(expected, actual, message.get());
    }

    public static void assertEquals(boolean expected, boolean actual) {
        assertEquals(expected, actual, (String) null);
    }

    public static void assertEquals(boolean expected, boolean actual, String message) {
        if (expected != actual) {
            String printMessage = message == null ?
                    "Expected: " + expected + "\nActual: " + actual :
                    message + "==>\nExpected: " + expected + "\nActual: " + actual;
            throw new AssertionFailedError(printMessage);
        }
    }

    public static void assertEquals(boolean expected, boolean actual, Supplier<String> message) {
        assertEquals(expected, actual, message.get());
    }

    public static void assertEquals(Object expected, Object actual) {
        assertEquals(expected, actual, (String) null);
    }

    public static void assertEquals(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            String printMessage = message == null ?
                    "Expected: " + expected + "\nActual: " + actual :
                    message + "==>\nExpected: " + expected + "\nActual: " + actual;
            throw new AssertionFailedError(printMessage);
        }
    }

    public static void assertEquals(Object expected, Object actual, Supplier<String> message) {
        assertEquals(expected, actual, message.get());
    }

    public static void assertTrue(boolean condition) {
        assertTrue(condition, (String) null);
    }

    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            String printMessage = message == null ?
                    "Expected: true\nActual: false" :
                    message + "==>\nExpected: true\nActual: false";
            throw new AssertionFailedError(printMessage);
        }
    }

    public static void assertTrue(boolean condition, Supplier<String> message) {
        assertTrue(condition, message.get());
    }

    public static void assertFalse(boolean condition) {
        assertFalse(condition, (String) null);
    }

    public static void assertFalse(boolean condition, String message) {
        if (condition) {
            String printMessage = message == null ?
                    "Expected: false\nActual: true" :
                    message + "==>\nExpected: false\nActual: true";
            throw new AssertionFailedError(printMessage);
        }
    }

    public static void assertFalse(boolean condition, Supplier<String> message) {
        assertFalse(condition, message.get());
    }

    public static void assertNotNull(Object object) {
        assertNotNull(object, (String) null);
    }

    public static void assertNotNull(Object object, String message) {
        if (object == null) {
            String printMessage = message == null ?
                    "Expected: not null\nActual: null" :
                    message + "==>\nExpected: not null\nActual: null";
            throw new AssertionFailedError(printMessage);
        }
    }

    public static void assertNotNull(Object object, Supplier<String> message) {
        assertNotNull(object, message.get());
    }

    public static void assertNull(Object object) {
        assertNull(object, (String) null);
    }

    public static void assertNull(Object object, String message) {
        if (object != null) {
            String printMessage = message == null ?
                    "Expected: null\nActual: not null" :
                    message + "==>\nExpected: null\nActual: not null";
            throw new AssertionFailedError(printMessage);
        }
    }

    public static void assertNull(Object object, Supplier<String> message) {
        assertNull(object, message.get());
    }

    public static void assertThrows(Class<? extends Throwable> expected, Runnable runnable) {
        assertThrows(expected, runnable, (String) null);
    }

    public static void assertThrows(Class<? extends Throwable> expected, Runnable runnable, String message) {
        try {
            runnable.run();
        } catch (Throwable throwable) {
            if (!expected.isInstance(throwable)) {
                String printMessage = message == null ?
                        "Expected: " + expected + "\nActual: " + throwable :
                        message + "==>\nExpected: " + expected + "\nActual: " + throwable;
                throw new AssertionFailedError(printMessage);
            }
            return;
        }
        String printMessage = message == null ?
                "Expected: " + expected + "\nActual: no exception" :
                message + "==>\nExpected: " + expected + "\nActual: no exception";
        throw new AssertionFailedError(printMessage);
    }

    public static void assertThrows(Class<? extends Throwable> expected, Runnable runnable, Supplier<String> message) {
        assertThrows(expected, runnable, message.get());
    }

    public static void assertTimeout(Duration timeout, Executable executable) {
        assertTimeout(timeout, executable, (String) null);
    }

    public static void assertTimeout(Duration timeout, Executable executable, String message) {
        long start = System.currentTimeMillis();
        try {
            executable.execute();
        } catch (Throwable throwable) {
            throw new AssertionFailedError(throwable);
        }
        long end = System.currentTimeMillis();
        if (end - start > timeout.toMillis()) {
            String printMessage = message == null ?
                    "Expected: " + timeout + "ms\nActual: " + (end - start) + "ms" :
                    message + "==>\nExpected: " + timeout + "ms\nActual: " + (end - start) + "ms";
            throw new AssertionFailedError(printMessage);
        }
    }

    public static void assertTimeout(Duration timeout, Executable executable, Supplier<String> message) {
        assertTimeout(timeout, executable, message.get());
    }

    public static <T> void assertTimeout(Duration timeout, ThrowingSupplier<T> executable) {
        assertTimeout(timeout, executable, (String) null);
    }

    public static <T> void assertTimeout(Duration timeout, ThrowingSupplier<T> executable, String message) {
        long start = System.currentTimeMillis();
        try {
            executable.get();
        } catch (Throwable throwable) {
            throw new AssertionFailedError(throwable);
        }
        long end = System.currentTimeMillis();
        if (end - start > timeout.toMillis()) {
            String printMessage = message == null ?
                    "Expected: " + timeout + "ms\nActual: " + (end - start) + "ms" :
                    message + "==>\nExpected: " + timeout + "ms\nActual: " + (end - start) + "ms";
            throw new AssertionFailedError(printMessage);
        }
    }

    public static <T> void assertTimeout(Duration timeout, ThrowingSupplier<T> executable, Supplier<String> message) {
        assertEquals(timeout, executable, message.get());
    }

    public static void assertTimeoutPreemptively(Duration timeout, Executable executable) {
        assertTimeoutPreemptively(timeout, executable, (String) null);
    }

    public static void assertTimeoutPreemptively(Duration timeout, Executable executable, String message) {
        Thread thread = new Thread(() -> {
            try {
                executable.execute();
            } catch (Throwable throwable) {
                throw new AssertionFailedError(throwable);
            }
        });
        thread.start();
        try {
            thread.join(timeout.toMillis());
        } catch (InterruptedException e) {
            throw new AssertionFailedError(e);
        }
        if (thread.isAlive()) {
            String printMessage = message == null ?
                    "Expected: " + timeout + "ms\nActual: " + timeout.toMillis() + "ms" :
                    message + "==>\nExpected: " + timeout + "ms\nActual: " + timeout.toMillis() + "ms";
            throw new AssertionFailedError(printMessage);
        }
    }

    public static void assertTimeoutPreemptively(Duration timeout, Executable executable, Supplier<String> message) {
        assertTimeoutPreemptively(timeout, executable, message.get());
    }

    public static <T> void assertTimeoutPreemptively(Duration timeout, ThrowingSupplier<T> executable) {
        assertTimeoutPreemptively(timeout, executable, (String) null);
    }

    public static <T> void assertTimeoutPreemptively(Duration timeout, ThrowingSupplier<T> executable, String message) {
        Thread thread = new Thread(() -> {
            try {
                executable.get();
            } catch (Throwable throwable) {
                throw new AssertionFailedError(throwable);
            }
        });
        thread.start();
        try {
            thread.join(timeout.toMillis());
        } catch (InterruptedException e) {
            throw new AssertionFailedError(e);
        }
        if (thread.isAlive()) {
            thread.interrupt();
            String printMessage = message == null ?
                    "Expected: " + timeout + "ms\nActual: " + timeout.toMillis() + "ms" :
                    message + "==>\nExpected: " + timeout + "ms\nActual: " + timeout.toMillis() + "ms";
            throw new AssertionFailedError(printMessage);
        }
    }

    public static <T> void assertTimeoutPreemptively(Duration timeout, ThrowingSupplier<T> executable, Supplier<String> message) {
        assertTimeoutPreemptively(timeout, executable, message.get());
    }
}
