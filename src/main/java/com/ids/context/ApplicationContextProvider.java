package com.ids.context;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;



/**
 * This class provides an application-wide access to the
 * Spring ApplicationContext! The ApplicationContext is
 * injected in a static method of the class "AppContext".
 *
 * Use AppContext.getApplicationContext() to get access
 * to all Spring Beans.
 *
 * @author John Hawthorne
 */
public class ApplicationContextProvider implements ApplicationContextAware {


	private String url ="";
	private String username ="";
	private String password ="";
	private String dataSourceName = "";
	

    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        // Wiring the ApplicationContext into a static method
        AppContext.setApplicationContext(ctx);
    }
    
    public String getDataSourceName(){
    	return dataSourceName;
   }
    
    public String getUrl(){
    	return url;
   }
    public void setUrl(String url) {
    	this.url = url;
    }
    
    
    public String getUsername(){
    	return username;
   }
    public void setDataSourceName(String dataSourceName) {
    	this.dataSourceName = dataSourceName;
    }
    
    public void setUsername(String username) {
    	this.username = username;
    }
    
    public String getPassword(){
    	return password;
   }
    public void setPassword(String password) {
    	this.password = password;
    }
    
} 
