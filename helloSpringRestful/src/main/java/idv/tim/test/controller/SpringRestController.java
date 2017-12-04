package idv.tim.test.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PreDestroy;
import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/hello")
public class SpringRestController {
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String hello(@PathVariable String name) {
		String result="Hello "+name; 
		return result;
	}
	
	@PostConstruct
	public void initIt() throws Exception {
	  System.out.println("Init method after properties are set");
	}
	
	@PreDestroy
	public void cleanUp() throws Exception {
	  System.out.println("Spring Container is destroy! Customer clean up");
	  Thread.sleep(5000);
	  System.out.println("Spring Container is destroy! Customer clean up - Done");
	}
}
