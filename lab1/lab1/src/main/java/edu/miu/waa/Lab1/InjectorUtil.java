package edu.miu.waa.Lab1;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class InjectorUtil {
    private InjectorUtil() {
        super();
    }

    public static void autowire(MyInjector injector, Class<?> clazz, Object classInstance) throws InstantiationException, IllegalAccessException {
        Set<Field> fields = findFields(clazz);
        for (Field field : fields) {


            Object fieldInstance = injector.getBeanInstance(field.getType(), field.getName(), null);
            System.out.println("Field Instance " + fieldInstance);

            field.set(classInstance, fieldInstance);
            System.out.println(fieldInstance.getClass().getName());
            autowire(injector, fieldInstance.getClass(), fieldInstance);
        }
    }

    private static Set<Field> findFields(Class<?> clazz) {
        Set<Field> set = new HashSet<>();

        while (clazz != null) {
            for(Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(MyAutowired.class)) {
                    field.setAccessible(true);
                    set.add(field);
                }
            }
            clazz = clazz.getSuperclass();
        }

        return set;

    }
}
