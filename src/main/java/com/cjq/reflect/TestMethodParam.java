package com.cjq.reflect;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 获取方法的参数名方法
 * Created by jq Chen on 2018/8/17.
 */
public class TestMethodParam {

    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
        //Class clazz = TestMethodParam.class;
        Class clazz = Class.forName("com.cjq.demo.reflect.TestMethodParam");
        Method method = clazz.getDeclaredMethod("test", String.class, int.class);
        System.out.println("test： ");
        Parameter[] parameters = method.getParameters();
        for(Parameter parameter : parameters) {
            System.out.println(parameter.getName());
        }

        List<String> paramList = getParameterNameJava8(TestMethodParam.class, "test");
        paramList.forEach(System.out::println);
    }

    public void test(String param1, int param2) {
        System.out.println(param1 + param2);
    }

    /**
     * 在Java 8之前的版本，代码编译为class文件后，方法参数的类型是固定的，但参数名称却丢失了，这和动态语言严重依赖参数名称形成了鲜明对比。现在，Java 8开始在class文件中保留参数名，给反射带来了极大的便利。jdk8增加了类Parameter
     * 如果编译级别低于1.8，得到的参数名称是无意义的arg0、arg1……
     遗憾的是，保留参数名这一选项由编译开关javac -parameters打开，默认是关闭的。
     注意此功能必须把代码编译成1.8版本的class才行。
     idea设置保留参数名：
     在 preferences-》Java Compiler->设置模块字节码版本1.8，Javac Options中的 Additional command line parameters: -parameters
     * 获取方法里的参数名
     * @param clazz
     * @param methodName
     * @return
     */
    public static List<String> getParameterNameJava8(Class clazz, String methodName){
        List<String> paramterList = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                Parameter[] params = method.getParameters();
                for(Parameter parameter : params){
                    paramterList.add(parameter.getName());
                }
            }
        }
        return paramterList;
    }

    /**
     * 通过spring提供的类获取参数名的方法
     * @param clazz
     * @param methodName
     * @return
     */
    public static List<String> getParamterName(Class clazz, String methodName){
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                String[] params = u.getParameterNames(method);
                return Arrays.asList(params);
            }
        }

        return null;
    }
}
