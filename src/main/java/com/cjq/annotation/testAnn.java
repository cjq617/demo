package com.cjq.annotation;



import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class testAnn {

    @Test
    public void getAnn() {
        try {
            Class c = Class.forName("com.cjq.annotation.Person");
            if(c.isAnnotationPresent(Description.class)) {
                Description a = (Description)c.getAnnotation(Description.class);
                System.out.println(a.value());
            }

            Method[] methods = c.getMethods();
            for(Method m : methods) {
                if(m.isAnnotationPresent(Description.class)) {
                    Description d = m.getAnnotation(Description.class);
                    System.out.println(d.value());
                }
            }

            for(Method m : methods) {
                for(Annotation ann : m.getAnnotations()) {
                    if(ann instanceof  Description) {
                        Description d = (Description)ann;
                        System.out.println(d.value());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sing() {
        try {
            Class clazz = Class.forName("com.cjq.demo.annotation.Child");
            //Class clazz = ClassLoader.getSystemClassLoader().loadClass("com.cjq.com.cjq.demo.annotation.Person");
            if(clazz.isAnnotationPresent(Description.class)) {
                for(Annotation ann : clazz.getAnnotations()) {
                    if(ann.annotationType().equals(Description.class)) {
                        System.out.println(((Description) ann).age());
                        System.out.println(((Description) ann).value());
                    }
                }
            }

            for(Method method : clazz.getMethods()) {
                if(method.isAnnotationPresent(Description.class)) {
                    for(Annotation ann : method.getAnnotations()) {
                        if(ann.annotationType().equals(Description.class)) {
                            System.out.println(((Description) ann).age());
                            System.out.println(((Description) ann).value());
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
