package com.ids.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.apache.commons.dbcp.BasicDataSource;

import com.google.appengine.api.rdbms.AppEngineDriver;

public class GetBeansFromContext {

	private  ApplicationContext ctx;
	//private   DriverManagerDataSource source;
	private   DriverManagerDataSource source;
	private Connection con;

	static final Logger logger = LoggerFactory.getLogger(GetBeansFromContext.class);
	
	public String ajaxURLprefix(){

		   if (myDataSourceName().equals("GOOGLE") || myDataSourceName().equals("AMAZON") ) {
			  return "/"; 
		   }
        //return "/idstest/";
	   return "/ids/";
	}
	public String myURL()
	{
		   ctx = AppContext.getApplicationContext(); 
		   ApplicationContextProvider acp = (ApplicationContextProvider) ctx.getBean("contextApplicationContextProvider");
           return acp.getUrl();
	}
	
	public String excelDownloadPath()
	{
		   ctx = AppContext.getApplicationContext(); 
		   ApplicationContextProvider acp = (ApplicationContextProvider) ctx.getBean("contextApplicationContextProvider");
           return acp.getexcelDownloadPath()+ ajaxURLprefix() +"/images/";
	}
	
	
	
	public String myPassword()
	{
		   ctx = AppContext.getApplicationContext(); 
		   ApplicationContextProvider acp = (ApplicationContextProvider) ctx.getBean("contextApplicationContextProvider");
           return acp.getPassword();
	}
	
	public String myUserId()
	{
		   ctx = AppContext.getApplicationContext(); 
		   ApplicationContextProvider acp = (ApplicationContextProvider) ctx.getBean("contextApplicationContextProvider");
           return acp.getUsername();
	}
	
	
	public String myDataSourceName()
	{
		   ctx = AppContext.getApplicationContext(); 
		   ApplicationContextProvider acp = (ApplicationContextProvider) ctx.getBean("contextApplicationContextProvider");
           return acp.getDataSourceName();
	}
	
	
	public Connection myConnection() throws SQLException 
	{
		   ctx = AppContext.getApplicationContext(); 
		   
		   
			 DriverManager.registerDriver(new AppEngineDriver());
			 GetBeansFromContext gcfc = new GetBeansFromContext();
			 
			 //If this is set to GOOGLE YOU ARE RUNNING ON THE APP ENGINE
			 if (!gcfc.myDataSourceName().equals("GOOGLE")) {
			 
			 source = (DriverManagerDataSource) ctx.getBean("DataSource");

		//	 System.out.println("HAWTHORNE: "+source.getUrl() );
		//	 System.out.println("HAWTHORNE: "+source.getUsername() );
		//	 System.out.println("HAWTHORNE: "+source.getPassword()  );

		        try {
		        	con = (Connection) source.getConnection();

		        } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error( "failed!", e );

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error( "failed!", e );
				}
		        
			 } else {
			 
			 
			 con = DriverManager.getConnection(gcfc.myURL(),gcfc.myUserId(),gcfc.myPassword());
			 
			 System.out.println("HAWTHORNE: "+gcfc.myURL() );
		//	 System.out.println("HAWTHORNE: "+gcfc.myUserId() );
		//	 System.out.println("HAWTHORNE: "+gcfc.myPassword() );
			 }

		  

		return con;
	}
	
	
	public void closeCon() {
		try {
			con.close();
			logger.warn("are we closing this too");
		} catch (SQLException e) {
			logger.warn("whoops no");
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error( "failed!", e );
		}
	}
	
	
	
}

