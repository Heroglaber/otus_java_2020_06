package ru.otus.testsframework;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestsLauncher {

    static final String testClassName = "ru.otus.tests.WeddingServiceImplTest";

    public static void main(String[] args) {
        Class<?> testClass;
        try {
            testClass = Class.forName(testClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        //Collecting all methods of testClass with annotations Before, After, Test
        List<Method> tests = new ArrayList<>();
        List<Method> before = new ArrayList<>();
        List<Method> after = new ArrayList<>();
        findTests(testClass, tests, before, after);

        int testsAmount = tests.size();
        int testsFailed = 0;
        //Executing test methods of the testClass
        for(Method testMethod : tests) {
            if(executeTest(testClass, testMethod, before, after)) {
                System.out.println(String.format("TEST %s PASSED", testMethod.getName()));
            }
            else {
                testsFailed++;
                System.out.println(String.format("TEST %s FAILED", testMethod.getName()));
            }
        }

        System.out.println(String.format("FAILED %d/%d TESTS", testsFailed, testsAmount));
    }

    public static void findTests(Class<?> testClass, List<Method> tests, List<Method> before, List<Method> after) {
        Method[] methodsAll = testClass.getDeclaredMethods();

        for(Method method : methodsAll) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            if(Arrays.stream(annotations).anyMatch(annotation ->
                    annotation.annotationType().equals(Test.class)))
                tests.add(method);
            if(Arrays.stream(annotations).anyMatch(annotation ->
                    annotation.annotationType().equals(Before.class)))
                before.add(method);
            if(Arrays.stream(annotations).anyMatch(annotation ->
                    annotation.annotationType().equals(After.class)))
                after.add(method);
        }
    }

    public static boolean executeTest(Class<?> testClass, Method testMethod, List<Method> before, List<Method> after) {
        boolean passed = true;
        Constructor<?> defaultConstructor;
        Object testClassObject = null;
        try {
            defaultConstructor = testClass.getConstructor();
            testClassObject = defaultConstructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //Invoke all before methods on testObject
        for(Method method : before) {
            try {
                method.invoke(testClassObject);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        try {
            testMethod.invoke(testClassObject);
        } catch (Exception e) {
            passed = false;
        }

        //Invoke all after methods on testObject
        for(Method method : after) {
            try {
                method.invoke(testClassObject);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return passed;
    }
}
