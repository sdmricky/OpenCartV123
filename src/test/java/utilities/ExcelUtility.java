package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	FileInputStream fi;
	FileOutputStream fo;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	CellStyle style;
	String path;
	
	public ExcelUtility(String path)
	{
		this.path = path;
	}
	
	public int getRowCount(String sheetname) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}
	
	public int getCellCount(String sheetname, int rowcount) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rowcount);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	public String getCellData(String sheetname, int rowcount, int column) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rowcount);
		cell = row.getCell(column);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try
		{
			data = formatter.formatCellValue(cell);
		}
		catch(Exception e)
		{
			data = "";
		}
		
		workbook.close();
		fi.close();
		return data;
	}
	
	public void setCellData(String sheetname, int rownum, int column, String data) throws IOException
	{
		File xlfile = new File(path);
		if(!xlfile.exists())               //if file not exist, create a new file
		{
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
		}
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		if((workbook.getSheetIndex(sheetname)) == -1)      //if sheet doesn't exist, then create new Sheet
		{
			workbook.createSheet(sheetname);
			sheet = workbook.getSheet(sheetname);
		}
			
		if((sheet.getRow(rownum)) == null)            //if row doesn't exist, then create new Row
		{
			sheet.createRow(rownum);
			row = sheet.getRow(rownum);
		}
				
		cell = row.createCell(column);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
	}
	
	public void setGreenColor(String sheetname, int rownum, int column) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		cell = row.getCell(column);
		
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	public void setRedColor(String sheetname, int rownum, int column) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		cell = row.getCell(column);
		
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
}
