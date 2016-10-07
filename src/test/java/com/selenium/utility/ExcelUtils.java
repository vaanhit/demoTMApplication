package com.selenium.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author Rohit
 *
 */
public class ExcelUtils {

	private static Sheet excelSheet;
	private static Workbook workbook;
	private static Row row;
	private static Cell cell;

	public ExcelUtils() {

	}

	public static void setExcelFile(String Path, String SheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream excelFile = new FileInputStream(Path);
			// Access the required test data sheet
			/******************
			 * NEED TO FIX
			 *********************************************/
			
			workbook = new HSSFWorkbook(excelFile);
			excelSheet = workbook.getSheet(SheetName);
			// excelSheet = new HSSFSheet(ExcelFile);
			// workbook = workbook.getSheet(SheetName);
			/******************
			 * NEED TO FIX
			 *********************************************/
		} catch (Exception e) {
			throw (e);
		}
	}

	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num

	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			cell = excelSheet.getRow(RowNum).getCell(ColNum);
			String CellData = cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			return "";
		}
	}

	// This method is to write in the Excel cell, Row num and Col num are the
	// parameters

	public static void setCellData(String Result, int RowNum, int ColNum) throws Exception {
		try {
			row = excelSheet.getRow(RowNum);
			cell = row.getCell(ColNum, row.RETURN_BLANK_AS_NULL);
			if (cell == null) {
				cell = row.createCell(ColNum);
				cell.setCellValue(Result);
			} else {
				cell.setCellValue(Result);
			}
			// Constant variables Test Data path and Test Data file name

			FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}
}
