package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
    
    public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;   
	private static final String path = "src/test/resources/TestData.xlsx";
		
	public int getRowCount(String sheetName) throws IOException 
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;		
	}
	
	public int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	public String getCellData(String sheetName,String rowName) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int lastRow=sheet.getLastRowNum();
		int n=0;
		while(n<=lastRow) {
			if(sheet.getRow(n).getCell(0).toString().equals(rowName)) {
				cell=sheet.getRow(n).getCell(1);
				break;
			}
			n++;
		}
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try{
		data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
	public void setCellData(String sheetName, String rowName, String data) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        int lastRow = sheet.getLastRowNum();
        int rowIndex = -1;

        for (int i = 0; i <= lastRow; i++) {
            if (sheet.getRow(i).getCell(0).toString().equals(rowName)) {
                rowIndex = i;
                break;
            }
        }

        if (rowIndex == -1) { // If rowName not found, create a new row
            rowIndex = lastRow + 1;
            row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(rowName); // Setting rowName in the first column
        } else {
            row = sheet.getRow(rowIndex);
        }

        cell = row.createCell(1); // Assuming you want to write data in the second column
        cell.setCellValue(data);

        fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();
    }

}
