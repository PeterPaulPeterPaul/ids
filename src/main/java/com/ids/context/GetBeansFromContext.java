package com.ids.context;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.apache.commons.dbcp.BasicDataSource;

public class GetBeansFromContext {

	private  ApplicationContext ctx;
	//private   DriverManagerDataSource source;
	private   DriverManagerDataSource source;
	private Connection con;

	static final Logger logger = LoggerFactory.getLogger(GetBeansFromContext.class);
	
	
	public String myURL()
	{
		   ctx = AppContext.getApplicationContext(); 
		   ApplicationContextProvider acp = (ApplicationContextProvider) ctx.getBean("contextApplicationContextProvider");
           return acp.getUrl();
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
	
	public Connection myConnection() 
	{
		   ctx = AppContext.getApplicationContext(); 

		     source = (DriverManagerDataSource) ctx.getBean("DataSource");



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
		return con;
	}
	
	
	public void closeCon() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error( "failed!", e );
		}
	}
	
	
	
}

