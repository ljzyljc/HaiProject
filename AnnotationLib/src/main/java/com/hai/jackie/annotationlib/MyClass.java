package com.hai.jackie.annotationlib;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.lang.model.element.Modifier;

public class MyClass {


    public static void main(String[] args) throws IOException {

        MyClass myClass = new MyClass();
        myClass.test1();

    }

    private void test1() throws IOException {
        MethodSpec main = MethodSpec.methodBuilder("main")
                //添加代码块
                .addCode(""
                        + "int total = 0;\n"
                        + "for (int i = 0; i < 10; i++) {\n"
                        + "  total += i;\n"
                        + "}\n")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addStatement("$T.out.println($S)",System.class,"hai hai is a beauty girl")
                .addParameter(String[].class, "args")
                .build();
        TypeSpec helloHai = TypeSpec.classBuilder("HelloHai")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();
        JavaFile javaFile = JavaFile
                .builder("com.hai.annotationlib.HelloHai", helloHai)
                .build();

        javaFile.writeTo(System.out);
    }










}
