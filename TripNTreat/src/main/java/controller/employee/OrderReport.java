package controller.employee;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import po.Trorder;
import po.TrorderDetail;
import po.service.impl.TrorderDetailServiceImpl;
import po.service.impl.TrorderServiceImpl;

public class OrderReport extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea reportArea;

	private TrorderServiceImpl trorderService = new TrorderServiceImpl();
	private TrorderDetailServiceImpl trorderDetailService = new TrorderDetailServiceImpl();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new OrderReport().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public OrderReport() {
		setTitle("訂單報表");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 973, 661);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(248, 245, 240));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(64, 224, 208));
		headerPanel.setBounds(20, 8, 912, 35);
		contentPane.add(headerPanel);
		headerPanel.setLayout(null);

		JLabel titleLabel = new JLabel("訂單報表");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
		titleLabel.setBounds(393, 0, 160, 35);
		headerPanel.add(titleLabel);

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(64, 224, 208));
		mainPanel.setBounds(20, 55, 912, 545);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(82, 36, 776, 393);
		mainPanel.add(scrollPane);

		reportArea = new JTextArea();
		scrollPane.setViewportView(reportArea);
		reportArea.setEditable(false);
		reportArea.setFont(new Font("微軟正黑體", Font.PLAIN, 14));

		JButton generateButton = new JButton("生成報表");
		generateButton.setFont(new Font("Serif", Font.BOLD, 14));
		generateButton.setBounds(267, 452, 150, 69);
		mainPanel.add(generateButton);

		generateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				generateReport();
			}
		});

		JButton backButton = new JButton("返回主頁");
		backButton.setFont(new Font("Serif", Font.BOLD, 14));
		backButton.setBounds(554, 452, 150, 69);
		mainPanel.add(backButton);

		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Backstage().setVisible(true);
				dispose();
			}
		});

		setLocationRelativeTo(null);
	}

	/**
	 * 生成報表：Trorder 與 TrorderDetail
	 */
	private void generateReport() {
		try {
			List<Trorder> trorderList = trorderService.selectAll();

			if (trorderList == null || trorderList.isEmpty()) {
				reportArea.setText("目前沒有任何訂單資料。\n");
				return;
			}

			StringBuilder report = new StringBuilder();
			report.append("【訂單報表】\n");
			report.append("------------------------------------------------------------\n");

			for (Trorder order : trorderList) {
				report.append("訂單編號：").append(order.getTrorderNo()).append("\n");
				report.append("會員號：").append(order.getMemberNo()).append("\n");
				report.append("員工編號：").append(order.getEmployeeNo()).append("\n");
				report.append("訂單日期：").append(order.getOrderDate()).append("\n");
				report.append("總金額：").append(order.getTotalAmount()).append("\n");
				report.append("實付金額：").append(order.getPaidAmount()).append("\n");
				report.append("找零金額：").append(order.getChangeAmount()).append("\n");

				// 查詢細項
				List<TrorderDetail> details = trorderDetailService.findByTrorderNo(order.getTrorderNo());
				if (details != null && !details.isEmpty()) {
					report.append("【訂單細項】\n");
					report.append(String.format("%-15s%-10s%-10s%-10s%-10s\n", "細項編號", "項目名稱", "數量", "單價", "小計"));
					for (TrorderDetail d : details) {
						report.append(String.format("%-15s%-10s%-10d%-10d%-10d\n", d.getTrorderdetailNo(),
								d.getItemName(), d.getQuantity(), d.getUnitPrice(), d.getAmount()));
					}
				} else {
					report.append("（此訂單沒有細項資料）\n");
				}

				report.append("------------------------------------------------------------\n");
			}

			reportArea.setText(report.toString());
		} catch (Exception e) {
			reportArea.setText("生成報表失敗：" + e.getMessage());
			e.printStackTrace();
		}
	}
}
