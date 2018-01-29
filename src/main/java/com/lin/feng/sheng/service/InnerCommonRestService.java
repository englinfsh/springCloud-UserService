package com.lin.feng.sheng.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

public class InnerCommonRestService {

	public Logger logger= Logger.getLogger(this.getClass());


	public HttpServletRequest getHttpServletRequest(){
		ServletRequestAttributes sAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sAttributes.getRequest();
		return request;
	}

	/**
	 * @param name
	 * @return
	 */
	public Object getRequestAttribute(String name){
		Object  o = this.getHttpServletRequest().getAttribute(name);
		return o;
	}
	/**
	 * @param name
	 * @param o
	 */
	public void setRequestAttribute(String name,Object o){
		 this.getHttpServletRequest().setAttribute(name,o);
	}

	/**
	 * @param name
	 * @return
	 */
	public Object getSessionAttribute(String name ){
		Object o= WebUtils.getSessionAttribute(this.getHttpServletRequest(), name);
		return o;
	}

	/**
	 * @param name
	 * @return
	 */
	public void setSessionAttribute(String name ,Object o){
		 WebUtils.setSessionAttribute(this.getHttpServletRequest(),name,o);
	}

	/**
	 * @param name
	 * @return
	 */
	public Object getRequestParam(String name){
		String  o = this.getHttpServletRequest().getParameter(name);
		if(o!=null){
			return o.trim();
		}
		return o;
	}
	/**
	 * @param name
	 * @return
	 */
	public String[] getRequestParamValues(String name){
		String[]  o = this.getHttpServletRequest().getParameterValues(name);
		if(o!=null&&o.length>0){
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < o.length; i++) {
				String a = (o[i]==null?"":o[i].trim());
				list.add(a);
			}
			return (String[]) list.toArray();
		}
		return o;
	}






}
