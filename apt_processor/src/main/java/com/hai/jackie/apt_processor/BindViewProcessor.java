package com.hai.jackie.apt_processor;

import com.google.auto.service.AutoService;
import com.hai.jackie.apt_annotations.BindView;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Filter;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

@AutoService(Processor.class)
public class BindViewProcessor extends AbstractProcessor {

    private Elements mElementsUtils;
    private Types mTypesUtils;
    private Filter mFilter;
    private Messager mMessager;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mElementsUtils = processingEnvironment.getElementUtils();
    }

    /**
     * 处理注解
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //获取所有包含 BindView 注解的元素
        Set<? extends Element> elementSet = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        Map<TypeElement, Map<Integer, VariableElement>> typeElementMapHashMap = new HashMap<>();
        for (Element element : elementSet) {
            //因为 BindView 的作用对象是 FIELD，因此 element 可以直接转化为 VariableElement
            VariableElement variableElement = (VariableElement) element;
            //getEnclosingElement 方法返回封装此 Element 的最里层元素
            //如果 Element 直接封装在另一个元素的声明中，则返回该封装元素
            //此处表示的即是 Activity 类对象
            TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();
            Map<Integer, VariableElement> variableElementMap = typeElementMapHashMap.get(typeElement);
            if (variableElementMap == null) {
                variableElementMap = new HashMap<>();
                typeElementMapHashMap.put(typeElement, variableElementMap);
            }
            //获取注解的值，即 ViewId
            BindView bindAnnotation = variableElement.getAnnotation(BindView.class);
            int viewId = bindAnnotation.value();
            variableElementMap.put(viewId, variableElement);
        }
        for (TypeElement key : typeElementMapHashMap.keySet()) {
            Map<Integer, VariableElement> elementMap = typeElementMapHashMap.get(key);
            String packageName = ElementUtils.getPackageName(mElementsUtils, key);
            JavaFile javaFile = JavaFile.builder(packageName, generateCodeByPoet(key, elementMap)).build();
            try {
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 生成 Java 类
     *
     * @param typeElement        注解对象的上层元素对象，即 Activity 对象
     * @param variableElementMap Activity 包含的注解对象以及注解的目标对象
     * @return
     */
    private TypeSpec generateCodeByPoet(TypeElement typeElement, Map<Integer, VariableElement> variableElementMap) {
        //自动生成的文件以 Activity 名 + ViewBinding 进行命名
        return TypeSpec.classBuilder(ElementUtils.getEnclosingClassName(typeElement) + "ViewBinding")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(generateMethodByPoet(typeElement, variableElementMap))
                .build();
    }

    /**
     * 生成方法
     *
     * @param typeElement        注解对象上层元素对象，即 Activity 对象
     * @param variableElementMap Activity 包含的注解对象以及注解的目标对象
     * @return
     */
    private MethodSpec generateMethodByPoet(TypeElement typeElement, Map<Integer, VariableElement> variableElementMap) {
        ClassName className = ClassName.bestGuess(typeElement.getQualifiedName().toString());
        //方法参数名
        String parameter = "_m" + className.simpleName();
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("bind")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(className, parameter);
        for (int viewId : variableElementMap.keySet()) {
            VariableElement element = variableElementMap.get(viewId);
            //被注解的字段名
            String name = element.getSimpleName().toString();
            //被注解的字段的对象类型的全名称
            String type = element.asType().toString();
            String text = "{0}.{1}=({2})({3}.findViewById({4}));\n";
            methodBuilder.addCode(MessageFormat.format(text, parameter, name, type, parameter, String.valueOf(viewId)));
        }
        return methodBuilder.build();
    }



    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> hashSet = new HashSet<>();
        hashSet.add(BindView.class.getCanonicalName());
        return hashSet;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
