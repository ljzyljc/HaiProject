package com.hai.jackie.haiproject.butterknife;

import android.app.Activity;

import java.lang.reflect.Method;

public class ButterKnife {

    public static void bind(Activity activity){
        Class clazz = activity.getClass();
        try {
            Class bindViewClass = Class.forName(clazz.getName() + "ViewBinding");
            Method method = bindViewClass.getMethod("bind", clazz);
            method.invoke(null, activity);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


//    public static void bind(Activity activity) {
//        Class clazz = activity.getClass();
//        try {
//            Class bindViewClass = Class.forName(clazz.getName() + "ViewBinding");
//            Method method = bindViewClass.getMethod("bind", clazz);
//            method.invoke(null, activity);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
