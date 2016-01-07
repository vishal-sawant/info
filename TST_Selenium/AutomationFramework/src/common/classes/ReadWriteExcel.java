package common.classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import org.apache.poi.ss.usermodel.*;
import java.io.IOException;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadWriteExcel {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static FileInputStream InputFile;

	// private static XSSFRow Row;
	// This method is to set the File path and to open the Excel file, Pass
	// Excel Path and Sheet name as Arguments to this method
	public static void setExcelFile(String Path, String SheetName)
			throws Exception {
		try {
			// Open the Excel file
			InputFile = new FileInputStream(Path);

			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(InputFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	public static void putCellData(String Path, String SheetName, int RowNum,
			int ColNum, String datatowrite) {
		try {
			FileInputStream InputStream = new FileInputStream(Path);
			// Access the required test data sheet
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(InputStream);
			XSSFSheet sheet = workbook.getSheet(SheetName);
			// Access the required test data sheet
			XSSFCell cell = null;
			cell = sheet.getRow(RowNum).getCell(ColNum);
			cell.setCellValue(datatowrite);
			// cell.setCellValue("testuser090@thestreet.com");
			InputStream.close();
			FileOutputStream OutputFile = new FileOutputStream(Path);
			workbook.write(OutputFile);
			OutputFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			InputFile.close();
			return CellData;
		} catch (Exception e) {
			return "";
		}
	}

	@SuppressWarnings({ "unchecked", "resource" })
	public static Object[][] getExcelData(String fileName, String sheetName)
			throws IOException {
		int rowIndex = 0, columnIndex = 0;
		Workbook wb;
		Sheet ws;
		Row wr;
		Hashtable<Integer, String>[] data = null;
		@SuppressWarnings("rawtypes")
		List data1 = new ArrayList();
		if (fileName.indexOf("xlsx") < 0) {
			wb = new HSSFWorkbook(new FileInputStream(new File(fileName)));
			ws = wb.getSheet(sheetName);
		} else {
			wb = new XSSFWorkbook(fileName);
			ws = (XSSFSheet) wb.getSheet(sheetName);
		}
		data = new Hashtable[ws.getPhysicalNumberOfRows()];
		wr = ws.getRow(0);

		int totalRows = ws.getPhysicalNumberOfRows();
		for (rowIndex = 0; rowIndex < totalRows; rowIndex++) {
			wr = ws.getRow(rowIndex);
			if (wr != null) {

				data[rowIndex] = new Hashtable<Integer, String>();
				int totalCols = ws.getRow(rowIndex).getPhysicalNumberOfCells();

				for (columnIndex = 0; columnIndex < totalCols; columnIndex++) {
					data[rowIndex].put(columnIndex, ws.getRow(rowIndex)
							.getCell(columnIndex).toString());
				}
			}
		}
		int j = data.length;
		for (rowIndex = 0; rowIndex < j; rowIndex++) {
			if (data[rowIndex] != null
					&& !(data[rowIndex].get(0).toString().equals(""))) {
				data1.add(data[rowIndex]);
			}
		}

		int listLength = data1.size();
		Object[][] obj = new Object[listLength][5];
		for (rowIndex = 0; rowIndex < listLength; rowIndex++) {
			Hashtable<Integer, String> temp1 = (Hashtable<Integer, String>) data1
					.get(rowIndex);
			for (columnIndex = 0; columnIndex < 5; columnIndex++) {
				String temp = temp1.get(columnIndex);
				obj[rowIndex][columnIndex] = temp;
			}

		}

		wb = null;
		ws = null;
		wr = null;
		return obj;
	}

}
