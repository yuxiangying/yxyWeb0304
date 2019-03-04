package com.hwy.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import freemarker.template.Configuration;
import freemarker.template.Template;

public class WordDocExportTableTest {
	public static void main(String[] args) throws Exception{
		 /** 初始化配置文件 **/
        Configuration configuration = new Configuration();
        /** 设置编码 **/
        configuration.setDefaultEncoding("utf-8");
//        String fileDirectory = "C:\\java";
        /** 我的ftl文件模板是相对类而言**/
//        configuration.setDirectoryForTemplateLoading(new File(fileDirectory));
        configuration.setClassForTemplateLoading(WordDocExportTableTest.class, "");
        /** 加载模板 **/
        Template template = configuration.getTemplate("template.ftl");
        /** ׼������ **/
        Map<String,List<Student>> dataMap = new HashMap<>();
//        Map<String,String> dataMap = new HashMap<>();

        /** 准备数据 **/
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("100424060","小毅","男",25));
        studentList.add(new Student("100424030","小兰","女",22));
        studentList.add(new Student("100424031","小五","男",23));
        studentList.add(new Student("100424032","小竹","女",27));

        /** 表格数据 studentList和freemarker标签要对应**/
        dataMap.put("studentList",studentList);

        /** 指定输出word文件的路径 **/
        String outFilePath = "C:\\java\\myFreeMarker.doc";
        File docFile = new File(outFilePath);
        FileOutputStream fos = new FileOutputStream(docFile);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);

        if(out != null){
            out.close();
        }
    }

}
