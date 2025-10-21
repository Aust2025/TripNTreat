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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import po.Trorder;
import po.TrorderDetail;
import po.service.impl.TrorderDetailServiceImpl;
import po.service.impl.TrorderServiceImpl;

public class ConfirmOrder extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable orderTable;
	private DefaultTableModel tableModel;

	private TrorderServiceImpl trorderService = new TrorderServiceImpl();
	private TrorderDetailServiceImpl trorderDetailService = new TrorderDetailServiceImpl();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new ConfirmOrder().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ConfirmOrder() {
		setTitle("確認顧客訂單");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(248, 245, 240));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(64, 224, 208));
		headerPanel.setBounds(20, 8, 704, 35);
		contentPane.add(headerPanel);
		headerPanel.setLayout(null);

		JLabel titleLabel = new JLabel("確認顧客訂單");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
		titleLabel.setBounds(277, 0, 160, 35);
		headerPanel.add(titleLabel);

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(64, 224, 208));
		mainPanel.setBounds(20, 55, 704, 380);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		JLabel welcomeLabel = new JLabel("選擇要確認的訂單");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(Color.BLACK);
		welcomeLabel.setFont(new Font("Serif", Font.BOLD, 16));
		welcomeLabel.setBounds(177, 8, 367, 25);
		mainPanel.add(welcomeLabel);

		String[] columnNames = { "訂單編號", "會員號", "訂單日期", "總金額", "狀態" };
		tableModel = new DefaultTableModel(columnNames, 0);
		orderTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(orderTable);
		scrollPane.setBounds(74, 45, 565, 250);
		mainPanel.add(scrollPane);

		loadOrderData();

		orderTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = orderTable.getSelectedRow();
				if (row >= 0) {
					String trorderNo = tableModel.getValueAt(row, 0).toString();

					List<TrorderDetail> details = trorderDetailService.findByTrorderNo(trorderNo);
					StringBuilder detailText = new StringBuilder("訂單編號：" + trorderNo + "\n\n");

					if (details == null || details.isEmpty()) {
						detailText.append("（此訂單沒有細項）");
					} else {
						detailText
								.append(String.format("%-15s%-15s%-10s%-10s%-10s\n", "細項編號", "項目名稱", "數量", "單價", "小計"));
						for (TrorderDetail td : details) {
							detailText.append(String.format("%-15s%-15s%-10d%-10d%-10d\n", td.getTrorderdetailNo(),
									td.getItemName(), td.getQuantity(), td.getUnitPrice(), td.getAmount()));
						}
					}

					JOptionPane.showMessageDialog(ConfirmOrder.this, detailText.toString(), "訂單細項",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		JButton confirmButton = new JButton("確認訂單");
		confirmButton.setFont(new Font("Serif", Font.BOLD, 13));
		confirmButton.setBounds(293, 307, 150, 35);
		mainPanel.add(confirmButton);

		confirmButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = orderTable.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(ConfirmOrder.this, "請選擇一筆訂單", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String trorderNo = tableModel.getValueAt(row, 0).toString();
				Trorder trorder = trorderService.findTrorderByNo(trorderNo);

				if (trorder != null) {
					trorder.setStatus("已確認");
					trorderService.updateTrorder(trorder);
					JOptionPane.showMessageDialog(ConfirmOrder.this, "訂單確認！", "提示", JOptionPane.INFORMATION_MESSAGE);
					loadOrderData();
				} else {
					JOptionPane.showMessageDialog(ConfirmOrder.this, "找不到訂單資料", "錯誤", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton backButton = new JButton("返回主頁");
		backButton.setFont(new Font("Serif", Font.BOLD, 13));
		backButton.setBounds(489, 307, 150, 35);
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

	private void loadOrderData() {
		try {
			List<Trorder> orders = trorderService.selectAll();
			tableModel.setRowCount(0);

			for (Trorder order : orders) {
				tableModel.addRow(new Object[] { order.getTrorderNo(), order.getMemberNo(), order.getOrderDate(),
						order.getTotalAmount(), order.getStatus() != null ? order.getStatus() : "待確認" });
			}

			if (orders.isEmpty()) {
				JOptionPane.showMessageDialog(this, "目前沒有任何訂單資料。", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "載入訂單失敗：" + e.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
