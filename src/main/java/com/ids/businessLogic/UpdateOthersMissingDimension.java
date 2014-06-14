package com.ids.businessLogic;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.ui.ModelMap;

import com.ids.controllers.MainController;
import com.ids.json.GetTotalCellValue;

public class UpdateOthersMissingDimension {

	private int quantity =0;
	private String missingDimensionId ="";
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getMissingDimensionId() {
		return missingDimensionId;
	}
	
	public void setQuantity(int quantity) {
		this.quantity= quantity;
	}
	
	public void setMissingDimensionId(String missingDimensionId) {
		this.missingDimensionId= missingDimensionId;
	}
	
}
