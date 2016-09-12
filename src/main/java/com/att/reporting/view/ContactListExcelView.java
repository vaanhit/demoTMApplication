package com.att.reporting.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.att.demo.entity.Contact;

/**
 * @author Rohit
 *
 */
public class ContactListExcelView extends AbstractXlsView {

	public ContactListExcelView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HSSFSheet excelSheet = (HSSFSheet) workbook.createSheet("Contact List");
		setExcelHeader(excelSheet);

		List<Contact> contactList = (List<Contact>) model.get("contactList");
		setExcelRows(excelSheet, contactList);
	}

	/**
	 * @param excelSheet
	 */
	public void setExcelHeader(HSSFSheet excelSheet) {
		HSSFRow excelHeader = excelSheet.createRow(0);
		excelHeader.createCell(0).setCellValue("First Name");
		excelHeader.createCell(1).setCellValue("Last Name");
		excelHeader.createCell(2).setCellValue("DOB");
		excelHeader.createCell(3).setCellValue("SSN");
		excelHeader.createCell(4).setCellValue("Street");
		excelHeader.createCell(5).setCellValue("City");
		excelHeader.createCell(6).setCellValue("State");
		excelHeader.createCell(7).setCellValue("Zip");
		excelHeader.createCell(8).setCellValue("User");
	}

	/**
	 * @param excelSheet
	 * @param itemList
	 */
	public void setExcelRows(HSSFSheet excelSheet, List<Contact> contactList) {
		int record = 1;
		for (Contact contact : contactList) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			excelRow.createCell(0).setCellValue(contact.getFirstName());
			excelRow.createCell(1).setCellValue(contact.getLastName());
			excelRow.createCell(2).setCellValue(contact.getDob());
			excelRow.createCell(3).setCellValue(contact.getSsn());
			excelRow.createCell(4).setCellValue(contact.getStreet());
			excelRow.createCell(5).setCellValue(contact.getCity());
			excelRow.createCell(6).setCellValue(contact.getZip());
			excelRow.createCell(7).setCellValue(contact.getUserName());
		}
	}

}
