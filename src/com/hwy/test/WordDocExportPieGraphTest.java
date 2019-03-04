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
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import freemarker.template.Configuration;
import freemarker.template.Template;
import sun.misc.BASE64Encoder;

public class WordDocExportPieGraphTest {
	public static void main(String[] args) throws Exception{
		 /** 初始化配置文件 **/
        Configuration configuration = new Configuration();
        /** 设置编码 **/
        configuration.setDefaultEncoding("utf-8");
//        String fileDirectory = "C:\\java";
        /** 我的ftl文件模板是相对类而言**/
//        configuration.setDirectoryForTemplateLoading(new File(fileDirectory));
        configuration.setClassForTemplateLoading(WordDocExportPieGraphTest.class, "");
        /** 加载模板 **/
        Template template = configuration.getTemplate("graphPieTemplate.ftl");
        /** ׼������ **/
        Map<String,Object> dataMap = new HashMap<>();
//        Map<String,String> dataMap = new HashMap<>();

        /** 准备数据 **/
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("100424060","小毅","男",25));
        studentList.add(new Student("100424030","小兰","女",22));
//        studentList.add(new Student("100424031","小五","男1",23));
        studentList.add(new Student("100424032","小竹","女",27));
       /* studentList.add(new Student("100424032","小竹","女1",26));
        studentList.add(new Student("100424032","小竹","女",24));
        studentList.add(new Student("100424032","小竹","中",24));
        studentList.add(new Student("100424032","小竹","女",26));
        studentList.add(new Student("100424032","小竹","女",24));
        studentList.add(new Student("100424032","小竹","中1",24));
        studentList.add(new Student("100424032","小竹","女",26));
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
//        String imagePath = "C:\\java\\picture.jpg";
       getGraph();
       String imagePath = "C:\\java\\graphPie.jpg";
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
        String outFilePath = "C:\\java\\graphPieFreeMarker.doc";
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
	 
	            DefaultPieDataset pds = new DefaultPieDataset();
	            pds.setValue("sun", 100);
	            pds.setValue("ibm", 300);
	            pds.setValue("bea", 500);
	            pds.setValue("oracle", 700);
	            /**
	             * 生成一个饼图的图表
	             *
	             * 分别是:显示图表的标题、需要提供对应图表的DateSet对象、是否显示图例、是否生成贴士以及是否生成URL链接
	             */
	            JFreeChart chart = ChartFactory.createPieChart("标题", pds, true, false, true);
	            //设置图片标题的字体
	            chart.getTitle().setFont(font);
	 
	            //得到图块,准备设置标签的字体
	            PiePlot plot = (PiePlot) chart.getPlot();
	 
	            //设置分裂效果,需要指定分裂出去的key
	            plot.setExplodePercent("oracle", 0.3);
	 
	            //设置标签字体
	            plot.setLabelFont(font);
	 
	            //设置图例项目字体
	            chart.getLegend().setItemFont(font);
	 
	            /**
	             * 设置开始角度(弧度计算)
	             *
	             * 度    0°    30°        45°        60°        90°        120°    135°    150°    180°    270°    360°
	             * 弧度    0    π/6        π/4        π/3        π/2        2π/3    3π/4    5π/6    π        3π/2    2π
	             */
	            plot.setStartAngle(new Float(3.14f / 2f));
	 
	            //设置背景图片,设置最大的背景
	            /*Image img = ImageIO.read(new File("C:/java/picture.jpg"));
	            chart.setBackgroundImage(img);*/
	            chart.setBackgroundPaint(ChartColor.red);
	 
	            //设置plot的背景图片
	            /*Image img = ImageIO.read(new File("C:/java/picture.jpg"));
	            plot.setBackgroundImage(img);*/
	            plot.setBackgroundPaint(ChartColor.WHITE);
	 
	            //设置plot的前景色透明度
	            //plot.setForegroundAlpha(0.7f);
	 
	            //设置plot的背景色透明度
	            //plot.setBackgroundAlpha(0.0f);
	 
	            //设置标签生成器(默认{0})
	            //{0}:key {1}:value {2}:百分比 {3}:sum
	            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1})/{2}"));
	 
	            //将内存中的图片写到本地硬盘
	            ChartUtilities.saveChartAsJPEG(new File("C:/java/graphPie.jpg"), chart, 600, 300);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
