package edu.miu;

import java.util.HashMap;

public class BeanFactory {

    //this is the map hold the instance of class
    private static  HashMap<String,Object> beanMap = new HashMap<>();

    Object getBean(Class clazz) throws BeanNotFoundException{
        Object instance = beanMap.get(clazz.getName());
        if(instance==null){
            throw  new BeanNotFoundException(String.format("bean:%s not found",clazz.getName()));
        }
        return instance;
    }
}
