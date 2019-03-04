package com.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import freemarker.template.Configuration;
import freemarker.template.Template;

public class TestFreemarker {

	public static void main(String[] args) throws Exception{
        /** 初始化配置文件 **/
        Configuration configuration = new Configuration();
        /** 设置编码 **/
        configuration.setDefaultEncoding("utf-8");
        /** 我的ftl文件是基于类路径**/
        configuration.setClassForTemplateLoading(TestFreemarker.class, "");
        /** 加载模板 **/
        Template template = configuration.getTemplate("template3.ftl");
        /** 准备数据 **/
        Map<String,Object> dataMap = new HashMap<>();

        getData(dataMap);
        /** 表格数据初始化 **/

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


	private static void getData(Map<String,Object> dataMap){
		List<Student> studentList = new ArrayList<Student>();  
		for (int i = 0; i < 10; i++) { 
			Student student = new Student();
			student.setId(""+i);
			student.setName("name"+i);
			student.setSex("男");
			student.setAge(20+i);
			studentList.add(student);  
		}  
		dataMap.put("studentList", studentList);  
	}
}
