package edu.miu.waa.w1d1.ioc.injectors;
import edu.miu.waa.w1d1.ioc.annotations.MyAutowired;
import edu.miu.waa.w1d1.ioc.annotations.MyBean;
import edu.miu.waa.w1d1.ioc.exceptions.BeanNotFoundException;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyInjector{

    HashMap<String,Object> myAutowiredInstances=new HashMap<String,Object>();

    public MyInjector(String packagePath){
        List<Class<?>> allClassesInPackage=getClassesInPackage(packagePath);
        allClassesInPackage.stream()
                .filter(c->{
                    Field[] fields=c.getDeclaredFields();
                    boolean flag=false;
                    for(Field f:fields){
                        if(f.isAnnotationPresent(MyAutowired.class)){
                            //We can inject the dependency after checking if field type has MyBean annotation.
                            //But it's not the requirement for now I guess.
                            //Assignment only wanted if class has MyAutowired annotation create its object and save in hashmap
                            flag=true;
                        }
                    }
                    return flag;
                })
                .forEach(c-> {
                    try {
                        myAutowiredInstances.put(c.getName(),c.getDeclaredConstructor().newInstance());
                    } catch (InstantiationException e) {
                        //throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        //throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        //throw new RuntimeException(e);
                    } catch (NoSuchMethodException e) {
                        //throw new RuntimeException(e);
                    }
                });
    }
    public List<Class<?>> getClassesInPackage(String packageName) {
       URL root = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));
       // Filter .class files.
       File[] files = new File(root.getFile()).listFiles(new FilenameFilter() {
           public boolean accept(File dir, String name) {
               return name.endsWith(".class");
           }
        });

        List<Class<?>> classes=new ArrayList<Class<?>>();
       // Find classes implementing ICommand.
        for (File file : files) {
           String className = file.getName().replaceAll(".class$", "");
           try {
                Class<?> cls = Class.forName(packageName + "." + className);
                classes.add(cls);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        return classes;
   }

   public <T> T getBean(Class<T> clazz){
        T obj = (T) myAutowiredInstances.get(clazz.getName());
        if(obj!=null){
            return obj;
        }else{
            throw new BeanNotFoundException("BeanNotFoundException for: "+clazz.getName());
        }
   }


}
