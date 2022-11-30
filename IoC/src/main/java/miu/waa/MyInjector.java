package miu.waa;

/**
 * @author Ankhbayar Azzaya
 */

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyInjector {

    private Map<Class<?>, Object> instancesMap = new HashMap<>();

    public MyInjector() {
        injectAllClasses();
    }

    public void injectAllClasses() {
        String packageName = MyInjector.class.getPackageName();
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> classSet =  reflections.getTypesAnnotatedWith(MyBean.class);

        for (Class<?> clazz: classSet) {
            if (!instancesMap.containsKey(clazz)) {
                createInstances(clazz);
            }
        }
    }

    public void createInstances(Class<?> clazz) {
        System.out.println("createInstances: " + clazz.getSimpleName());
        try {
            for (Field field: clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(MyAutoWired.class)) {
                    Class<?> fieldClass = field.getType();
                    System.out.println("fieldName: " + field.getName());
//                    System.out.println("fieldClass: " + fieldClass.getSimpleName());
                    createInstances(fieldClass);

                    field.setAccessible(true);
//                    field.set(this, getBean(fieldClass));
                }
            }

            System.out.println("Adding to map: " + clazz.getName());
            instancesMap.put(clazz, clazz.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Object getBean(Class<?> clazz) throws BeanNotFoundException {
        System.out.println("getBean: " + clazz.getName());
        if (instancesMap.containsKey(clazz))
            return instancesMap.get(clazz);
        throw new BeanNotFoundException("bean not found");
    }
}
