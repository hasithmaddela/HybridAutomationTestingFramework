package Utilities;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class ExcelUtil
{
   public String filepath;
   private  FileInputStream fis;
   private  FileOutputStream fos;
   private  XSSFWorkbook wb;
   private  XSSFSheet sheet;
   private  XSSFRow row;
   private  XSSFCell cell;
   private  CellStyle style;
   public  String data;
  
   public ExcelUtil(String filepath)
   {
	   this.filepath=filepath;
   }
   
   // Get RowCount
   public int getRowCount(String sheetName) throws IOException
   {
	   fis=new FileInputStream(filepath);
	   wb=new XSSFWorkbook(fis);
	   sheet=wb.getSheet(sheetName);
	   int rowCount=sheet.getLastRowNum();
	   wb.close();
	   fis.close(); 
	   return rowCount;
   }
   
   //Get Column Count
   public int getColumnCount(String sheetName,int rowNumber) throws IOException
   {
	   fis=new FileInputStream(filepath);
	   wb=new XSSFWorkbook(fis);
	   sheet=wb.getSheet(sheetName);
	   int columnCount=sheet.getRow(rowNumber).getLastCellNum();
	   fis.close();
	   wb.close();
	   return columnCount;
   }
   
   //Get Cell Data
   public String getCellData(String sheetName,int rowNumber,int columnNumber) throws IOException
   {
	   fis=new FileInputStream(filepath);
	   wb=new XSSFWorkbook(fis);
	   sheet=wb.getSheet(sheetName);
	   try 
	   {
		  cell=sheet.getRow(rowNumber).getCell(columnNumber);
		  //data=cell.toString();
		  DataFormatter df=new DataFormatter();
		  data=df.formatCellValue(cell);
	   }
	  catch(Exception e)
	   {
		  data="";
	   }
	   wb.close();
	   fis.close();
	   return data;
   }
   
   //Set Cell Data
    public void setCellData(String sheetName,int rowNumber,int columnNumber,String value) throws IOException
    {
    	fis=new FileInputStream(filepath);
    	wb=new XSSFWorkbook(fis);
    	sheet=wb.getSheet(sheetName);
    	row=sheet.getRow(rowNumber);
    	
    	fos=new FileOutputStream(filepath);
    	row.createCell(columnNumber).setCellValue(value);
    	wb.write(fos);
    	wb.close();
    	fos.close();
    	fis.close();
    }
    
    //Fill GreenBackground Color
    
     public void fillGreenBackground(String sheetName,int rowNumber,int columnNumber) throws IOException
     {
    	 fis=new FileInputStream(filepath);
    	 wb=new XSSFWorkbook(fis);
    	 sheet=wb.getSheet(sheetName);
    	 row=sheet.getRow(rowNumber);
    	 cell=row.getCell(columnNumber);
    	 style=wb.createCellStyle();
    	 style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
  	     style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
  	     cell.setCellStyle(style);
  	     fos=new FileOutputStream(filepath);
	     wb.write(fos);
	     wb.close();
	     fis.close();
	}
     
     //Fill Red BackgroundColor
     public void fillRedBackground(String sheetName,int rowNumber,int columnNumber) throws IOException
     {
    	 fis=new FileInputStream(filepath);
    	 wb=new XSSFWorkbook(fis);
    	 sheet=wb.getSheet(sheetName);
    	 row=sheet.getRow(rowNumber);
    	 cell=row.getCell(columnNumber);
    	 style=wb.createCellStyle();
    	 style.setFillForegroundColor(IndexedColors.RED.getIndex());
  	     style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
  	     cell.setCellStyle(style);
  	     fos=new FileOutputStream(filepath);
	     wb.write(fos);
	     wb.close();
	     fis.close();
	}
     }


