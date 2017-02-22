import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;

import static java.lang.reflect.Modifier.INTERFACE;
import static java.lang.reflect.Modifier.isFinal;
import static java.lang.reflect.Modifier.isStatic;

public class Main {

    private static User user = new User("Denis", "Melnychenko", "07.10.1989");

    public static void main(String[] args) throws Exception {
        Class uClass = user.getClass();
        Field[] fields = uClass.getDeclaredFields();
        Method[] methods = uClass.getDeclaredMethods();


        System.out.println("All Fields");
        for (Field field : fields) {
            System.out.println("Modifier: " + Modifier.toString(field.getModifiers()) + " " +
                    "Type: " + field.getType() + " " + "Name: " + field.getName());
        }

        System.out.println();
        System.out.println("Static Fields");
        for (Field field : fields) {
            if (isStatic(field.getModifiers())) {
                System.out.println(field.getName());
            }
        }

        System.out.println();
        System.out.println("Final field");
        for (Field field : fields) {
            if (isFinal(field.getModifiers())) {
                setFinalStatic(field, "10.10.1985");
                System.out.println("new modificator value: " + Modifier.toString(field.getModifiers()));
                System.out.println(field.get(user));
            }
        }
        System.out.println();

        for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for(Annotation annotation : annotations){
                if(annotation instanceof Override){
                    System.out.println(method.getName());
                }
            }

        }

    }

    static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(user, newValue);
    }

    public static Collection<Method> methodWithAnnotation(Class<?> classType, Class<? extends Annotation> annotationClass) {

        if (classType == null) throw new NullPointerException("classType must not be null");

        if (annotationClass == null) throw new NullPointerException("annotationClass must not be null");

        Collection<Method> result = new ArrayList<Method>();
        for (Method method : classType.getMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                result.add(method);
            }
        }
        return result;
    }

}



