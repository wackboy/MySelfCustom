package com.example.apt_processor;

import com.example.apt_annotation.AptAnnotation;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.annotation.processing.Filer;

@AutoService(Processor.class)
@SupportedOptions("MODULE_NAME")
@SupportedAnnotationTypes("com.example.apt_annotation.AptAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class AptAnnotationProcessor extends AbstractProcessor {

    // 节点工具类（类、函数、属性都是节点）
    private Elements mElementUtils;
    // 类信息工具类
    private Types mTypeUtils;
    // 文件生成器
    private Filer mFiler;
    // 日志信息打印器
    private Messager mMessager;
    private String mModuleName;

    /**
     * 做一些初始化的工作
     * @param processingEnvironment: 提供了若干个工具类，供编写生成java类使用
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
//        mElementUtils = processingEnvironment.getElementUtils();
//        mTypeUtils = processingEnvironment.getTypeUtils();
//        mFiler = processingEnvironment.getFiler();
//        mMessager = processingEnvironment.getMessager();
        mFiler = processingEnvironment.getFiler();
        mModuleName = processingEnvironment.getOptions().get("MODULE_NAME");

    }

//    /**
//     * 接受外来参数，常用在build.gradle脚本文件里的javaCompilerOptions的配置
//     * @return：属性的Key集合
//     */
//    @Override
//    public Set<String> getSupportedOptions() {
//        return super.getSupportedOptions();
//    }
//
//    /**
//     * 当前注解处理器支持的注解集合，如果支持，就会调用process方法
//     * @return
//     */
//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        return super.getSupportedAnnotationTypes();
//    }
//
//    /**
//     * 编译当前注解处理器的JDK版本
//     * @return
//     */
//    @Override
//    public SourceVersion getSupportedSourceVersion() {
//        return super.getSupportedSourceVersion();
//    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (set == null || set.isEmpty()) {
            return false;
        }
        Set<? extends Element> rootElements =
                roundEnvironment.getElementsAnnotatedWith(AptAnnotation.class);
        MethodSpec.Builder builder = MethodSpec.methodBuilder("test")
                .addModifiers(Modifier.PUBLIC)  // 执行方法修饰符
                .returns(void.class)    // 指定返回类型
                .addParameter(String.class, "param"); // 添加参数
        builder.addStatement("$T.out.println($S)", System.class, "模块：" + mModuleName);
        if (rootElements != null && !rootElements.isEmpty()) {
            for (Element element : rootElements) {
                // 当前节点的名称
                String elementName = element.getSimpleName().toString();
                // 当下节点注解的属性值
                String desc = element.getAnnotation(AptAnnotation.class).desc();
                // 构建方法体
                builder.addStatement("$T.out.println($S)", System.class, "节点：" + elementName + "描述：" + desc);
            }
        }
        MethodSpec main = builder.build();
        // 构建helloWorld类
        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC)  // 指定类修饰符
                .addMethod(main)    // 添加方法
                .build();

        JavaFile javaFile = JavaFile.builder("com.example.apt-processor", helloWorld).build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;

//        Set<? extends Element> elements =
//                roundEnvironment.getElementsAnnotatedWith(AptAnnotation.class);
//        for (Element element : elements) {
//            if (element.getKind() == ElementKind.CLASS) {
//                // 如果元素是类
//            } else if (element.getKind() == ElementKind.INTERFACE) {
//                // 如果元素是接口
//
//            }
//        }
//        return false;
    }
}