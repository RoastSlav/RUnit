package Functions;

import java.util.function.Supplier;

public interface ThrowingSupplier <T>{
    T get() throws Throwable;
}
