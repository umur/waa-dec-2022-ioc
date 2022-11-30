import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@MyBean
public class MyInjector {

    Map<Class, Object> MyHashMap = new HashMap<>();

    public MyInjector() {
        String packageName = "";
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("");

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        Set<Class> classes = reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> {
                    try {
                        return Class.forName(line.substring(0, line.lastIndexOf('.')));
                    } catch (Exception e) {
                        return null;
                    }
                })
                .collect(Collectors.toSet());

        for (Class c : classes) {
            try {
                Object annotation = c.getAnnotation(MyBean.class);
                if (annotation != null) {
                    Object obj = c.getDeclaredConstructor().newInstance();
                    MyHashMap.put(c, obj);
                }

            } catch (Exception e) {

            }


//            MyHashMap.put(c.getSimpleName(), obj);
            //java.lang.reflect.Constructor.newInstance();
        }

    }

    public Object getBean(Class clazz) throws IllegalAccessException, BeanNotFoundException {
        if (MyHashMap.containsKey(clazz)) {
            Object obj = MyHashMap.get(clazz);
            List<Field> fields = Arrays.stream(obj.getClass().getDeclaredFields()).toList();

            for (Field field : fields) {
                MyAutowired annotation = field.getAnnotation(MyAutowired.class);
                if (annotation != null) {
                    if (!field.canAccess(obj))
                        field.setAccessible(true);
                    Class csType = field.getType();
                    field.set(obj, MyHashMap.get(csType));
                }
            }

            return obj;
        } else {
            throw new BeanNotFoundException();
//            return null;
        }

    }
}

