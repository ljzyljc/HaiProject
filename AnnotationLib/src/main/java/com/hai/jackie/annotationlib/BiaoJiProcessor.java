package com.hai.jackie.annotationlib;

import com.google.auto.service.AutoService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class BiaoJiProcessor extends AbstractProcessor{

    private Elements mElementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mElementUtils = processingEnvironment.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        //System.out.println("--process--------");
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BiaoJi.class);
        try {
            FileWriter fileWriter = new FileWriter("/Users/xmly/Desktop/gitAndroid/HaiProject/demo.txt",true);
            fileWriter.write("Omooo");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //解析类上的注解
        for (Element element: roundEnvironment.getElementsAnnotatedWith(BiaoJi.class)){
            TypeElement classElement = (TypeElement) element;
            PackageElement packageElement = (PackageElement) element.getEnclosingElement();
            //全类名
            System.out.println(classElement.getQualifiedName().toString());
            //类名
            System.out.println(classElement.getSimpleName().toString());
            //包名
            System.out.println(packageElement.getQualifiedName().toString());
            //父类名
            System.out.println(classElement.getSuperclass().toString());

        }
        //解析方法上的注解
        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}

//    FileWriter fileWriter = null;
//    File file =new File("javaio-appendfile.txt");
//
////if file doesnt exists, then create it
//        if(!file.exists()){
//                try {
//                file.createNewFile();
//                } catch (IOException e) {
//                e.printStackTrace();
//                }
//                }
//
//                try {
//                fileWriter = new FileWriter(file.getName(),true);
//                fileWriter.write("23333");
//                fileWriter.close();
//                } catch (IOException e) {
//                e.printStackTrace();
//                }