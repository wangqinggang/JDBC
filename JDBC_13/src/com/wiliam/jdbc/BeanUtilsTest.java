package com.wiliam.jdbc;


import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

public class BeanUtilsTest {
	@Test
	public void testGetProperty() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Object object=new Student();
		System.out.println(object);
		
		BeanUtils.setProperty(object, "idCard", "370782199707196614");
		System.out.println(object);
		
		Object val=BeanUtils.getProperty(object, "idCard");
		System.out.println(val);
	}
	
	
	

	
	public void testSetProperty() throws IllegalAccessException, InvocationTargetException {
		Object object=new Student();
		
		BeanUtils.setProperty(object, "idCard", "211121196509091876");
		
		System.out.println(object);
		
	}

}
