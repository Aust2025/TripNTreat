package controller.trorder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import controller.member.LoginSuccess;
import po.Trip;
import po.Trorder;
import po.TrorderDetail;
import po.service.impl.TripServiceImpl;
import po.service.impl.TrorderDetailServiceImpl;
import po.service.impl.TrorderServiceImpl;
import util.ChartUtil;
import util.CreateExcel;
import util.ToolChange;

public class OutputTrorder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TrorderServiceImpl trorderService;
	private TrorderDetailServiceImpl trorderDetailService;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public OutputTrorder() {
		trorderService = new TrorderServiceImpl();
		trorderDetailService = new TrorderDetailServiceImpl();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 標題面板
		JPanel panelHeader = new JPanel();
		panelHeader.setBounds(30, 10, 670, 41);
		panelHeader.setLayout(null);
		panelHeader.setBackground(new Color(250, 119, 146));
		contentPane.add(panelHeader);

		JLabel lblTitle = new JLabel("匯出訂單明細");
		lblTitle.setBounds(177, 6, 300, 35);
		panelHeader.add(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("微軟正黑體", Font.BOLD, 14));

		// 內容面板
		JPanel panelContent = new JPanel();
		panelContent.setBounds(30, 71, 670, 259);
		panelContent.setLayout(null);
		panelContent.setBackground(new Color(250, 119, 146));
		contentPane.add(panelContent);

		JButton btnShowStockChart = new JButton("庫存圖表");
		btnShowStockChart.setBounds(37, 137, 280, 74);
		panelContent.add(btnShowStockChart);
		btnShowStockChart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TripServiceImpl tripService = new TripServiceImpl();
				List<Trip> trips = tripService.findAllTrip();
				JPanel chartPanel = ChartUtil.createStockBarChartPanel(trips);

				JFrame chartFrame = new JFrame("行程庫存圖表");
				chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				chartFrame.getContentPane().add(chartPanel);
				chartFrame.pack();
				chartFrame.setLocationRelativeTo(null);
				chartFrame.setVisible(true);
			}
		});

		JButton btnExcelExport = new JButton("Excel報表");
		btnExcelExport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreateExcel ce = new CreateExcel();
				String excelName = "訂單明細.xls";
				String sheetName = "訂購內容";
				String[] titleName = { "序號", "訂單號", "會員號", "員編", "訂單日", "總金額", "實付金額", "找零金額", "明細序號", "明細號", "項目號",
						"項目名", "數量", "單價", "金額" };

				List<Trorder> trorderList = trorderService.selectAll();
				List<TrorderDetail> trorderDetailList = trorderDetailService.findAllTrorderDetail();

				if (trorderList.isEmpty() && trorderDetailList.isEmpty()) {
					JOptionPane.showMessageDialog(null, "沒有訂單資料可匯出！", "提示", JOptionPane.WARNING_MESSAGE);
					return;
				}

				// 驗證訂單資料完整性
				for (Trorder t : trorderList) {
					if (t.getTrorderNo() == null || t.getMemberNo() == null) {
						JOptionPane.showMessageDialog(null, "訂單資料不完整", "錯誤", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				ce.createExcel(excelName, sheetName, titleName);
				if (!trorderList.isEmpty()) {
					ce.insertValue(excelName, sheetName, trorderList);
				}
				if (!trorderDetailList.isEmpty()) {
					ce.insertExcel(excelName, sheetName, trorderDetailList);
				}
				JOptionPane.showMessageDialog(null, "資料匯出成功至 " + excelName, "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnExcelExport.setBounds(37, 44, 280, 68);
		panelContent.add(btnExcelExport);

		JButton btnPrintPDF = new JButton("PDF列印");
		btnPrintPDF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<Trorder> trorderList = trorderService.selectAll();
				List<TrorderDetail> trorderDetailList = trorderDetailService.findAllTrorderDetail();

				if (trorderList.isEmpty()) {
					JOptionPane.showMessageDialog(null, "沒有可列印的訂單資料！", "提示", JOptionPane.WARNING_MESSAGE);
					return;
				}

				StringBuilder reportText = new StringBuilder();
				reportText.append(
						"============================================ 訂單報表 ==========================================\n");
				for (Trorder t : trorderList) {
					reportText.append("訂單編號：").append(t.getTrorderNo()).append("\n").append("訂購會員號：")
							.append(t.getMemberNo()).append("\n").append("員編：").append(t.getEmployeeNo()).append("\n")
							.append("訂單日期：").append(t.getOrderDate()).append("\n").append("總金額：")
							.append(t.getTotalAmount()).append("\n").append("實付金額：").append(t.getPaidAmount())
							.append("\n").append("找零金額：\n").append(ToolChange.breakdown(t.getChangeAmount()))
							.append("\n").append("--------------------------------------------------------------\n")
							.append("訂單明細：\n");
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

				JTextArea reportArea = new JTextArea(20, 40);
				reportArea.setText(reportText.toString());
				try {
					reportArea.print();
					JOptionPane.showMessageDialog(null, "報表列印成功", "提示", JOptionPane.INFORMATION_MESSAGE);
				} catch (PrinterException ex) {
					JOptionPane.showMessageDialog(null, "列印失敗：" + ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPrintPDF.setBounds(345, 44, 280, 68);
		panelContent.add(btnPrintPDF);

		JButton btnBack = new JButton("回到訂購頁");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginSuccess ls = new LoginSuccess();
				ls.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(345, 137, 280, 74);
		panelContent.add(btnBack);
	}
}