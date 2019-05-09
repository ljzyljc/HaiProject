package com.hai.jackie.haiproject.annotation;

import android.support.annotation.ColorRes;
import android.support.annotation.IntRange;
import android.support.annotation.MainThread;
import android.support.annotation.RequiresPermission;
import android.support.annotation.UiThread;

import java.lang.reflect.Field;

@BiaoJi("class focus")
public class AnnotationTest2 {

    @BiaoJi("field foucs")
    private int count;

    @BiaoJi("method foucs")
    public int getCount(){
        return 100;
    }

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        if (AnnotationTest2.class.isAnnotationPresent(BiaoJi.class)){
            BiaoJi biaoJi = AnnotationTest2.class.getAnnotation(BiaoJi.class);
                System.out.println(biaoJi.value());
        }
        //解析成员变量的注解
        BiaoJi methodBiaoJi = AnnotationTest2.class.getMethod("getCount").getAnnotation(BiaoJi.class);
        System.out.println(methodBiaoJi.value());
        //解析方法上的注解
        Field field = AnnotationTest2.class.getDeclaredField("count");
        field.setAccessible(true);
        BiaoJi fieldBiaoJi = field.getAnnotation(BiaoJi.class);
        System.out.println(fieldBiaoJi.value());


    }


    public void testAndroidAnnotation(@ColorRes int drawable){

    }
    @MainThread
    @UiThread
    public void testAndroidValueAnnotation(@IntRange int drawable){

    }

}
