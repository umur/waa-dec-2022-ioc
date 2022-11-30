package springsystem;

import springsystem.BeanNotFoundException;
import springsystem.MyAutowired;

import java.lang.reflect.Field;
import java.util.*;

public class MyInjector {

    Map<Class, Object> newMap = new HashMap<>();



    public Object getMyBean(Class clazz) throws BeanNotFoundException, IllegalAccessException {

        if (newMap.containsKey(clazz)) {
            Object classObj = newMap.get(clazz);
            List<Field> fieldList = (List<Field>) Arrays.stream(new List[]{Arrays.stream(classObj.getClass().getDeclaredFields()).toList()});

            for (Field field : fieldList) {
                MyAutowired annot = field.getAnnotation(MyAutowired.class);
                if (annot != null) {
                    if (!field.canAccess(classObj))
                        field.setAccessible(true);
                    Class clsType = field.getType();
                    field.set(classObj, newMap.get(clsType));
                }
            }
            return classObj;
        } else {
            throw new BeanNotFoundException();
        }
    }
}
