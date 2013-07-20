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

	//private String idsDirectory ="";
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        // Wiring the ApplicationContext into a static method
        AppContext.setApplicationContext(ctx);
    }
    
 //   public String getIdsDirectory(){
 //   	return idsDirectory;
 //  }
 //   public void setIdsDirectory(String idsDirectory) {
 //   	this.idsDirectory = idsDirectory;
 //   }
} // .EOF
