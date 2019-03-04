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

import com.hwy.test.Student;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TestFreemarker3 {

	public static void main(String[] args) throws Exception {
		/** 初始化配置文件 **/
		Configuration configuration = new Configuration();
		/** 设置编码 **/
		configuration.setDefaultEncoding("utf-8");
		/** 我的ftl文件是基于类路径 **/
		configuration.setClassForTemplateLoading(TestFreemarker3.class, "");
		/** 加载模板 **/
		Template template = configuration.getTemplate("template3.ftl");
		/** 准备数据 **/
		Map<String, Object> dataMap = new HashMap<>();

		getData(dataMap);
		/** 表格数据初始化 **/

		/** 指定输出word文件的路径 **/
		String outFilePath = "C:\\java\\myFreeMarker3.doc";
		File docFile = new File(outFilePath);
		FileOutputStream fos = new FileOutputStream(docFile);
		Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
		template.process(dataMap, out);

		if (out != null) {
			out.close();
		}
	}

	private static void getData(Map<String, Object> dataMap) {
		List<Map<String, Object>> studentList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", i);
			map.put("name", "name" + i);
			map.put("sex", "男");
			map.put("age", 20 + i);
			studentList.add(map);
		}
		dataMap.put("studentList", studentList);
	}
}
