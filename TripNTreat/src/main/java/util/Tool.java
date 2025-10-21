package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import po.Member;
import po.Trorder;
import po.TrorderDetail;
import po.service.impl.TrorderDetailServiceImpl;
import po.service.impl.TrorderServiceImpl;

public class Tool {

	private static final String MEMBER_FILE = "session_member.ser";
	private static final String TRORDER_FILE = "session_trorder.ser";
	private static final String TRORDERDETAIL_FILE = "session_trorderdetail.ser";

	private static Member currentMember;
	private static Trorder currentTrorder;
	private static TrorderDetail currentTrorderDetail;

	private static TrorderServiceImpl trorderService = new TrorderServiceImpl();
	private static TrorderDetailServiceImpl trorderDetailService = new TrorderDetailServiceImpl();

	
	// 兼容方法
	public static Object readFile(String fileName) {
		return readObject(fileName);
	}

	public static void saveFile(Object object, String fileName) {
		saveObject(object, fileName);
	}

	public static Member readMember() {
		return loadMember();
	}

	
	// 儲存物件
	public static void saveObject(Object object, String fileName) {
		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(object);
			System.out.println("Object saved to " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Object readObject(String fileName) {
		File file = new File(fileName);
		if (!file.exists())
			return null;
		try (FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis)) {
			return ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	// Member 序列化
	public static void saveMember(Member member) {
		saveObject(member, MEMBER_FILE);
		currentMember = member;
	}

	public static Member loadMember() {
		if (currentMember != null)
			return currentMember;
		Object obj = readObject(MEMBER_FILE);
		if (obj instanceof Member)
			currentMember = (Member) obj;
		return currentMember;
	}


	// Trorder 序列化
	public static void saveTrorder(Trorder trorder) {
		saveObject(trorder, TRORDER_FILE);
		currentTrorder = trorder;
	}

	public static Trorder readTrorder() {
		if (currentTrorder != null)
			return currentTrorder;
		Object obj = readObject(TRORDER_FILE);
		if (obj instanceof Trorder)
			currentTrorder = (Trorder) obj;
		return currentTrorder;
	}

	
	// TrorderDetail 序列化
	public static void saveTrorderDetail(TrorderDetail trorderDetail) {
		saveObject(trorderDetail, TRORDERDETAIL_FILE);
		currentTrorderDetail = trorderDetail;
	}

	public static TrorderDetail readTrorderDetail() {
		if (currentTrorderDetail != null)
			return currentTrorderDetail;
		Object obj = readObject(TRORDERDETAIL_FILE);
		if (obj instanceof TrorderDetail)
			currentTrorderDetail = (TrorderDetail) obj;
		return currentTrorderDetail;
	}

	
	public static void clearFile(String fileName) {
		try {
			File file = new File(fileName);
			if (file.exists())
				file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	// 匯出完整報表
	public static void exportOrderExcel(List<Trorder> trorders, String excelName, JTextArea reportArea) {
		if (trorders == null || trorders.isEmpty()) {
			JOptionPane.showMessageDialog(null, "沒有訂單資料可匯出！", "提示", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			String sheetName = "訂購內容";
			String[] titleName = { "序號", "訂單號", "會員號", "員工號", "訂單日", "總金額", "實付金額", "找零金額", "細項號", "項目名", "數量", "單價",
					"小計" };

			util.CreateExcel ce = new util.CreateExcel();
			ce.createExcel(excelName, sheetName, titleName);

			int rowIndex = 1;
			int serialNo = 1;
			StringBuilder reportText = new StringBuilder();
			reportText.append("==================================== 訂單報表 ====================================\n");

			for (Trorder order : trorders) {
				List<TrorderDetail> details = trorderDetailService.findByTrorderNo(order.getTrorderNo());

				if (details.isEmpty()) {
					Object[] rowData = { serialNo++, order.getTrorderNo(), order.getMemberNo(), order.getEmployeeNo(),
							order.getOrderDate(), order.getTotalAmount(), order.getPaidAmount(),
							order.getChangeAmount(), "", "", "", "", "" };
					ce.insertValue(excelName, sheetName, rowData, rowIndex++);
				} else {
					for (TrorderDetail d : details) {
						Object[] rowData = { serialNo++, order.getTrorderNo(), order.getMemberNo(),
								order.getEmployeeNo(), order.getOrderDate(), order.getTotalAmount(),
								order.getPaidAmount(), order.getChangeAmount(), d.getTrorderdetailNo(), d.getItemName(),
								d.getQuantity(), d.getUnitPrice(), d.getAmount() };
						ce.insertValue(excelName, sheetName, rowData, rowIndex++);
					}
				}

				// 同步UI報表
				reportText.append(String.format("訂單號：%s | 會員號：%s | 員工號：%s | 訂單日：%s\n", order.getTrorderNo(),
						order.getMemberNo(), order.getEmployeeNo(), order.getOrderDate()));
				reportText.append(String.format("總金額：%d | 實付：%d | 找零：%d\n", order.getTotalAmount(),
						order.getPaidAmount(), order.getChangeAmount()));
				reportText.append("訂單明細：\n");

				for (TrorderDetail d : details) {
					reportText.append(String.format("  %s | %s | %d | %d | %d\n", d.getTrorderdetailNo(),
							d.getItemName(), d.getQuantity(), d.getUnitPrice(), d.getAmount()));
				}
				reportText.append("--------------------------------------------------------------\n");
			}

			reportArea.setText(reportText.toString());
			JOptionPane.showMessageDialog(null, "Excel 匯出成功：" + excelName, "提示", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Excel 匯出失敗：" + ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}


	public static boolean isAdmin(Member member) {
		return member != null && member.isAdmin();
	}

	public static boolean isMember(Member member) {
		return member != null && !member.isAdmin();
	}
}
