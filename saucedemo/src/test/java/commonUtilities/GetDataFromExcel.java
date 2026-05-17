package commonUtilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class GetDataFromExcel {
	
	
	@DataProvider(name="loginData")
	public Object[][] readData(){

		Object[][] testData = null;
		try {

			FileInputStream file = new FileInputStream("src/test/resources/testData/testData_Invalid.xlsx");
			XSSFWorkbook workBook = new XSSFWorkbook(file);
			XSSFSheet sheet = workBook.getSheet("Sheet1");

			int rowCount = sheet.getPhysicalNumberOfRows();
			int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
			
			System.out.println(rowCount);
			System.out.println(columnCount);

			testData = new Object[rowCount-1][columnCount];
			DataFormatter formatter = new DataFormatter();

			for(int i=1; i<rowCount; i++) {

				XSSFRow row = sheet.getRow(i);
				for(int j=0; j<columnCount; j++) {
					testData[i-1][j]=formatter.formatCellValue(row.getCell(j));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return testData;
	}
}
