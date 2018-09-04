package com.annotation;

import java.lang.reflect.Method;

public class TestTools {
    public static void main(String[] args) {
        NoBug testObj = new NoBug();

        Class clazz = testObj.getClass();

        Method[] methods = clazz.getDeclaredMethods();

        StringBuilder log = new StringBuilder();

        int errorNum = 0;

        for (Method m: methods){
            if (m.isAnnotationPresent(Check.class)){
                try {
                    m.setAccessible(true);
                    m.invoke(testObj, null);
                }catch (Exception e){
                    errorNum++;
                    log.append(m.getName());
                    log.append(" ");
                    log.append("has error:");
                    log.append("\n\r  caused by ");
                    log.append(e.getCause().getClass().getSimpleName());
                    log.append("\n\r");
                    //记录测试过程中，发生的异常的具体信息
                    log.append(e.getCause().getMessage());
                    log.append("\n\r");
                }
            }
        }

        log.append(clazz.getSimpleName());
        log.append(" has  ");
        log.append(errorNum);
        log.append(" error.");

        // 生成测试报告
        System.out.println(log.toString());
    }
}
