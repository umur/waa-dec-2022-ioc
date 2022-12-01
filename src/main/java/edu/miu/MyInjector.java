package edu.miu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyInjector {
    Map<String, Object> instanceForAllMyBeanClass = new HashMap<String,Object>() ;
    Predicate<Field> isAnnotationPresent = h->h.isAnnotationPresent(MyAutowired.class);

    Predicate<Class> finallyCreateClassFilter = x->{
        Stream<Field> fieldStream = Arrays.stream(x.getDeclaredFields())
                .filter(isAnnotationPresent);
        return fieldStream.count()>=1;
    };
    Predicate<Class> firstCreateClassFilter = x->{
        Stream<Field> fieldStream = Arrays.stream(x.getDeclaredFields())
                .filter(y -> y.isAnnotationPresent(MyAutowired.class));
        return fieldStream.count()==0;
    };

    //scan all the classes under current package
    public Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {

        }
        return null;
    }

    public  void  createInstanceForAllMyBeanClass() throws IOException,NoSuchMethodException  {
        String packageName = this.getClass().getPackage().getName();
        //divide to 2 partion stream;
        //one is don't have any annotation on the field
        //another is have some annotation on the field
        List<Class> myBeansAnnotationClass = findAllClassesUsingClassLoader(packageName)
                .stream()
                .filter(x -> x.isAnnotationPresent(MyBean.class)).collect(Collectors.toList());

        Stream<Class> finallyCreateClass = myBeansAnnotationClass.stream().filter(finallyCreateClassFilter);
        Stream<Class> firstCreateClass = myBeansAnnotationClass.stream();


        firstCreateClass.forEach(x->{
            try {
                Constructor constructor = x.getConstructor();
                instanceForAllMyBeanClass.put(x.getName(),constructor.newInstance());
            } catch (NoSuchMethodException | InvocationTargetException|InstantiationException|IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        finallyCreateClass.forEach(x->{
            String name = x.getName();
            Object referInstance = instanceForAllMyBeanClass.get(name);

            Arrays.stream(x.getDeclaredFields())
                    .filter(isAnnotationPresent)
                    .forEach(h-> {
                        try {
                            Object bean = getBean(h.getType());
                            h.setAccessible(true);
                            h.set(referInstance,bean);
                        } catch (BeanNotFoundException |IllegalAccessException e) {

                        }
                    });

            instanceForAllMyBeanClass.put(name,referInstance);
        });
    }

    public Object getBean(Class clazz) throws BeanNotFoundException {
        Object instance = this.instanceForAllMyBeanClass.get(clazz.getName());
        if(instance==null){
            throw  new BeanNotFoundException(String.format("bean:%s not found",clazz.getName()));
        }
        return instance;
    }

}
