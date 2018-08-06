package freeCRM_Cucumber_BDD_page;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.NoSuchFrameException;

public class CommonUtil extends freeCRMBase{
		
	public static String TestData_Path="C:/Users/srida/Documents/JavaTrainingSession/freeCRM-Cucumber-BDD/"
			+ "freeCRM_NewContacts_Data.xlsx";
	Workbook book;
	Sheet sheet;
	
	public void switchToFrame(){
		try{
			driver.switchTo().frame("mainpanel");
		}catch(NoSuchFrameException e){
			System.out.println("frame not found exception");
		}
	}
	
	public Object[][] getTestData(String sheetName){
		FileInputStream file = null;
		try{
			file= new FileInputStream(TestData_Path);
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0; i<sheet.getLastRowNum(); i++){
			for (int j=0; j<sheet.getRow(0).getLastCellNum(); j++){
				data[i][j]= sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
	}
}
