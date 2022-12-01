import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import annotations.MyAutoWired;
import exception.BeanNotFoundException;

public class MyInjector {
    Map<String, Object> instances = new HashMap<String, Object>();

    public static void main(String[] args) {
        MyInjector injector = new MyInjector();
        injector.run();
        try {
            injector.getBean(TestClass.class);
        } catch (BeanNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("running");
        String packageName = this.getClass().getPackageName();
        List<Class> allClasses = getPackageClasses(packageName);

        for (Class cl : allClasses) {
            if (cl.isAnnotationPresent(MyAutoWired.class)) {
                for (Field field : cl.getDeclaredFields()) {
                    try {
                        instances.put(cl.getName(), cl.getConstructor().newInstance());
                    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                            | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }

    public Object getBean(Class clazz) throws BeanNotFoundException {
        if (instances.get(clazz) == null) {
            throw new BeanNotFoundException("Bean doesn't exist");
        }
        return instances.get(clazz.getName());
    }

    static List<Class> getPackageClasses(String packageName) {
        List<Class> classes = new ArrayList<Class>();
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll(".", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        classes = reader.lines().filter(lines -> lines.endsWith(".class")).map(line -> getClass(line, packageName))
                .collect(Collectors.toList());

        return classes;
    }

    public static Class getClass(String line, String packageName) {
        try {
            return Class.forName(packageName + "." + line.substring(0, line.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

}
