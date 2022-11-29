package edu.miu.waa.Lab1;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassLoaderUtil {
    public static Class<?>[] getClasses(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);

        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        List<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }

        return classes.toArray(new Class[classes.size()]);
    }

    public static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();
        for(File f : files) {
            if (f.isDirectory()) {
                assert !f.getName().contains(".");
                classes.addAll(findClasses(f, packageName + "." + f.getName()));
            } else if (f.getName().endsWith(".class")) {
                String className = packageName + "." + f.getName().substring(0, f.getName().length() - 6);
                if (packageName.equals("")) {
                    className = f.getName().substring(0, f.getName().length() - 6);
                }
                System.out.println(className);
                classes.add(Class.forName(className));
            }
        }
        return classes;
    }
}

