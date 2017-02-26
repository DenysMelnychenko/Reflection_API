import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by aspir on 26.02.2017.
 */
public class TestingLibrary {

    public static void main(String[] args) {
        TestClass testExample = new TestClass();
        Class tClass = testExample.getClass();
        Method[] methods = tClass.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeTests.class)) {
                try {
                    method.invoke(testExample);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class) && method.isAnnotationPresent(Ignore.class)) {
                System.out.println("Test " + method.getName() + " " + "ignored.");
            } else if (method.isAnnotationPresent(Test.class)) {
                try {
                    method.invoke(testExample);
                    System.out.println("Test " + method.getName() + " " + "passed.");
                } catch (IllegalAccessException e) {
                    System.out.println("Test " + method.getName() + " " + "failed.");
                } catch (InvocationTargetException e) {
                    System.out.println("Test " + method.getName() + " " + "failed.");
                }
            }
        }
        for (Method method : methods) {
            if (method.isAnnotationPresent(AfterTests.class)) {
                try {
                    method.invoke(testExample);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


