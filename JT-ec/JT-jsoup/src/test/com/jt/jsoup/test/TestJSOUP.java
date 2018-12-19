package com.jt.jsoup.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jt.jsoup.mapper.CourseMapper;
import com.jt.jsoup.pojo.Course;

public class TestJSOUP {
	
	private CourseMapper courseMapper;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
		courseMapper = context.getBean(CourseMapper.class);
	}
	
	//获取tmooc中的课程数量,老师数量,用户数量
	@Test
	public void getStudentNum() throws IOException{
		String url = "http://www.tmooc.cn/";
		Elements elements = 
		Jsoup.connect(url).get().select(".num-box-lty")
		.select(".text-center").select(".data-show").select("h3");
		
		System.out.println(elements.get(0).text()+"个课程");
		System.out.println(elements.get(1).text()+"位讲师");
		System.out.println(elements.get(2).text()+"个用户");
	}
	
	//获取直播课程数据
	@Test
	public void testZhibo() throws IOException{
		String url = "http://www.tmooc.cn";
		Elements elements = Jsoup.connect(url).get().select(".md-com-list");
		for (int i=0;i<elements.size()-1;i++) {
			//获取课程名称
			Element element = elements.get(i);
			//获取课程名称
			String kecheng = element.select(".hd-tit").select(".color-333").select(".font-20").select(".verflag").text();
			System.out.println(kecheng);	//获取课程名称
			//获取课程链接
			Elements courses  = element.select("li");
			for (Element courseLi : courses) {
				String imageUrl = courseLi.select(".hd-pic").select("img").attr("src");
				Elements course = courseLi.select(".bd-word").select(".bgcolor-fff");
				//获取课程url/名称/人数
				String courseUrl = course.select("h4 a").attr("href");
				String courseName = course.select("h4 a").text();
				String learnNum = course.select(".sign-num").select(".pull-right").select(".color-888").text();
				System.out.println(courseUrl);
				System.out.println(courseName);
				System.out.println(learnNum);
				Course courseDB = new Course();
				courseDB.setCourseType(kecheng);
				courseDB.setCourseUrl(courseUrl);
				courseDB.setCourseName(courseName);
				courseDB.setCourseImage(imageUrl);
				int num = Integer.parseInt(learnNum.substring(0, learnNum.indexOf("人")).replace(",",""));
				courseDB.setCourseNumber(num);
				courseMapper.insert(courseDB);
			}
		}
		
		System.out.println("执行完成!!!!!");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
