package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.LoginBean;
import com.training.dao.ELearningDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;

/* LoginDataProviders is used to provide data to Test method as 2 dimensional object array by reading the excel files
 *  based on the sheet name using Apache POI excel read class file  */

public class LoginDataProviders {

	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<LoginBean> list = new ELearningDAO().getLogins(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(LoginBean temp : list){
			Object[]  obj = new Object[2]; 
			obj[0] = temp.getUserName(); 
			obj[1] = temp.getPassword(); 
			
			result[count ++] = obj; 
		}
		
		
		return result;
	}
	
	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelData(){
		String fileName ="C:\\Users\\IBM_ADMIN\\Videos\\ReSkill-Selenium\\ProjectWork\\TestData.xlsx"; 
		return new ApachePOIExcelRead().getExcelContent(fileName,"Sheet"); 
	}
	
	@DataProvider(name = "RETC_076")
	public Object[][] getExcelData_076(){
		String fileName ="./resources/TestData.xlsx"; 
		String sheetname = "RETC_076Data";
		return new ApachePOIExcelRead().getExcelContent(fileName,sheetname); 
	}
	
	@DataProvider(name = "RETC_078")
	public Object[][] getExcelData_078(){
		String fileName ="./resources/TestData.xlsx"; 
		String sheetname = "RETC_078Data";
		return new ApachePOIExcelRead().getExcelContent(fileName,sheetname); 
	}
	
	@DataProvider(name = "RETC_079")
	public Object[][] getExcelData_079(){
		String fileName ="./resources/TestData.xlsx"; 
		String sheetname = "RETC_079Data";
		return new ApachePOIExcelRead().getExcelContent(fileName,sheetname); 
	}
	
	
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:/Users/Naveen/Desktop/Testing.xls", "Sheet1"); 
	}
}
