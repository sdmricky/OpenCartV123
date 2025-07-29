package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException
	{
		String path = ".\\testData\\Opencart_LoginData.xlsx";   //taking excel file from the testData
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcolumns = xlutil.getCellCount("Sheet1", 1);
		
		String loginData[][] = new String[totalrows][totalcolumns];  //created two dimensional array
		
		for(int r = 1;r<=totalrows;r++)
		{
			for(int c = 0;c<=totalcolumns;c++)
			{
				loginData[r-1][c] = xlutil.getCellData("Sheet1", r, c);
			}
		}
		return loginData;        //returning two dimensional array
	}
}
