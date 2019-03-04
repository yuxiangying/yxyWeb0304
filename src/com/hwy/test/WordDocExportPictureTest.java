package com.hwy.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import freemarker.template.Configuration;
import freemarker.template.Template;
import sun.misc.BASE64Encoder;

public class WordDocExportPictureTest {
	public static void main(String[] args) throws Exception{
		 /** 初始化配置文件 **/
        Configuration configuration = new Configuration();
        /** 设置编码 **/
        configuration.setDefaultEncoding("utf-8");
//        String fileDirectory = "C:\\java";
        /** 我的ftl文件模板是相对类而言**/
//        configuration.setDirectoryForTemplateLoading(new File(fileDirectory));
        configuration.setClassForTemplateLoading(WordDocExportPictureTest.class, "");
        /** 加载模板 **/
        Template template = configuration.getTemplate("template1.ftl");
        /** ׼������ **/
        Map<String,Object> dataMap = new HashMap<>();
//        Map<String,String> dataMap = new HashMap<>();

        /** 准备数据 **/
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("100424060","小毅","男",25));
        studentList.add(new Student("100424030","小兰","女",22));
        studentList.add(new Student("100424031","小五","男",23));
        studentList.add(new Student("100424032","小竹","女",27));

        /** 表格数据 studentList和freemarker标签要对应**/
        dataMap.put("studentList",studentList);
        
        /** 图片路径 **/
        String imagePath = "C:\\java\\picture.jpg";
        /** 将图片转化为**/
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imagePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(in != null){
                in.close();
            }
        }
        /** 进行base64位编码 **/
        BASE64Encoder encoder = new BASE64Encoder();

        /** 图片数据**/
        dataMap.put("myImage",encoder.encode(data));

        /** 指定输出word文件的路径 **/
        String outFilePath = "C:\\java\\myFreeMarker1.doc";
        File docFile = new File(outFilePath);
        FileOutputStream fos = new FileOutputStream(docFile);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);

        if(out != null){
            out.close();
        }
    }

}
