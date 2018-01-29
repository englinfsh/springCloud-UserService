package com.lin.feng.sheng.service.inner;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lin.feng.sheng.service.InnerCommonRestService;


/**
 * 内部服务层
 * @author lfsheng
 *
 */
@Controller
@EnableEurekaClient
@RestController(value="/user")
public class RestUserService extends InnerCommonRestService {

	@Autowired
    private DiscoveryClient discoveryClient;

	@Value("${server.port}")
    String port;

	@Value("${spring.application.name}")
	String application;


    @SuppressWarnings("unchecked")
	@RequestMapping("/hello")
    public String hello(@RequestParam String name) {

        List<String> instanceServices = discoveryClient.getServices();

        if(instanceServices!=null&&!instanceServices.isEmpty()){
        	logger.error("discoveryClient.getServices:");
        	for (String service : instanceServices) {
        		logger.info(service);
			}

        	for (String ss : instanceServices) {
        		List<ServiceInstance> serviceInstances= discoveryClient.getInstances(ss);

        		if(serviceInstances!=null&&!serviceInstances.isEmpty()){

        			for (ServiceInstance serviceInstance : serviceInstances) {

        				logger.error("-----------------start----------------------------------------------");
        				logger.error("discoveryClient.getServices.getHost:"+serviceInstance.getHost());
        				logger.error("discoveryClient.getServices.getPort:"+serviceInstance.getPort());
        				logger.error("discoveryClient.getServices.getServiceId:"+serviceInstance.getServiceId());
        				logger.error("discoveryClient.getServices.getUri:"+serviceInstance.getUri());
        				logger.error("--------------end--------------------------------------------------------------");

        				Map<String, String> metadataMap = serviceInstance.getMetadata();

        				Set<Entry<String, String>> entry= metadataMap.entrySet();

        				for (@SuppressWarnings("rawtypes")
						Iterator iterator = entry.iterator(); iterator
								.hasNext();) {
							Entry<String, String> entry2 = (Entry<String, String>) iterator
									.next();
							logger.error("metadataMap:"+entry2.getKey()+":"+entry2.getValue());
						}


					}

        		}
			}
        }
        return "hi "+name+",i am from application:"+application+",port:" +port;
    }





}
