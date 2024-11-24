package Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders
{
   String filepath;

   //Get All Test Data	
   @DataProvider(name="AllTestData")
   public String[][] getAllTestData() throws IOException
   {
	   ExcelUtil eu=new ExcelUtil(filepath);
	   int rowCount=eu.getRowCount("Sheet1");
	   int columnCount=eu.getColumnCount("Sheet1", rowCount);
	   String data[][]=new String[rowCount][columnCount];
	   for(int i=1;i<=rowCount;i++)
	   {
		   for(int c=0;c<columnCount;c++)
		   {
			   data[i-1][0]=eu.getCellData("Sheet", i, c);
		   }
	   }
	   return data;
   }
	
   //Get Single Column Data
   
   @DataProvider(name="SingleColumnData")
   public String[] getSingleColumnData() throws IOException
   {
	   ExcelUtil eu=new ExcelUtil(filepath);
	   int rowCount=eu.getRowCount("Sheet1");
	   String[] data=new String[rowCount];
	   for(int i=1;i<=rowCount;i++)
	   {
		   data[i-1]=eu.getCellData("Sheet1", i, 1);
	   }
	   return data;
   }
   
}
