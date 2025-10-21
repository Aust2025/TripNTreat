package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import po.Trorder;
import po.TrorderDetail;

public class CreateExcel {


	public void createExcel(String excelName, String sheetName, String[] titleName) {
		try (HSSFWorkbook workbook = new HSSFWorkbook(); FileOutputStream fos = new FileOutputStream(excelName)) {
			HSSFSheet sheet = workbook.createSheet(sheetName);
			HSSFRow row = sheet.createRow(0);
			for (int i = 0; i < titleName.length; i++) {
				row.createCell(i).setCellValue(titleName[i]);
			}
			workbook.write(fos);
			System.out.println("Excel file created: " + excelName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void insertValue(String excelName, String sheetName, List<Trorder> trorderList) {
		try (FileInputStream fis = new FileInputStream(excelName);
				HSSFWorkbook workbook = new HSSFWorkbook(fis);
				FileOutputStream fos = new FileOutputStream(excelName)) {

			HSSFSheet sheet = workbook.getSheet(sheetName);
			int count = sheet.getPhysicalNumberOfRows();

			for (Trorder tr : trorderList) {
				HSSFRow row = sheet.createRow(count++);
				row.createCell(0).setCellValue(tr.getTrorderId());
				row.createCell(1).setCellValue(tr.getTrorderNo());
				row.createCell(2).setCellValue(tr.getMemberNo());
				row.createCell(3).setCellValue(tr.getEmployeeNo());
				row.createCell(4).setCellValue(tr.getOrderDate().toString());
				row.createCell(5).setCellValue(tr.getTotalAmount());
				row.createCell(6).setCellValue(tr.getPaidAmount());
				row.createCell(7).setCellValue(tr.getChangeAmount());
			}

			workbook.write(fos);
			System.out.println("Trorder data inserted successfully into " + excelName);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void insertExcel(String excelName, String sheetName, List<TrorderDetail> trorderDetailList) {
		try (FileInputStream fis = new FileInputStream(excelName);
				HSSFWorkbook workbook = new HSSFWorkbook(fis);
				FileOutputStream fos = new FileOutputStream(excelName)) {

			HSSFSheet sheet = workbook.getSheet(sheetName);
			int count = sheet.getPhysicalNumberOfRows();

			for (TrorderDetail trd : trorderDetailList) {
				HSSFRow row = sheet.createRow(count++);
				row.createCell(0).setCellValue(trd.getTrorderdetailId());
				row.createCell(1).setCellValue(trd.getTrorderdetailNo());
				row.createCell(2).setCellValue(trd.getItemNo());
				row.createCell(3).setCellValue(trd.getItemName());
				row.createCell(4).setCellValue(trd.getQuantity());
				row.createCell(5).setCellValue(trd.getUnitPrice());
				row.createCell(6).setCellValue(trd.getAmount());
			}

			workbook.write(fos);
			System.out.println("TrorderDetail data inserted successfully into " + excelName);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//配合OutputTrorder 插入 Trorder + TrorderDetail
	public void insertValue(String excelName, String sheetName, Object[] rowData, int rowIndex) {
		try (FileInputStream fis = new FileInputStream(excelName);
				HSSFWorkbook workbook = new HSSFWorkbook(fis);
				FileOutputStream fos = new FileOutputStream(excelName)) {

			HSSFSheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				sheet = workbook.createSheet(sheetName);
			}

			HSSFRow row = sheet.createRow(rowIndex);
			for (int i = 0; i < rowData.length; i++) {
				if (rowData[i] instanceof String) {
					row.createCell(i).setCellValue((String) rowData[i]);
				} else if (rowData[i] instanceof Integer) {
					row.createCell(i).setCellValue((Integer) rowData[i]);
				} else if (rowData[i] instanceof Double) {
					row.createCell(i).setCellValue((Double) rowData[i]);
				} else if (rowData[i] != null) {
					row.createCell(i).setCellValue(rowData[i].toString());
				} else {
					row.createCell(i).setCellValue("");
				}
			}

			workbook.write(fos);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
