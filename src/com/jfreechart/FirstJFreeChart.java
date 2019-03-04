package com.jfreechart;

import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class FirstJFreeChart {
	public static void main(String[] args) {
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
            ChartUtilities.saveChartAsJPEG(new File("C:/java/数据图2.jpg"), chart, 600, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
