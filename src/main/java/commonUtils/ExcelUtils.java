package commonUtils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	
	private String filePath;
	private Workbook wb;
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

}
