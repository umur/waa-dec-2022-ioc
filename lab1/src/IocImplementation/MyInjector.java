package IocImplementation;

import MyAnnotations.MyAutowired;
import MyAnnotations.MyBean;
import MyExceptions.BeanNotFoundException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MyInjector {

    private final static Map<String, Object> autoInstances = new HashMap<>();

    static {
        createRequiredInstances();
    }
    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void createRequiredInstances() {

        String packageName = MyInjector.class.getPackageName();

        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        Set<Class> registeredClasses = reader.lines()
                .filter(e -> e.endsWith(".class"))
                .map(e -> getClass(e, packageName))
                .filter(e -> e.isAnnotationPresent(MyBean.class))
                .collect(Collectors.toSet());

//        registeredClasses.forEach(System.out::println);

        for (Class clazz : registeredClasses) {
            for (Field field : clazz.getDeclaredFields()) {
                if(field.isAnnotationPresent(MyAutowired.class)) {
                    try {
                        Class className = field.getType();
                        if(registeredClasses.contains(className)) {
//                            System.out.println(className.getSimpleName());
                            autoInstances.put(className.getSimpleName(), className.newInstance());
                        }

                    }
                    catch(InstantiationException | IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public Object getBean(Class clazz) throws BeanNotFoundException {
        if (autoInstances.get(clazz.getSimpleName()) == null)
            throw new BeanNotFoundException("BeanNotFoundException: Object not found for '" + clazz.getName() + "'");

        return autoInstances.get(clazz.getSimpleName());
    }
}
