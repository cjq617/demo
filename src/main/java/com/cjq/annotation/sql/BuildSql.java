package com.cjq.annotation.sql;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by jq Chen on 2018/8/10.
 */
public class BuildSql {

    public static void main(String[] args) {
        User user = new User();
        user.setName("mjq");
        user.setAge(1);

        User user1 = new User();
        user1.setAge(18);

        User user2 = new User();
        user2.setName("cjq,wqx,mmm");

        String sql = query(user);
        String sql1 = query(user1);
        String sql2 = query(user2);

        System.out.println(sql);
        System.out.println(sql1);
        System.out.println(sql2);
    }

    public static String query(User user) {
        StringBuffer sb = new StringBuffer("");
        Class c = user.getClass();
        if(!c.isAnnotationPresent(Table.class)) {
            return null;
        }
        Table table = (Table) c.getAnnotation(Table.class);
        String tableName = table.value();
        sb.append("select * from ").append(tableName).append(" where 1=1");

        Field[] fs = c.getDeclaredFields();
        for(Field f : fs) {
            if(!f.isAnnotationPresent(Column.class)) {
                continue;
            }
            Column column = f.getAnnotation(Column.class);
            String columnName = column.value();

            Object value = null;
            String fieldName = f.getName();
            String getMethodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
            try {
                Method method = c.getMethod(getMethodName);
                value = method.invoke(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(value == null || (value instanceof Integer && (Integer)value == 0)) {
                continue;
            }
            sb.append(" and ").append(columnName);
            if(value instanceof String) {
                if(((String) value).contains(",")) {
                    sb.append(" in(");
                    for(String v : ((String) value).split(",")) {
                        sb.append("'").append(v).append("',");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(")");
                }else {
                    sb.append("=").append("'").append(value).append("'");
                }
            }else {
                sb.append("=").append(value);
            }
        }
        return sb.toString();
    }
}
