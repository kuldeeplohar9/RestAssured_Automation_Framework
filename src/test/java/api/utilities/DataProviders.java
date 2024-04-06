package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "AllData")
	public String[][] AllDataProvider() {

		String fname = System.getProperty("user.dir") + "//TestData//TestData_RestAssured.xlsx";
		int ttlrowcount = ReadExcelFile.getRowCount(fname, "sheet1");
		int ttlcolcount = ReadExcelFile.getColCount(fname, "sheet1");
		String UserData[][] = new String[ttlrowcount - 1][ttlcolcount];
		for (int rowno = 1; rowno < ttlrowcount; rowno++) {
			for (int colno = 0; colno < ttlcolcount; colno++) {
				UserData[rowno - 1][colno] = ReadExcelFile.getCellValue(fname, "sheet1", rowno, colno);
			}
		}
		return UserData;
	}

	@DataProvider(name = "UserNamesData")
	public Object[] UserNamesData() {

		String fname = System.getProperty("user.dir") + "//TestData//TestData_RestAssured.xlsx";
		int ttlrowcount = ReadExcelFile.getRowCount(fname, "sheet1");
		// int ttlcolcount = ReadExcelFile.getColCount(fname, "sheet1");
		String UsernamesData[] = new String[ttlrowcount - 1];
		for (int rowno = 1; rowno < ttlrowcount; rowno++) {
			UsernamesData[rowno - 1] = ReadExcelFile.getCellValue(fname, "sheet1", rowno, 1);
		}
		return UsernamesData;
	}
}
