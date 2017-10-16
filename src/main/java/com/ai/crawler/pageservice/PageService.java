package com.ai.crawler.pageservice;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ai.crawler.entity.WebPage;
import com.ai.crawler.service.WebPageService;

@RestController
@ComponentScan("com.ai")
@MapperScan("com.ai.crawler")
public class PageService {
	@Autowired
	WebPageService webPageService;
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	WebPage getFetchPage(){
		List<WebPage> pages=webPageService.getFetchList(1);
		if(pages.size()>0){
			webPageService.updateFetchingTime(pages);
		}
		return pages.get(0);
	}
	
	@RequestMapping(value="/page",method=RequestMethod.PUT)
	boolean insertPage(@RequestBody WebPage newPage){
		return webPageService.insert(newPage);
	}
	
	@RequestMapping(value="/page",method=RequestMethod.POST)
	boolean updatePage(@RequestBody WebPage newPage){
		return webPageService.update(newPage);
	}	
}
