package com.ids.context;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class GetConnectionFromContext {

	private  ApplicationContext ctx;
	private   DataSource source;
	private Connection con;
	static final Logger logger = LoggerFactory.getLogger(GetConnectionFromContext.class);
	
	public Connection myConnection() 
	{
		   ctx = AppContext.getApplicationContext(); 

			  ctx = AppContext.getApplicationContext();  
		       source = (DataSource) ctx.getBean("DataSource");
		        try {
					con =       source.getConnection();
				} catch (SQLException e) {
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
