package Ioc;

import Annotations.MyAutoWired;
import Annotations.MyBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyInjector {

    public final  Map<String,Object> classObjects = new HashMap<String,Object>();

    public void MyAutoWiredObjects() {

        //Get PackageName
        MyInjector m = new MyInjector();
        String packageName = m.getClass().getPackageName();

        //Get all class as Stream from classLoader
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        List<Class> classSet = reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toList());

        //Check For Annotations and if annotation is found insert into hashmap

        try
        {
            for (Class item : classSet) {
                Class<?> classItem = ClassLoader.getSystemClassLoader().loadClass(item.getName());
                if(item.isAnnotationPresent(MyBean.class)!= true)
                {
                    continue;
                }
                for(Field itemField: item.getDeclaredFields())
                {
                    if(itemField.isAnnotationPresent(MyAutoWired.class))
                    {
                        //System.out.println(itemField.getDeclaringClass());
                        classObjects.put(classItem.getName(),itemField.getDeclaringClass().newInstance());
                    }
                }

            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex )
        {
            System.out.println(ex.getMessage());
        }

    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf(".")));
        } catch (ClassNotFoundException e) {
            System.out.println("className not found");
            return null;
        }
    }
    public Object getBean(Class c) throws  BeanNotFoundException
    {
        if(classObjects.get(c.getName())!=null)
        {
            return classObjects.get(c.getName());
        }
        else {
            throw new BeanNotFoundException();
        }
    }

    public static void main(String[] args) {

    }
}
