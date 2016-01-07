package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ReadWriteExcel {
	private static Logger logger;
	public static final String EXCEPTION = "Some exception occurred:";
	private static volatile FileInputStream InputFile = null;
	private static volatile XSSFWorkbook ExcelWBook = null;
	private static volatile XSSFSheet ExcelWSheet = null;
	private static volatile XSSFCell Cell = null;
	
	public static FileInputStream getInstance(String Path) throws FileNotFoundException {
	    if (InputFile != null) {
	        return InputFile;
	    }
	    InputFile = new FileInputStream(Path);
	    return InputFile;
	}
	
	public static XSSFWorkbook getXSSFWorkbookInstance(FileInputStream IStream) throws IOException {
	    if (ExcelWBook != null) {
	        return ExcelWBook;
	    }
	    ExcelWBook = new XSSFWorkbook(IStream);
	    return ExcelWBook;
	}
	
	public static XSSFSheet getSheetInstance(XSSFWorkbook ExcelWBook, String SheetName) {
	    if (ExcelWSheet != null) {
	        return ExcelWSheet;
	    }
	    ExcelWSheet = ExcelWBook.getSheet(SheetName);
	    return ExcelWSheet;
	}

	public static XSSFCell getCellInstance(XSSFSheet ExcelWSheet, int RowNum, int ColNum) {
	    if (Cell != null) {
	        return Cell;
	    }
	    Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
	    return Cell;
	}

	public static void setExcelFile(String Path, String SheetName) {
		try {
			FileInputStream InputFile = getInstance(Path);
			XSSFWorkbook ExcelWBook = getXSSFWorkbookInstance(InputFile);
			ExcelWSheet = getSheetInstance(ExcelWBook, SheetName);
		} catch (Exception e) {	
			logger.info("Some exception occurred:", e);	
		}
	}

	public static void putCellData(String Path, String SheetName, int RowNum,
			int ColNum, String datatowrite) {
		try {
			FileInputStream InputStream = new FileInputStream(Path);
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(InputStream);
			XSSFSheet sheet = workbook.getSheet(SheetName);
			XSSFCell cell = null;
			cell = sheet.getRow(RowNum).getCell(ColNum);
			cell.setCellValue(datatowrite);
			InputStream.close();
			FileOutputStream OutputFile = new FileOutputStream(Path);
			workbook.write(OutputFile);
			OutputFile.close();
		} catch (Exception e) {
			logger.info("Some exception occurred:", e);
			logger.info(EXCEPTION, e);
		}
	}

	public static String getCellData(int RowNum, int ColNum) {
		try {
			Cell = getCellInstance(ExcelWSheet, RowNum, ColNum);
			String CellData = Cell.getStringCellValue();
			Cell = null;
			InputFile.close();
			return CellData;
		} catch (Exception e) {

			logger.info("Some exception occurred:", e);

			logger.info(EXCEPTION, e);

			return "";
		}
	}

	@SuppressWarnings({ "resource", "static-access" })
	public static String[] getHSSFCellData(String Path, String SheetName,
			int RowNum) {
		String[] CellData = null;
		try {
			FileInputStream fsIP = new FileInputStream(Path);
			HSSFWorkbook wb = new HSSFWorkbook(fsIP);
			HSSFSheet worksheet = wb.getSheet(SheetName);
			HSSFCell XLScell = null;
			int totalNumColumns = worksheet.getRow(RowNum)
					.getPhysicalNumberOfCells();
			CellData = new String[totalNumColumns];
			for (int i = 0; i < totalNumColumns; i++) {
				XLScell = worksheet.getRow(RowNum).getCell(i);
				int cellType = XLScell.getCellType();
				if (cellType == XLScell.CELL_TYPE_NUMERIC) {
					if (!XLScell.toString().equals("")) {
						double numericData = XLScell.getNumericCellValue();
						CellData[i] = String.valueOf(numericData);
					}
				} else {
					if (!XLScell.getStringCellValue().toString().equals(""))
						CellData[i] = XLScell.getStringCellValue();
				}
			}
			fsIP.close();
		} catch (Exception e) {
			logger.info("Some exception occurred:", e);
			logger.info(EXCEPTION, e);
		}
		return CellData;
	}

	@SuppressWarnings({ "unchecked", "resource" })
	public static Object[][] getExcelData(String fileName, String sheetName)
			throws IOException {
		int rowIndex = 0, columnIndex = 0;
		int totalCols = 0;
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
				totalCols = ws.getRow(rowIndex).getPhysicalNumberOfCells();

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
		Object[][] obj = new Object[listLength][totalCols];
		for (rowIndex = 0; rowIndex < listLength; rowIndex++) {
			Hashtable<Integer, String> temp1 = (Hashtable<Integer, String>) data1
					.get(rowIndex);
			for (columnIndex = 0; columnIndex < totalCols; columnIndex++) {
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
