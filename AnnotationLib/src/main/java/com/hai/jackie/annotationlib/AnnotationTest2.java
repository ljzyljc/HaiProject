package com.hai.jackie.annotationlib;


import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

@BiaoJi("class focus")
public class AnnotationTest2 {

    @BiaoJi("field foucs")
    private int count;

    @BiaoJi("method foucs")
    public int getCount(){
        return 100;
    }

    public static void main(String[] args){
        AnnotationTest2 annotationTest2 = new AnnotationTest2();
        System.out.println(annotationTest2.getCount());
        System.out.println("--------0----------");

//        try {
//            FileWriter fileWriter = new FileWriter("demo.txt",true);
//            fileWriter.write("Omooo");
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //解析成员变量的注解
//        BiaoJi methodBiaoJi = AnnotationTest2.class.getMethod("getCount").getAnnotation(BiaoJi.class);
//        System.out.println(methodBiaoJi.value());
//        //解析方法上的注解
//        Field field = AnnotationTest2.class.getDeclaredField("count");
//        field.setAccessible(true);
//        BiaoJi fieldBiaoJi = field.getAnnotation(BiaoJi.class);
//        System.out.println(fieldBiaoJi.value());


    }
}
