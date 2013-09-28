package com.ids.businessLogic;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;



public class DownloadExcel {

	private final static Logger logger = Logger.getLogger(DownloadExcel.class.getName()); 
	 private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	public DownloadExcel( JSONObject myData, HttpServletRequest request, HttpServletResponse resp) throws IOException {
		
		
		//tell browser program going to return an application file 
        //instead of html page
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition","attachment;filename=temp.csv");
 
	//try 
	//{
		ServletOutputStream out = resp.getOutputStream();
		StringBuffer sb = generateCsvFileBuffer();
 
		InputStream in = 
                    new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
 
		byte[] outputByte = new byte[4096];
		//copy binary contect to output stream
		while(in.read(outputByte, 0, 4096) != -1)
		{
			out.write(outputByte, 0, 4096);
		}
		in.close();
		out.flush();
		out.close();
 
	//  }
	//  return null;
	
 

		
		

        
        
	//	 HttpSession session = request.getSession(true);
	//	 ServletContext context = session.getServletContext();
	 //     String realContextPath = context.getRealPath(request.getContextPath());
	 //     logger.warning(realContextPath);
		 //  FileOutputStream fileOut = new FileOutputStream(realContextPath+"\\off-highway.xls");
		//	HSSFWorkbook workbook = new HSSFWorkbook();
		//	HSSFSheet worksheet = workbook.createSheet("Off-Highway Research");

		//	workbook.write(fileOut);
		//	fileOut.flush();
		//	fileOut.close();
			
			
		   Runtime.getRuntime().gc();
		   
		   

		if(myData.has("tabData")){
			try {
				logger.warning("it has tabData");

				JSONArray clobArray = myData.getJSONArray("tabData");
				
				
				JSONObject jo1 = clobArray.getJSONObject(1);
				logger.warning("size1 is: "+clobArray.length());

				List<String> columnHeaders = new ArrayList<String>();
				if (jo1.has("columns")){
					logger.warning("it has myData");
					JSONArray myArray = jo1.getJSONArray("columns");
					logger.warning("myArray size: "+myArray.length());
					for (int i = 0; i < myArray.length();i++){
						columnHeaders.add(myArray.getString(i));

					}
				}
				
				
				
				JSONObject jo2 = clobArray.getJSONObject(2);
				logger.warning("size2 is: "+jo2.length());

				if (jo2.has("myData")){
					logger.warning("it has myData");
					JSONArray myDataArray = jo2.getJSONArray("myData");
					logger.warning("myData size: "+myDataArray.length());
					for (int i = 0; i < +myDataArray.length();i++){
						for (String s : columnHeaders){
						   try{
							
						      logger.warning("array value: "+myDataArray.getJSONObject(i).getString(s).trim().replaceAll(",",""));
						   }catch(Exception e){
							   logger.warning("not found");
						   }
						}
					}
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}


	private static StringBuffer generateCsvFileBuffer()
	{
		StringBuffer writer = new StringBuffer();
	 
		writer.append("DisplayName");
		writer.append(',');
		writer.append("Age");
		writer.append(',');
		writer.append("HandPhone");
		writer.append('\n');
	 
	        writer.append("mkyong");
		writer.append(',');
		writer.append("26");
		writer.append(',');
		writer.append("0123456789");
		writer.append('\n');
	 
		return writer;
	}

}
