package com.cjq.annotation;



import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class testAnn {

    @Test
    public void getAnn() {
        try {
            Class c = Class.forName("com.cjq.thread.com.cjq.annotation.Person");
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
}
