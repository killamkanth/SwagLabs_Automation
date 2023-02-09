package commonUtils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	
	private String filePath;
	private Workbook wb;
	private Sheet sheet;
	private Cell cell;
	private String cellValue;

	public ExcelUtils(String filePath) throws EncryptedDocumentException, IOException {
		this.filePath = filePath;
		FileInputStream fip = new FileInputStream(filePath);
		
		wb = WorkbookFactory.create(fip);
		
	}
	
	
	
	public String getSingleCellData(String sheetName, int rowNum,int cellNum) {
		 //String data = "";
		 cell = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum);
		 
		 if(cell.getCellType()==CellType.STRING) {
			  cellValue = cell.getStringCellValue();
			} else if(cell.getCellType()==CellType.NUMERIC) { 
				
				cellValue = cell.getNumericCellValue()+""; 
			}
				 
		 
		 return cellValue;
		
	}
	
	/**
	public String getCellData(String sheetName, String Value) {
		
		sheet = wb.getSheet(sheetName);
		int rowNum = sheet.getLastRowNum();
		//String finalValue;
		for (int i = 0; i < rowNum; i++) 
		{
			int lastCellNum = sheet.getRow(i).getLastCellNum();
			for (int j = 0; j < lastCellNum; j++) 
			{
				String keyValue = sheet.getRow(i).getCell(j).getStringCellValue();
				if(keyValue.equalsIgnoreCase(Value)) 
				{
					Cell req_Value = sheet.getRow(i).getCell(j+1);
					if(req_Value.getCellType()==CellType.STRING) 
					{
						cellValue = req_Value.getStringCellValue();
						System.out.println(keyValue + "::" + cellValue);
						break;
					} else if(req_Value.getCellType()==CellType.NUMERIC) 
					{
						cellValue = req_Value.getNumericCellValue()+"";
						 System.out.println(keyValue + "::" + cellValue);
						break;
					}
				}		
			}		
		}		
		return cellValue;	
	}
	**/
	
	public String getCellData(String sheetName, String Value) {
		
		sheet = wb.getSheet(sheetName);
		int rowNum = sheet.getLastRowNum();
		//String finalValue;
		for (int i = 0; i < rowNum; i++) 
		{
			int lastCellNum = sheet.getRow(i).getLastCellNum();
			String keyValue = sheet.getRow(i).getCell(0).getStringCellValue();
			if(keyValue.equalsIgnoreCase(Value)) 
			{
				for (int j = 1; j < lastCellNum; j++) 
				{
					
						Cell req_Value = sheet.getRow(i).getCell(j+1);
						if(req_Value.getCellType()==CellType.STRING) 
						{
							cellValue = req_Value.getStringCellValue();
							break;
						} else if(req_Value.getCellType()==CellType.NUMERIC) 
						{
							cellValue = req_Value.getNumericCellValue()+"";
							break;
						}
				}		
			}		
		}		
		return cellValue;	
	}
}
