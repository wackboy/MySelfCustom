package com.example.apt_api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ApiImpl {

    public static void init() {
        try {
            Class c = Class.forName("com.example.myselfcustom.HelloWorld");
            Constructor declaredConstructor = c.getDeclaredConstructor();
            Object o = declaredConstructor.newInstance();
            Method test = c.getDeclaredMethod("test", String.class);
            test.invoke(o, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}