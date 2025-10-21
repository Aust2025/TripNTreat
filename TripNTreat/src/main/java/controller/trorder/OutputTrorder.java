package controller.trorder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.print.PrinterException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import po.Trorder;
import po.TrorderDetail;
import po.service.impl.TrorderDetailServiceImpl;
import po.service.impl.TrorderServiceImpl;
import util.Tool;

public class OutputTrorder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TrorderServiceImpl trorderService;
	private TrorderDetailServiceImpl trorderDetailService;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				OutputTrorder frame = new OutputTrorder();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public OutputTrorder() {
		trorderService = new TrorderServiceImpl();
		trorderDetailService = new TrorderDetailServiceImpl();

		setTitle("匯出訂單明細");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelHeader = new JPanel();
		panelHeader.setBounds(30, 10, 670, 41);
		panelHeader.setLayout(null);
		panelHeader.setBackground(new Color(250, 119, 146));
		contentPane.add(panelHeader);

		JLabel lblTitle = new JLabel("匯出訂單明細");
		lblTitle.setBounds(177, 6, 300, 35);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Serif", Font.BOLD, 16));
		panelHeader.add(lblTitle);

		JPanel panelContent = new JPanel();
		panelContent.setBounds(30, 71, 670, 259);
		panelContent.setLayout(null);
		panelContent.setBackground(new Color(250, 119, 146));
		contentPane.add(panelContent);

		JButton btnExcelExport = new JButton("Excel報表");
		btnExcelExport.setFont(new Font("Serif", Font.PLAIN, 13));
		btnExcelExport.setBounds(348, 144, 280, 68);
		panelContent.add(btnExcelExport);
		btnExcelExport.addActionListener(e -> exportExcel());

		JButton btnPrintPDF = new JButton("PDF列印");
		btnPrintPDF.setFont(new Font("Serif", Font.PLAIN, 13));
		btnPrintPDF.setBounds(345, 44, 280, 68);
		panelContent.add(btnPrintPDF);
		btnPrintPDF.addActionListener(e -> printPDF());

		JButton btnBack = new JButton("回到訂購頁");
		btnBack.setFont(new Font("Serif", Font.PLAIN, 13));
		btnBack.setBounds(38, 94, 280, 74);
		panelContent.add(btnBack);
		btnBack.addActionListener(e -> dispose());
	}

	// 匯出 Excel 報表
	private void exportExcel() {
		List<Trorder> trorders = trorderService.selectAll();
		if (trorders.isEmpty()) {
			JOptionPane.showMessageDialog(this, "沒有訂單資料可匯出！", "提示", JOptionPane.WARNING_MESSAGE);
			return;
		}

		JTextArea reportArea = new JTextArea(20, 40);
		Tool.exportOrderExcel(trorders, "訂單明細.xls", reportArea);
	}

	// 列印 PDF 報表
	private void printPDF() {
		try {
			List<Trorder> trorders = trorderService.selectAll();
			if (trorders.isEmpty()) {
				JOptionPane.showMessageDialog(this, "沒有訂單資料可列印！", "提示", JOptionPane.WARNING_MESSAGE);
				return;
			}

			StringBuilder reportText = new StringBuilder();
			reportText.append("==================================== 訂單報表 ====================================\n");

			for (Trorder order : trorders) {
				reportText.append(String.format("訂單編號：%s | 會員號：%s | 員工編號：%s | 訂單日：%s\n", order.getTrorderNo(),
						order.getMemberNo(), order.getEmployeeNo(), order.getOrderDate()));
				reportText.append(String.format("總金額：%d | 實付：%d | 找零：%d\n", order.getTotalAmount(),
						order.getPaidAmount(), order.getChangeAmount()));
				reportText.append("訂單明細：\n");

				List<TrorderDetail> details = trorderDetailService.findByTrorderNo(order.getTrorderNo());
				if (details.isEmpty()) {
					reportText.append("  （無細項資料）\n");
				} else {
					reportText.append(String.format("%-10s%-15s%-10s%-10s%-10s\n", "細項號", "項目名", "數量", "單價", "小計"));
					for (TrorderDetail d : details) {
						reportText.append(String.format("%-10s%-15s%-10d%-10d%-10d\n", d.getTrorderdetailNo(),
								d.getItemName(), d.getQuantity(), d.getUnitPrice(), d.getAmount()));
					}
				}
				reportText.append("--------------------------------------------------------------\n");
			}

			JTextArea reportArea = new JTextArea(20, 40);
			reportArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
			reportArea.setText(reportText.toString());
			reportArea.print();
			JOptionPane.showMessageDialog(this, "✅ 報表列印成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

		} catch (PrinterException ex) {
			JOptionPane.showMessageDialog(this, "列印失敗：" + ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}
}
