package idv.tim.test.controller;

//http://allen0818.pixnet.net/blog/post/95292125-spring-mvc-4---%E4%BD%BF%E7%94%A8-%40restcontroller-%E4%BE%86%E6%8F%90%E4%BE%9Brest-service

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import idv.tim.test.HelloWorld;
import idv.tim.test.model.Car;
import idv.tim.test.model.CarsAndTruck;
import idv.tim.test.model.Message;
import idv.tim.test.model.CustomerInfo;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletContext;

import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/hello")
public class SpringRestController {
	
	@Autowired
	ServletContext servletContext;
	
	static final Logger logger = Logger.getLogger(SpringRestController.class);
	
	
	private void init() {
		try{
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			URL url = loader.getResource("log4j_helloSpringRestful.properties");
			PropertyConfigurator.configure(url);
		}catch(Exception e) {
			System.out.println("Exception happen while loading log4j configuration file:" + e.toString());
		}
	}
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public Message hello(@PathVariable String name) {
		init();
		String result="Hello "+name;
		logger.info("[info] This is a logging statement from log4j:" + result);
		logger.debug("[debug] This is a logging statement from log4j:" + result);
		logger.trace("[trace] This is a logging statement from log4j:" + result);
		//ApplicationContext context = new ClassPathXmlApplicationContext("simplebean.xml");
		//logger.info(context);
		//HelloWorld obj = (HelloWorld) context.getBean("helloBean");
		//obj.printHello();
		ApplicationContext ctx = (ApplicationContext)servletContext.getAttribute("ctx");
		logger.info(ctx);
		HelloWorld obj2 = (HelloWorld) ctx.getBean("helloBean");
		obj2.printHello();
		Message msg = new Message(name, "Hello, " + name);
		return msg;
	}
	
	@RequestMapping(value={"/customer/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public CustomerInfo CustomerInfo(@PathVariable(value="id") int custId)
    {
		CustomerInfo custInfo;
	    EntityManager em = null;
	    init();
	    logger.info((new StringBuilder()).append("Get CustomerInfo:").append(custId).toString());
	    custInfo = new CustomerInfo();
	    try {
	    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("mfgDataSourceJPA");
	    	em = factory.createEntityManager();
	    	Query q = em.createNativeQuery("SELECT CUSTID,FIRSTNAME,LASTNAME FROM CUSTOMERS WHERE CUSTID=#custid");
	    	q.setParameter("custid", Integer.valueOf(custId));
	    	List resultList = q.getResultList();
	    	logger.info((new StringBuilder()).append("Query result size is ").append(resultList.size()).toString());
	    	for(int i = 0; i < resultList.size(); i++)
	    	{
	    		Object record[] = (Object[])(Object[])resultList.get(i);
	    		custInfo.setCustid(Long.parseLong(record[0].toString()));
	    		custInfo.setFirstname((String)record[1]);
	    		custInfo.setLastname((String)record[2]);
	    	}
	    }catch(Exception e) {
	    	logger.error("Read customer id-" + custId + " fail.");
	    }finally{
	    	if (em!=null) {
	    		em.close();
	    	}
	    }
	    return custInfo;


    }
		
	@RequestMapping(value = "/{name}/{id}", method = RequestMethod.GET)
	public Message hello(@PathVariable("name") String name,@PathVariable("id") String id) {
		init();
		String result="Hello "+name;
		logger.info("[info] This is a logging statement from log4j:" + result);
		logger.debug("[debug] This is a logging statement from log4j:" + result);
		logger.trace("[trace] This is a logging statement from log4j:" + result);
		Message msg = new Message(name, "Hello, " + name + " Id is " +id);
		return msg;
	}
	
	@RequestMapping(value = "/car", method = RequestMethod.POST)
	public Car update(@RequestBody Car car) {
		if (car != null) {
	        car.setMiles(car.getMiles() + 100);
	    }
		return car;
	}
	
	@RequestMapping(value = "/cars", method = RequestMethod.POST)
	public List<Car> update(@RequestBody List<Car> cars) {
		if (cars != null) {
	       logger.info("Input Car size:" + cars.size());
	       for (int i=0;i<cars.size();i++) {
	    	   cars.get(i).setMiles(cars.get(i).getMiles()+100);
	       }
		}
		return cars;
	}
	
	@RequestMapping(value = "/carsandtruck", method = RequestMethod.POST)
	public CarsAndTruck update(@RequestBody CarsAndTruck carsandtruck) {
		if (carsandtruck != null) {
	       logger.info("Input Car size:" + carsandtruck.getCars().size());
	       for (int i=0;i<carsandtruck.getCars().size();i++) {
	    	   carsandtruck.getCars().get(i).setMiles(carsandtruck.getCars().get(i).getMiles()+100);
	       }
	       carsandtruck.getTruck().setMiles(carsandtruck.getTruck().getMiles()+100);
		}
		return carsandtruck;
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
