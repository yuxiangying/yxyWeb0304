package com.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hwy.test.Student;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestFreemarker2 {
	private Configuration configuration = null;  
	   public TestFreemarker2(){  
	       configuration = new Configuration();  
	       configuration.setDefaultEncoding("UTF-8");  
	   }  
	   
	   public static void main(String[] args) {  
		   TestFreemarker2 test = new TestFreemarker2();  
		   test.createWord();  
		 }  

	public void createWord(){
        /** ��ʼ�������ļ� **/
        //Configuration configuration = new Configuration();
        /** ���ñ��� **/
        //configuration.setDefaultEncoding("utf-8");
        /** �ҵ�ftl�ļ��Ƿ���D�̵�**/
        //String fileDirectory = "C:\\java";
        /** �����ļ� **/
        configuration.setClassForTemplateLoading(this.getClass(), "");//ģ���ļ�����·��
        //configuration.setDirectoryForTemplateLoading(new File(fileDirectory));
        /** ����ģ�� **/
        Template template = null;
		try {
			template = configuration.getTemplate("template3.ftl");
		} catch (IOException e) {
			e.printStackTrace();
		}
        /** ׼������ **/
        Map<String,Object> dataMap = new HashMap<>();

        getData(dataMap);
        /** ������ݳ�ʼ�� **/

        /** ָ�����word�ļ���·�� **/
        String outFilePath = "C:\\java\\myFreeMarker2.doc";
        File docFile = new File(outFilePath);
        FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(docFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			template.process(dataMap,out);
		} catch (TemplateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if(out != null){
            try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }


	private static void getData(Map<String,Object> dataMap){
		List<Map<String,Object>> studentList = new ArrayList<Map<String,Object>>();  
		for (int i = 0; i < 10; i++) {  
			Map<String,Object> map = new HashMap<String,Object>();  
			map.put("id", i);  
			map.put("name", "name"+i); 
			map.put("sex", "��");  
			map.put("age", 20+i);  
			studentList.add(map);  
		}  
		dataMap.put("studentList", studentList);  
	}
}
