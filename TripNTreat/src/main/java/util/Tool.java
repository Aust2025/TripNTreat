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
	private static Member currentMember;
	private static final String TRORDER_FILE = "session_trorder.ser";
	private static Trorder currentTrorder;
	private static final String TRORDERDETAIL_FILE = "session_trorderdetail.ser";
	private static TrorderDetail currentTrorderDetail;
	private static TrorderServiceImpl trorderService = new TrorderServiceImpl();
	private static TrorderDetailServiceImpl trorderDetailService = new TrorderDetailServiceImpl();

	public static void main(String[] args) {
		// Member member = new MemberServiceImpl().Login("M009", "Alex");
		// Tool.saveMember(member);
		// System.out.print(Tool.readMember());
	}

	public static void saveMember(Member member) {
		try (FileOutputStream fos = new FileOutputStream(MEMBER_FILE);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(member);
			currentMember = member;
			System.out.println("Session: Member object saved to " + MEMBER_FILE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveTrorder(Trorder trorder) {
		try (FileOutputStream fos = new FileOutputStream(TRORDER_FILE);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(trorder);
			currentTrorder = trorder;
			System.out.println("Session: Trorder object saved to " + TRORDER_FILE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveTrorderDetail(TrorderDetail trorderDetail) {
		try (FileOutputStream fos = new FileOutputStream(TRORDERDETAIL_FILE);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(trorderDetail);
			currentTrorderDetail = trorderDetail;
			System.out.println("Session: TrorderDetail object saved to " + TRORDERDETAIL_FILE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Member loadMember() {
		return currentMember != null ? currentMember : readMember();
	}

	public static Member readMember() {
		Member member = null;
		File sessionFile = new File(MEMBER_FILE);
		if (!sessionFile.exists()) {
			return null;
		}
		try (FileInputStream fis = new FileInputStream(MEMBER_FILE);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			member = (Member) ois.readObject();
			currentMember = member;
			System.out.println("Session: Member object loaded from " + MEMBER_FILE);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return member;
	}

	public static Trorder readTrorder() {
		Trorder trorder = null;
		File sessionFile = new File(TRORDER_FILE);
		if (!sessionFile.exists()) {
			return null;
		}
		try (FileInputStream fis = new FileInputStream(TRORDER_FILE);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			trorder = (Trorder) ois.readObject();
			currentTrorder = trorder;
			System.out.println("Session: Trorder object loaded from " + TRORDER_FILE);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return trorder;
	}

	public static TrorderDetail readTrorderDetail() {
		TrorderDetail trorderDetail = null;
		File sessionFile = new File(TRORDERDETAIL_FILE);
		if (!sessionFile.exists()) {
			return null;
		}
		try (FileInputStream fis = new FileInputStream(TRORDERDETAIL_FILE);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			trorderDetail = (TrorderDetail) ois.readObject();
			currentTrorderDetail = trorderDetail;
			System.out.println("Session: TrorderDetail object loaded from " + TRORDERDETAIL_FILE);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return trorderDetail;
	}

	public static void saveFile(Object object, String fileName) {
		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(object);
			System.out.println("Object saved to " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object readFile(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			return null;
		}
		try (FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis)) {
			return ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void submitOrder(List<Trorder> trorderList, JTextArea reportArea) {
		if (trorderList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "沒有訂單資料可送出！", "提示", JOptionPane.WARNING_MESSAGE);
			return;
		}

		StringBuilder reportText = new StringBuilder();
		reportText.append(
				"============================================ 訂單報表 ==========================================\n");
		for (Trorder t : trorderList) {
			trorderService.addTrorder(t);
			reportText.append("訂單編號：").append(t.getTrorderNo()).append("\n").append("訂購會員號：").append(t.getMemberNo())
					.append("\n").append("員編：").append(t.getEmployeeNo()).append("\n").append("訂單日期：")
					.append(t.getOrderDate()).append("\n").append("總金額：").append(t.getTotalAmount()).append("\n")
					.append("實付金額：").append(t.getPaidAmount()).append("\n").append("找零金額：").append(t.getChangeAmount())
					.append("\n").append("--------------------------------------------------------------\n")
					.append("訂單明細：\n");

			List<TrorderDetail> details = trorderDetailService.findAllTrorderDetail();
			for (TrorderDetail td : details) {
				if (td.getTrorderdetailNo() != null && t.getTrorderNo() != null
						&& td.getTrorderdetailNo().equals(t.getTrorderNo())) {
					trorderDetailService.addTrorderDetail(td);
					reportText.append("  項目名：").append(td.getItemName()).append("\n").append("  數量：")
							.append(td.getQuantity()).append("\n").append("  單價：").append(td.getUnitPrice())
							.append("\n").append("  小計：").append(td.getAmount()).append("\n");
				}
			}
			reportText.append("\n");
		}

		reportArea.setText(reportText.toString());
		JOptionPane.showMessageDialog(null, "成功送出訂單", "提示", JOptionPane.INFORMATION_MESSAGE);
	}

	public void showOrders(JTextArea reportArea, Member member) {
		List<Trorder> trorderList = trorderService.selectAll();
		StringBuilder reportText = new StringBuilder();
		boolean hasOrder = false;

		for (Trorder t : trorderList) {
			if (t.getMemberNo() != null && t.getMemberNo().equals(member.getMemberno())) {
				hasOrder = true;
				reportText.append("訂單編號：").append(t.getTrorderNo()).append("\n").append("訂購會員號：")
						.append(t.getMemberNo()).append("\n").append("員編：").append(t.getEmployeeNo()).append("\n")
						.append("訂單日期：").append(t.getOrderDate()).append("\n").append("總金額：").append(t.getTotalAmount())
						.append("\n").append("實付金額：").append(t.getPaidAmount()).append("\n").append("找零金額：")
						.append(t.getChangeAmount()).append("\n")
						.append("--------------------------------------------------------------\n").append("訂單明細：\n");

				List<TrorderDetail> trorderDetailList = trorderDetailService.findAllTrorderDetail();
				for (TrorderDetail td : trorderDetailList) {
					if (td.getTrorderdetailNo() != null && t.getTrorderNo() != null
							&& td.getTrorderdetailNo().equals(t.getTrorderNo())) {
						reportText.append("  項目名：").append(td.getItemName()).append("\n").append("  數量：")
								.append(td.getQuantity()).append("\n").append("  單價：").append(td.getUnitPrice())
								.append("\n").append("  小計：").append(td.getAmount()).append("\n");
					}
				}
				reportText.append("\n");
			}
		}

		if (!hasOrder) {
			reportText.append("沒有訂單資料！");
		}

		reportArea.setText(reportText.toString());
	}

	public static boolean isAdmin(Member member) {
		return member != null && member.isAdmin();
	}

	public static boolean isMember(Member member) {
		return member != null && !member.isAdmin();
	}

	public static void clearFile(String fileName) {
		try {
			File file = new File(fileName);
			if (file.exists()) {
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}