import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class MyInjector {
    static Map<String, Object> all_autowired_fields = new HashMap<String,Object>();
    public <T> T getInstance(Class<T> clazz)throws Exception{
        T instance = (T) all_autowired_fields.get(clazz.getName());

        if (instance == null) {

            Constructor<?> constructor = clazz.getConstructor();
            T object = (T)constructor.newInstance();

            Field[] declaredFields = clazz.getDeclaredFields();
            inject(object, declaredFields);
            return object;
        }
        else {
            return instance;
        }
    }

    private <T> void inject(T object, Field[] declaredFields) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Field field : declaredFields) {

            if (field.isAnnotationPresent(MyAutowired.class)) {

                field.setAccessible(true);
                Class<?> type = field.getType();
                Object innerObject = type.getDeclaredConstructor().newInstance();

                field.set(object, innerObject);
                inject(innerObject, type.getDeclaredFields());
                all_autowired_fields.put(type.getName(), innerObject);

            }
        }
    }

    Object getBean(Class<?> clazz) throws Exception {

        Object bean = all_autowired_fields.get(clazz.getName());

        if (bean == null) {

            throw new Exception("Bean is not found");
        }

        return bean;

    }
}
