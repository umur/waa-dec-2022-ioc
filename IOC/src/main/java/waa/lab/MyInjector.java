package waa.lab;

import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyInjector {
    private Map<Class<?>, Object> autowiredInstances = new HashMap<>();
    private String beanNotFoundMsg = "Bean Not Found %s%n";

    public MyInjector() {Inject();}

    private void Inject() {
        String packageName = MyInjector.class.getPackageName();
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(MyBean.class);

        for(Class<?> cls : classes){
            addAutoWiredInstances(cls);
        }
    }


   // private void addAutoWiredInstances(Class<?> toAdd) throws Exception {
    private Object addAutoWiredInstances(Class<?> toAdd) {
        try {
            Object object = autowiredInstances.get(toAdd);
            for (Field field: toAdd.getDeclaredFields()){
                    if(!field.isAnnotationPresent(MyAutowired.class))
                        throw new RuntimeException(beanNotFoundMsg);
                Class<?> fieldClass = field.getType();
                addAutoWiredInstances(fieldClass);
                Object fieldObject = addAutoWiredInstances(fieldClass);
                //set is not working
//                field.set(toAdd, fieldObject);
                }
            //after all instances created , add them to the map
            autowiredInstances.put(toAdd, toAdd.getDeclaredConstructor().newInstance());
            return object;
        }catch(Exception e){
            throw new RuntimeException("Exception initializing Bean: " + toAdd.getName() + "\nError Msg: " + e.getMessage());
        }
    }


//    public <T> T getBeans(Class<T> clazz) throws BeanNotFoundException {
        public <T> T getBeans(Class<T> clazz) throws BeanNotFoundException {
        String className = clazz.getSimpleName();
        if (autowiredInstances.containsKey(className)) {
            return (T) autowiredInstances.get(className);
        } else {
            throw new BeanNotFoundException(beanNotFoundMsg, className);
        }
    }

}
