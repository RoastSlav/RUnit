import Anotations.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class RUnitCore {
    public static void runTests(Class<?> clazz) {
        ArrayList<Method> testMethods = new ArrayList<>();
        ArrayList<Method> beforeClass = new ArrayList<>();
        ArrayList<Method> afterClass = new ArrayList<>();
        ArrayList<Method> beforeEach = new ArrayList<>();
        ArrayList<Method> afterEach = new ArrayList<>();

        Method[] methods = clazz.getDeclaredMethods();
        sortMethods(methods, testMethods, beforeClass, afterClass, beforeEach, afterEach);
        runMethods(clazz, testMethods, beforeEach, afterEach, beforeClass, afterClass);
    }

    private static void sortMethods(Method[] methods, ArrayList<Method> testMethods,
                                    ArrayList<Method> beforeClass, ArrayList<Method> afterClass,
                                    ArrayList<Method> beforeEach, ArrayList<Method> afterEach) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
                continue;
            }
            if (method.isAnnotationPresent(BeforeClass.class)) {
                addNonStaticMethod(method, beforeClass, BeforeClass.class);
                continue;
            }
            if (method.isAnnotationPresent(AfterClass.class)) {
                addNonStaticMethod(method, afterClass, AfterClass.class);
                continue;
            }
            if (method.isAnnotationPresent(BeforeEach.class)) {
                addNonStaticMethod(method, beforeEach, BeforeEach.class);
                continue;
            }
            if (method.isAnnotationPresent(AfterEach.class)) {
                addNonStaticMethod(method, afterEach, AfterEach.class);
            }
        }
    }

    private static void addNonStaticMethod(Method method, ArrayList<Method> methods, Class<?> annotation) {
        if (!Modifier.isStatic(method.getModifiers())) {
            methods.add(method);
        } else {
            System.out.println(annotation.getName() + " annotation can only be used on non-static methods. Method " + method.getName() + " is static.");
        }
    }

    private static void runMethods(Class<?> clazz,
                                   ArrayList<Method> testMethods,
                                   ArrayList<Method> beforeEach, ArrayList<Method> afterEach,
                                   ArrayList<Method> beforeClass, ArrayList<Method> afterClass) {
        Object instance = null;
        try {
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            instance = declaredConstructor.newInstance();
        } catch (Exception e) {
            //todo what do to about this?
            e.printStackTrace();
        }


        for (Method method : beforeClass) {
            invokeMethod(method, instance);
        }

        for (Method method : testMethods) {
            for (Method beforeMethod : beforeEach) {
                invokeMethod(beforeMethod, instance);
            }

            invokeMethod(method, instance);

            for (Method afterMethod : afterEach) {
                invokeMethod(afterMethod, instance);
            }
        }

        for (Method method : afterClass) {
            invokeMethod(method, instance);
        }
    }

    private static void invokeMethod(Method method, Object instance) {
        try {
            method.setAccessible(true);
            if (method.isAnnotationPresent(RepeatedTest.class)) {
                int times = method.getAnnotation(RepeatedTest.class).value();
                for (int i = 0; i < times; i++) {
                    method.invoke(instance);
                }
            } else {
                method.invoke(instance);
            }
        } catch (Exception e) {
            Description desc = method.getAnnotation(Description.class);
            if (desc != null)
                System.out.println(desc.value());

            System.out.println(e.getCause().getMessage());
            e.getCause().printStackTrace();
        }
    }
}
