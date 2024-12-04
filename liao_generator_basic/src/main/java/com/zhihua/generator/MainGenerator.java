package com.zhihua.generator;

import com.zhihua.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 */
public class MainGenerator {

    /**
     * 生成
     *
     * @param model 数据模型
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerate(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
        // 整个项目的根路径 D:\programer\liao_generator
        String rootPath = new File("").getAbsolutePath();

        // 输入路径 D:\programer\liao_generator\liao_generator_demo\acm-template
        String inputPath = new File(rootPath, "liao_generator_demo/acm-template").getAbsolutePath();

        String outputPath = projectPath;
        // 生成静态文件
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);
        // 生成动态文件
        String inputDynamicFilePath = rootPath + File.separator + "liao_generator_basic/src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = rootPath + File.separator + "MainTemplate.java";
        DynamicGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);
    }

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("liaozhihua");
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setOutputText("求和结果：");
        doGenerate(mainTemplateConfig);
    }
}
