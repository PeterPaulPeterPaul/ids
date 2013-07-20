package com.ids.context;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.apache.commons.dbcp.BasicDataSource;

public class GetBeansFromContext {

	private  ApplicationContext ctx;
	//private   DriverManagerDataSource source;
	private   BasicDataSource source;
	private Connection con;

	static final Logger logger = LoggerFactory.getLogger(GetBeansFromContext.class);
	
	
	//public String myDirectory()
	//{
     
	//	   ctx = AppContext.getApplicationContext(); 
	//	   ApplicationContextProvider acp = (ApplicationContextProvider) ctx.getBean("contextApplicationContextProvider");
   //        return acp.getIdsDirectory();
	//	
	//}
	
	
	
	public Connection myConnection() 
	{
		   ctx = AppContext.getApplicationContext(); 

		     source = (BasicDataSource) ctx.getBean("springDataSource");



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

