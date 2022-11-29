package annotations;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class MyInjector {
    HashMap<Class, Object> objectMap = new HashMap<>();

    public void getReflection() {
//        findAllClassesUsingClassLoader("classes").stream().forEach(System.out::println);
        List<Class> myClasses = findAllClassesUsingClassLoader("classes");

        for(Class cc: myClasses) {
            try {
                Object bean = cc.getAnnotation(MyBean.class);
                if(bean != null) {
                    Object oo = cc.getDeclaredConstructor().newInstance();
                    objectMap.put(cc, oo);
                }
            } catch(Exception e) {

            }
        }
    }

    public Object getBean(Class cc) throws IllegalAccessException {
        if(objectMap.containsKey(cc)) {
            Object oo = objectMap.get(cc);
            Field[] fields = cc.getDeclaredFields();
            for(int i = 0; i < fields.length; i++) {
                MyAutowired fieldAnnotation = fields[i].getAnnotation(MyAutowired.class);
                if(fieldAnnotation != null) {
                    if(!fields[i].canAccess(oo)) {
                        fields[i].setAccessible(true);
                    }
                    Class fieldType = fields[i].getType();
                    fields[i].set(oo, objectMap.get(fieldType));
                }
            }
            return  oo;
        } else {
            return null;
        }
    }

    public List<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toList());
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}