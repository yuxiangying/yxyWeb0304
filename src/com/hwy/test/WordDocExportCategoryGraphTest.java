package com.hwy.test;

import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import freemarker.template.Configuration;
import freemarker.template.Template;
import sun.misc.BASE64Encoder;

public class WordDocExportCategoryGraphTest {
	public static void main(String[] args) throws Exception{
		 /** 初始化配置文件 **/
        Configuration configuration = new Configuration();
        /** 设置编码 **/
        configuration.setDefaultEncoding("utf-8");
//        String fileDirectory = "C:\\java";
        /** 我的ftl文件模板是相对类而言**/
//        configuration.setDirectoryForTemplateLoading(new File(fileDirectory));
        configuration.setClassForTemplateLoading(WordDocExportCategoryGraphTest.class, "");
        /** 加载模板 **/
        Template template = configuration.getTemplate("graphCategoryTemplate.ftl");
        /** ׼������ **/
        Map<String,Object> dataMap = new HashMap<>();
//        Map<String,String> dataMap = new HashMap<>();

        /** 准备数据 **/
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("100424060","小毅","男",25));
        studentList.add(new Student("100424030","小兰","女",22));
//        studentList.add(new Student("100424031","小五","男1",23));
        studentList.add(new Student("100424032","小竹","女6",27));
        studentList.add(new Student("100424032","小竹","女1",26));
        studentList.add(new Student("100424032","小竹","女3",24));
        studentList.add(new Student("100424032","小竹","中4",24));
        studentList.add(new Student("100424032","小竹","女4",26));
        studentList.add(new Student("100424032","小竹","女5",24));
        studentList.add(new Student("100424032","小竹","中1",24));
        /*studentList.add(new Student("100424032","小竹","女7",26));
        studentList.add(new Student("100424032","小竹","女2",24));
        studentList.add(new Student("100424032","小竹","中2",24));*/
        /** 表格数据 studentList和freemarker标签要对应**/
        dataMap.put("studentList",studentList);
        
        List<SexGraph> sexGraphList = new ArrayList<>();
        Map<String,SexGraph> sexGraphMap = new HashMap<>();
        BigDecimal sum = new BigDecimal(studentList.size());
        for(Student student:studentList){
        	SexGraph sexGraph = new SexGraph();
        	if(sexGraphMap.containsKey(student.getSex())){
        		sexGraph = sexGraphMap.get(student.getSex());
        		sexGraph.setNum(sexGraph.getNum()+1);
        	}else{
        		sexGraph.setSex(student.getSex());
        		sexGraph.setNum(1);
        	}
        	sexGraphMap.put(student.getSex(), sexGraph);
        }
       for(String key: sexGraphMap.keySet()){
    	   SexGraph sexGraph = sexGraphMap.get(key);
    	   BigDecimal proportion = new BigDecimal(sexGraph.getNum()).divide(sum,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
    	   sexGraph.setProportion(proportion);
    	   sexGraphList.add(sexGraph);
       } 
       System.out.println(sexGraphList.size());
       /** 表格数据 sexGraphList和freemarker标签要对应**/
       dataMap.put("sexGraphList",sexGraphList);
       
        /** 图片路径 **/
       getGraph();
       String imagePath = "C:\\java\\graphCategory.png";
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
        String outFilePath = "C:\\java\\graphCategoryFreeMarker.doc";
        File docFile = new File(outFilePath);
        FileOutputStream fos = new FileOutputStream(docFile);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);

        if(out != null){
            out.close();
        }
    }
	
	private static void getGraph(){
		 try {
	            //如果不使用Font,中文将显示不出来
	            Font font = new Font("宋体", Font.BOLD, 15);
	 
	            DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
	            /*dataset.addValue(100, "北京", "苹果");  
	            dataset.addValue(100, "上海", "苹果");  
	            dataset.addValue(100, "广州", "苹果");  
	            dataset.addValue(200, "北京", "梨子");  
	            dataset.addValue(200, "上海", "梨子");  
	            dataset.addValue(200, "广州", "梨子");  
	            dataset.addValue(300, "北京", "葡萄");  
	            dataset.addValue(300, "上海", "葡萄");  
	            dataset.addValue(300, "广州", "葡萄");  
	            dataset.addValue(400, "北京", "香蕉");  
	            dataset.addValue(400, "上海", "香蕉");  
	            dataset.addValue(400, "广州", "香蕉");  
	            dataset.addValue(500, "北京", "荔枝");  
	            dataset.addValue(500, "上海", "荔枝");  
	            dataset.addValue(500, "广州", "荔枝");  */
	            dataset.addValue(10, "月参保人数", "2018.03");
	            dataset.addValue(10, "累积参保人数", "2018.03");
	            dataset.addValue(20, "月参保人数", "2018.04");
	            dataset.addValue(30, "累积参保人数", "2018.04");
	            dataset.addValue(30, "月参保人数", "2018.05");
	            dataset.addValue(60, "累积参保人数", "2018.05");
	            dataset.addValue(60, "月参保人数", "2018.06");
	            dataset.addValue(120, "累积参保人数", "2018.06");
	            dataset.addValue(10, "月参保人数", "2018.07");
	            dataset.addValue(130, "累积参保人数", "2018.07");
	            dataset.addValue(10, "月参保人数", "2018.08");
	            dataset.addValue(140, "累积参保人数", "2018.08");
	            dataset.addValue(10, "月参保人数", "2018.09");
	            dataset.addValue(150, "累积参保人数", "2018.09");
	            dataset.addValue(10, "月参保人数", "2018.10");
	            dataset.addValue(160, "累积参保人数", "2018.10");
	            dataset.addValue(25, "月参保人数", "2018.11");
	            dataset.addValue(185, "累积参保人数", "2018.11");
	            dataset.addValue(0, "月参保人数", "2018.12");
	            dataset.addValue(185, "累积参保人数", "2018.12");
	            JFreeChart chart = ChartFactory.createBarChart3D(  
                       "水果", // 图表标题  
                       "水果种类", // 目录轴的显示标签  
                       "数量", // 数值轴的显示标签  
                       dataset, // 数据集  
                       PlotOrientation.VERTICAL, // 图表方向：水平、垂直  
                       true,           // 是否显示图例(对于简单的柱状图必须是false)  
                       false,          // 是否生成工具  
                       false           // 是否生成URL链接  
                       );  
	            //设置图片标题的字体
	            //chart.getTitle().setFont(font);
	            
	            //从这里开始  
	            CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象  
	            CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表  
	            domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题  
	            domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题  
	            ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状  
	            rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));  
	            chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));  
	            chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体  
	            chart.setBackgroundPaint(ChartColor.PINK);
	            plot.setBackgroundPaint(ChartColor.WHITE);
	            //将内存中的图片写到本地硬盘
	            ChartUtilities.saveChartAsPNG(new File("C:/java/graphCategory.png"), chart, 600, 400);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
